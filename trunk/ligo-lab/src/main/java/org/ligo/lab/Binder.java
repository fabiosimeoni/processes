package org.ligo.lab;

public interface Binder<IN,TYPE> {
	
	TYPE bind(IN data);
}