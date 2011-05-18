package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;

class UsingWith<TYPE> implements UsingWithClause<TYPE> {
	
	Context<TYPE,?,?> ctxt;

	public UsingWith(Context<TYPE,?,?> c) {
		ctxt = c;
	}
	
	/**{@inheritDoc}*/
	@Override
	public <IN, OUT> WithClause<TYPE, IN, OUT> with(Transform<IN, OUT> pattern) {
		
		Context<TYPE,IN,OUT> c = new Context<TYPE,IN,OUT>(ctxt.boundtype,pattern);
		return new With<TYPE, IN, OUT>(c);
	}

	/**{@inheritDoc}*/
	@Override
	public <IN, OUT> TranslatedBindClause<TYPE, IN, OUT> using(TransformFactory<Class<TYPE>, IN, OUT> translation) {
		Context<TYPE, IN, OUT> c = new Context<TYPE, IN, OUT>(ctxt.boundtype(), translation.transform(ctxt.boundtype));
		return new TranslatedBinding<TYPE, IN, OUT>(c);
	}

	/**{@inheritDoc}*/
	@Override
	public <IN> FromClause<TYPE,IN> with(Transform<IN, TYPE> binder) {
		return new From<TYPE,IN>(new Context<TYPE,IN,TYPE>(ctxt.boundtype(),binder));
	}
	
	
}