package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;

class Context<T,IN,OUT> {
	
	Transform<IN,OUT> transform;
	
	Class<T> boundtype;
	/**
	 * 
	 */
	public Context(Class<T>type) {
		boundtype = type;
	}
	
	public Context(Class<T>type, Transform<IN, OUT> t) {
		this(type);
		transform=t;
	}
	
	public Class<T> boundtype() {
		return boundtype;
	}
	
	public Transform<IN, OUT> transform() {
		return transform;
	}
	
}