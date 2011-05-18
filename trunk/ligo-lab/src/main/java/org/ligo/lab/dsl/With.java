package org.ligo.lab.dsl;

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
	public FromClause<TYPE,IN> and(Transform<OUT, TYPE> binder) {
		CompositeTransform<IN, OUT, TYPE> ct = new CompositeTransform<IN, OUT, TYPE>(ctxt.transform,binder); 
		return new From<TYPE, IN>(new Context<TYPE, IN,TYPE>(ctxt.boundtype(),ct));
	}
}