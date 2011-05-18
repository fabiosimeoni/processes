package org.ligo.lab.transform;

public interface TransformFactory<IN1,IN,OUT> extends Transform<IN1,Transform<IN,OUT>> {
	
}