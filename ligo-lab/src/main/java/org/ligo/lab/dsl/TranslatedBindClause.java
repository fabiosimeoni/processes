package org.ligo.lab.dsl;

import org.ligo.lab.Binder;
import org.ligo.lab.transform.Transform;

interface TranslatedBindClause<TYPE,IN,OUT> {
	
	Binder<IN,TYPE> with(Transform<OUT,TYPE> binder);
}