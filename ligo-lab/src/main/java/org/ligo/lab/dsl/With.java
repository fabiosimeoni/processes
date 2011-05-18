package org.ligo.lab.dsl;

import org.ligo.lab.Binder;
import org.ligo.lab.DefaultBinder;
import org.ligo.lab.transform.CompositeTransform;
import org.ligo.lab.transform.Transform;

class With<TYPE,IN,OUT> implements WithClause<TYPE, IN, OUT> {
	
	Context<TYPE,IN,OUT> ctxt;
	
	/**
	 * 
	 */
	public With(Context<TYPE, IN, OUT> c) {
		ctxt=c;
	}
	
	/**{@inheritDoc}*/
	@Override
	public Binder<IN,TYPE> with(Transform<OUT, TYPE> binder) {
		CompositeTransform<IN, OUT, TYPE> ct = new CompositeTransform<IN, OUT, TYPE>(ctxt.transform,binder); 
		return new DefaultBinder<IN, TYPE>(ct);
	}
}