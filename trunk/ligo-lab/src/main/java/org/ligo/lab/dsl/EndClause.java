package org.ligo.lab.dsl;

import org.ligo.lab.Binder;
import org.ligo.lab.transform.Transform;

public interface EndClause<TYPE,IN> {
	
	<STREAM> Binder<STREAM,TYPE> and(Transform<STREAM,IN> t);
	Binder<IN,TYPE> build();
}