package org.ligo.lab.transform;

public class CompositeTransform<IN,INOUT,OUT> implements Transform<IN, OUT> {
	
	Transform<IN,INOUT> in;
	Transform<INOUT,OUT> out;
	
	/**
	 * 
	 */
	public CompositeTransform(Transform<IN,INOUT> i, Transform<INOUT,OUT> o) {
		in=i;
		out=o;
	}
	
	public OUT transform(IN data) {
		return out.transform(in.transform(data));
	};
}