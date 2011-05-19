package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;
import org.ligo.lab.transform.TypeTransform;

public interface WithClause<TYPE> {
	
	<IN> EndClause<TYPE,IN> with(Transform<IN,TYPE> binder);
	<IN,OUT> AndClause<TYPE,IN,OUT> with(Transform<IN,OUT> pattern);
	<IN,OUT> AndClause<TYPE,IN,OUT> with(TypeTransform<TYPE,IN,OUT> translation);
}