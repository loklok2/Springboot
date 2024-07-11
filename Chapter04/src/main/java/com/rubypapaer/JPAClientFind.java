package com.rubypapaer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClientFind {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		try {
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("--->" + searchBoard);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			emf.close();
		}
	}

}
