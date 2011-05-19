package org.ligo.lab.transform;

public interface TypeTransform<TYPE,IN,OUT> extends Transform<Class<TYPE>,Transform<IN,OUT>> {
	
}