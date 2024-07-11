package com.rubypapaer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.rubypaper.domain.Board;

public class JPAClientJPQL {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		try {
//			String jpql = "select b from Board b order by b.seq asc";
			String sql = "select b.title from Board b where b.seq < 5 order by b.seq asc";
			Query q = em.createNativeQuery(sql);
			List<String> list = q.getResultList();
//			TypedQuery<Board> tq = em.createQuery(jpql, Board.class);
//			List<Board> list = tq.getResultList();
//			for (Board b: list) {
//				System.out.println(b);
//			}
			for( String s : list)
				System.out.println(s);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			emf.close();
		}
	}

}
