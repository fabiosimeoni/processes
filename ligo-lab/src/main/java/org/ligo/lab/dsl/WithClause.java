package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;

public interface WithClause<TYPE,IN,OUT> {
	
	FromClause<TYPE,IN> and(Transform<OUT,TYPE> t);
}