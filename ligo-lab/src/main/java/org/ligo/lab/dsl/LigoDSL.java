package org.ligo.lab.dsl;

public class LigoDSL {

	public static <TYPE> WithClause<TYPE> bind(Class<TYPE> type) {
		return new WithClauseImpl<TYPE>(new ClauseContext<TYPE,Object,Object>(type));
	}
	
}