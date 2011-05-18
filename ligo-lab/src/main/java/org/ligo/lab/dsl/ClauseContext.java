package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;

class ClauseContext<T,IN,OUT> {
	
	Transform<IN,OUT> transform;
	
	Class<T> boundtype;
	/**
	 * 
	 */
	public ClauseContext(Class<T>type) {
		boundtype = type;
	}
	
	public ClauseContext(Class<T>type, Transform<IN, OUT> t) {
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