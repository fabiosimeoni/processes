package org.ligo.lab.dsl;

import org.ligo.lab.transform.Transform;

public interface TranslatedBindClause<TYPE,IN,OUT> {
	
	FromClause<TYPE,IN> and(Transform<OUT,TYPE> binder);
}