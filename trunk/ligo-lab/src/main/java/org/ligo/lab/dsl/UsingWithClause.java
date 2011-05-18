package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TransformFactory;

public interface UsingWithClause<TYPE> {
	
	<IN> FromClause<TYPE,IN> with(Transform<IN,TYPE> binder);
	<IN,OUT> WithClause<TYPE,IN,OUT> with(Transform<IN,OUT> pattern);
	<IN,OUT> TranslatedBindClause<TYPE,IN,OUT> with(TransformFactory<Class<TYPE>,IN,OUT> translation);
}