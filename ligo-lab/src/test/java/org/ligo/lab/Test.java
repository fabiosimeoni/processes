/**
 * 
 */
package org.ligo.lab;

import static org.ligo.lab.dsl.LigoDSL.*;

import java.io.Reader;

import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;


/**
 * @author Fabio Simeoni
 *
 */
public class Test {

	public static void main(String[] args) {
		
		Transform<MyData,MyType> mybinder = null;
		Transform<Reader,MyData> mydatareader = new DataReader();
		Binder<MyData,MyType> simple = bind(MyType.class).with(mybinder).build();
		Binder<Reader,MyType> readersimple = bind(MyType.class).with(mybinder).from(mydatareader);
		
		MyData data = null;
		MyType bound = simple.bind(data);

		Reader stream = null;
		bound = readersimple.bind(stream);
		
		Transform<MyData,MyDataOut> mypattern = null;
		Transform<MyDataOut,MyType> myotherbinder = null;
		
		Binder<MyData,MyType> patterned = bind(MyType.class).with(mypattern).and(myotherbinder).build();
		Binder<Reader,MyType> readerPatterned = bind(MyType.class).with(mypattern).and(myotherbinder).from(mydatareader);

		
		bound = patterned.bind(mydatareader.transform(stream));
		bound = readerPatterned.bind(stream);
		
		TransformFactory<Class<MyType>,MyData,MyDataOut> translation = null;
		
		Binder<MyData,MyType> translated = bind(MyType.class).using(translation).and(myotherbinder).build();
		Binder<Reader,MyType> readerTranslated = bind(MyType.class).using(translation).and(myotherbinder).from(mydatareader);
		
		bound = translated.bind(data);
		bound = readerTranslated.bind(stream);
		
		System.out.println(bound);
	}
}

//sample impl
class MyData {}
class DataReader implements Transform<Reader,MyData> {
	public MyData transform(Reader in) {
		return null;
	}
}

//sample app model
interface MyDataOut {}
interface MyType {
	void use();
}
