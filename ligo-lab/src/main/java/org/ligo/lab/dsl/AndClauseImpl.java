package org.ligo.lab.dsl;

import org.ligo.lab.transform.CompositeTransform;
import org.ligo.lab.transform.Transform;

class AndClauseImpl<TYPE,IN,OUT> implements AndClause<TYPE, IN, OUT> {
	
	ClauseContext<TYPE,IN,OUT> ctxt;
	
	/**
	 * 
	 */
	public AndClauseImpl(ClauseContext<TYPE, IN, OUT> c) {
		ctxt=c;
	}
	
	/**{@inheritDoc}*/
	@Override
	public EndClause<TYPE,IN> and(Transform<OUT, TYPE> binder) {
		CompositeTransform<IN, OUT, TYPE> ct = new CompositeTransform<IN, OUT, TYPE>(ctxt.transform,binder); 
		return new EndClauseImpl<TYPE, IN>(new ClauseContext<TYPE, IN,TYPE>(ctxt.boundtype(),ct));
	}
}