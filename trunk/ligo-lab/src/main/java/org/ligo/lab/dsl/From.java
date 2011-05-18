/**
 * 
 */
package org.ligo.lab.dsl;

import org.ligo.lab.Binder;
import org.ligo.lab.DefaultBinder;
import org.ligo.lab.transform.CompositeTransform;
import org.ligo.lab.transform.Transform;

/**
 * @author Fabio Simeoni
 *
 */
public class From<TYPE,IN> implements FromClause<TYPE,IN> {

	Context<TYPE,IN,TYPE> ctxt;
	
	/**
	 * 
	 */
	public From(Context<TYPE,IN,TYPE> c) {
		ctxt=c;
	}
	
	/**{@inheritDoc}*/
	@Override
	public <STREAM> Binder<STREAM, TYPE> and(Transform<STREAM, IN> t) {
		CompositeTransform<STREAM, IN, TYPE> ct = new CompositeTransform<STREAM,IN,TYPE>(t,ctxt.transform()); 
		return new DefaultBinder<STREAM,TYPE>(ct);
	}
	
	/**{@inheritDoc}*/
	@Override
	public Binder<IN, TYPE> build() {
		return new DefaultBinder<IN,TYPE>(ctxt.transform());
	}

}
