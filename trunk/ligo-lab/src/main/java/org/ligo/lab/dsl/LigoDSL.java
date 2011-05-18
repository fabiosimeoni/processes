package org.ligo.lab.dsl;

public class LigoDSL {

	public static <TYPE> UsingWithClause<TYPE> bind(Class<TYPE> type) {
		return new UsingWith<TYPE>(new Context<TYPE,Object,Object>(type));
	}
	
}