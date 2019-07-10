package com.sysone.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {

	public static void rollbackTransaction(Session session, Transaction tx) {
		if (tx != null)
			tx.rollback();
		if (session != null)
			session.close();
	}
	
}
