package com.sge.util;

import javax.persistence.Persistence;

public class CriarTabelas {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("sge");
	}

}
