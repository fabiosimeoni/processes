package org.ligo.lab.dsl;

import org.ligo.lab.Binder;
import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;

interface UsingWithClause<TYPE> {
	
	<IN> Binder<IN,TYPE> with(Transform<IN,TYPE> binder);
	<IN,OUT> WithClause<TYPE,IN,OUT> using(Transform<IN,OUT> pattern);
	<IN,OUT> TranslatedBindClause<TYPE,IN,OUT> using(TransformFactory<Class<TYPE>,IN,OUT> translation);
}