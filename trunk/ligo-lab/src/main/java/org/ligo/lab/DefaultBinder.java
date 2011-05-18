package org.ligo.lab;

import org.ligo.lab.transform.Transform;

public class DefaultBinder<IN,TYPE> implements Binder<IN, TYPE> {
	
	Transform<IN,TYPE> pipe;
	/**
	 * 
	 */
	public DefaultBinder(Transform<IN,TYPE> p) {
		pipe=p;
	}
	public TYPE bind(IN data) {
		return pipe.transform(data);
	};
}