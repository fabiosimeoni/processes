package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;

class WithClauseImpl<TYPE> implements WithClause<TYPE> {
	
	ClauseContext<TYPE,?,?> ctxt;

	public WithClauseImpl(ClauseContext<TYPE,?,?> c) {
		ctxt = c;
	}
	
	/**{@inheritDoc}*/
	@Override
	public <IN, OUT> AndClause<TYPE, IN, OUT> with(Transform<IN, OUT> pattern) {
		
		ClauseContext<TYPE,IN,OUT> c = new ClauseContext<TYPE,IN,OUT>(ctxt.boundtype,pattern);
		return new AndClauseImpl<TYPE, IN, OUT>(c);
	}

	/**{@inheritDoc}*/
	@Override
	public <IN, OUT> AndClause<TYPE, IN, OUT> with(TransformFactory<Class<TYPE>, IN, OUT> translation) {
		ClauseContext<TYPE, IN, OUT> c = new ClauseContext<TYPE, IN, OUT>(ctxt.boundtype(), translation.transform(ctxt.boundtype));
		return new AndClauseImpl<TYPE, IN, OUT>(c);
	}

	/**{@inheritDoc}*/
	@Override
	public <IN> EndClause<TYPE,IN> with(Transform<IN, TYPE> binder) {
		return new EndClauseImpl<TYPE,IN>(new ClauseContext<TYPE,IN,TYPE>(ctxt.boundtype(),binder));
	}
	
	
}