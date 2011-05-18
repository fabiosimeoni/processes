package org.ligo.lab.transform;

public interface Transform<IN,OUT> {
	
	OUT transform(IN in);
}