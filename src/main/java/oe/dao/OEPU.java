package oe.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class OEPU {
	public static EntityManager getEntityManager() {
		var factory = Persistence.createEntityManagerFactory("OEPU");
		return factory.createEntityManager();
	}
}