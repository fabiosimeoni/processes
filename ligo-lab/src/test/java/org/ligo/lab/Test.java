/**
 * 
 */
package org.ligo.lab;

import static org.ligo.lab.dsl.LigoDSL.*;

import java.io.Reader;
import java.io.StringReader;

import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;


/**
 * @author Fabio Simeoni
 *
 */
public class Test {

	public static void main(String[] args) {
		
		
		
		
		System.out.println("---- use case 1");

		//config
		Transform<Data,MyType> binder = new DataBinder();
		
		//define
		Binder<Data,MyType> simple = bind(MyType.class).with(binder).build();

		//use 
		Data data = new Data();
		MyType bound = simple.bind(data);

		
		
		
		System.out.println("---- use case 2");

		//config
		Transform<Reader,Data> parser = new DataReader();
		
		//define
		Binder<Reader,MyType> readersimple = bind(MyType.class).with(binder).and(parser);
		
		//use
		Reader stream = new StringReader("sample stream");
		bound = readersimple.bind(stream);
		
		
		
		System.out.println("---- use case 3");
		
		//config
		Transform<Data,Match> pattern = new DataPattern();
		Transform<Match,MyType> mbinder = new MatchBinder();
		
		//define
		Binder<Data,MyType> patterned = bind(MyType.class).with(pattern).and(mbinder).build();
		
		//use
		bound = patterned.bind(data);
		
		
		System.out.println("---- use case 4");
		
		//define
		Binder<Reader,MyType> readerPatterned = bind(MyType.class).with(pattern).and(mbinder).and(parser);

		//user
		bound = readerPatterned.bind(stream);
		
		
		
		System.out.println("---- use case 5");
		
		//config
		TransformFactory<Class<MyType>,Data,Match> translation = new PatternFactory<MyType>();
		
		//define
		Binder<Data,MyType> translated = bind(MyType.class).using(translation).and(mbinder).build();
		
		//use
		bound = translated.bind(data);
		
		
		System.out.println("---- use case 6");
		
		//define
		Binder<Reader,MyType> readerTranslated = bind(MyType.class).using(translation).and(mbinder).and(parser);
		
		//use
		bound = readerTranslated.bind(stream);
		
		System.out.println(bound);
	}
}

//sample impl
class Data {}
class DataReader implements Transform<Reader,Data> {
	public Data transform(Reader in) {
		return new Data();
	}
}

class DataBinder implements Transform<Data,MyType> {
	public MyType transform(Data in) {
		System.out.println("binding "+in.getClass().getSimpleName());
		return new MyType();
	}
}

class MatchBinder implements Transform<Match,MyType> {
	public MyType transform(Match in) {
		System.out.println("binding "+in.getClass().getSimpleName());
		return new MyType();
	}
}

class Match {}

class DataPattern implements Transform<Data,Match> {
	public Match transform(Data in) {
		System.out.println("matching "+in);
		return new Match();
	}
}

class PatternFactory<T> implements TransformFactory<Class<T>,Data,Match> {
	
	/**{@inheritDoc}*/
	@Override
	public DataPattern transform(Class<T> in) {
		System.out.println("generating pattern from "+in.getSimpleName());
		return new DataPattern();
	}
}

//sample app model
class MyType {}
