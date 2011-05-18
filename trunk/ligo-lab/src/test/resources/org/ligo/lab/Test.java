/**
 * 
 */
package org.ligo.lab.dsl;

import static org.ligo.lab.dsl.LigoDSL.*;

import org.ligo.lab.Binder;
import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;


/**
 * @author Fabio Simeoni
 *
 */
public class Test {

	public static void main(String[] args) {
		
		Transform<MyData,MyType> mybinder = null;

		Binder<MyData,MyType> simple = bind(MyType.class).with(mybinder);
		
		MyData data = null;
		simple.bind(data).use();
		
		Transform<MyData,MyDataOut> mypattern = null;
		Transform<MyDataOut,MyType> myotherbinder = null;
		Binder<MyData,MyType> patterned = bind(MyType.class).using(mypattern).with(myotherbinder);

		patterned.bind(data).use();
		
		
		TransformFactory<Class<MyType>,MyData,MyDataOut> translation = null;
		Binder<MyData,MyType> translated = bind(MyType.class).using(translation).with(myotherbinder);
		
		translated.bind(data).use();
		
	}
}

//sample model
interface MyData {}
interface MyDataOut {}
interface MyType {
	void use();
}
