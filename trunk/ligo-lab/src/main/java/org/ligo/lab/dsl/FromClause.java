package org.ligo.lab.dsl;

import org.ligo.lab.Binder;
import org.ligo.lab.transform.Transform;

public interface FromClause<TYPE,IN> {
	
	<STREAM> Binder<STREAM,TYPE> from(Transform<STREAM,IN> t);
	Binder<IN,TYPE> build();
}