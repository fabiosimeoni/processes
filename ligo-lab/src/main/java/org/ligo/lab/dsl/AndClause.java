package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;

public interface AndClause<TYPE,IN,OUT> {
	
	EndClause<TYPE,IN> and(Transform<OUT,TYPE> binder);
}