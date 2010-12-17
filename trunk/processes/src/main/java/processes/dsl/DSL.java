/**
 * 
 */
package processes.dsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import processes.PriorityTask;
import processes.Process;
import processes.dsl.impl.OngoingMode;
import processes.dsl.impl.voidtasks.OngoingVoidMode;
import processes.dsl.impl.voidtasks.VoidModeStep;


/**
 * @author Fabio Simeoni (University of Strathclyde)
 *
 */
public class DSL {

	public static <TR> ModeStep<TR,Void> execute(Collection<? extends Callable<TR>> tasks) {
		return new OngoingMode<TR,Void>(tasks);
	}
	
	public static <TR> ModeStep<TR, Void> execute(Callable<TR> task) {
		return new OngoingMode<TR,Void>(Collections.singleton(task));
	}
	
	public static VoidModeStep execute(Collection<? extends Runnable> rs) {
		return execute(rs.toArray(new Runnable[0]));
	}
	public static VoidModeStep execute(Runnable ... tasks) {
		Collection<Callable<Void>> ts = new ArrayList<Callable<Void>>();
		
		for (Runnable r : tasks)
			ts.add(callable(r));
		
		return new OngoingVoidMode(ts);
	}
	
	public static <T extends PriorityTask & Runnable> Process<Void,Void> generate(T ... tasks) {
		
		/**The component handlers indexed by order. */
		Map<Integer,List<T>> index = new TreeMap<Integer,List<T>>(); 
		
		//prepare map
		for (T task : tasks) {
			List<T> siblings = index.get(task.priority());
			if (siblings == null) {
				siblings = new ArrayList<T>();
				index.put(task.priority(), siblings);
			}
			siblings.add(task);
		}
		

		List<Callable<Void>> children = new ArrayList<Callable<Void>>();
		
		for (Integer i : index.keySet()) {
			List<T> siblings = index.get(i);
			if (siblings.size() > 1)
				//add parallel process
				children.add(execute(siblings).inParallel().and().blocking()); 
			else
				//add single task
				children.add(callable(siblings.get(0))); 
		}

		//build final chain
		return execute(children).inSequence().failingOnFailure().and().discardingResults().blocking(); 
		
			
	}
	
	//util
	public static Callable<Void> callable(final Runnable r) {
		return new Callable<Void>() {
			public Void call() throws Exception {
				r.run();
				return null;
			}
		};
	}
	
	public static Collection<Callable<Void>> callable(Runnable ... rs) {
		return callable(Arrays.asList(rs));
	}
	//util
	public static Collection<Callable<Void>> callable(Collection<? extends Runnable> rs) {
		Collection<Callable<Void>> c = new ArrayList<Callable<Void>>();
		for (final Runnable r : rs)
			c.add(new Callable<Void>() {
				public Void call() throws Exception {
					r.run();
					return null;
				}
				});
		return c;
	}
}
