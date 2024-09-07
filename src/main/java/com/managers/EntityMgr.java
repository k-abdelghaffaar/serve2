package com.managers;

import java.util.List;

import com.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EntityMgr {

    private static EntityMgr instance;
    private final EntityManagerFactory emf;

    private EntityMgr() {
        this.emf = Persistence.createEntityManagerFactory("unit");
    }

    // Public method to provide access to the instance
    public static synchronized EntityMgr getInstance() {
        if (instance == null) {
            instance = new EntityMgr();
        }
        return instance;
    }

    public List<User> fetchUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> usersList;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            
            Root<User> userRoot = cq.from(User.class);
            
            // Define the query
            cq.select(userRoot);
            
            // Execute the query
            usersList = em.createQuery(cq).getResultList();
            
        } finally {
            em.close();
        }

        return usersList;
    }

    public void addUser(User u){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    public User getUserByCredentials(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            
            Root<User> userRoot = cq.from(User.class);
            
            Predicate usernamePredicate = cb.equal(userRoot.get("username"), username);
            Predicate passwordPredicate = cb.equal(userRoot.get("password"), password);
            
            cq.where(cb.and(usernamePredicate, passwordPredicate));
            
            List<User> resultUSer = em.createQuery(cq).getResultList();
            
            // Return the first result or null if not found
            return resultUSer.isEmpty()? null: resultUSer.get(0);
        } finally {
            em.close();
        }
    }

    public String SearchForJSP(String name){
        String result = "";
        if (name == null)
            return result;
        String sqlQuery = "SELECT * FROM User WHERE firstName LIKE ? OR lastName LIKE ?";

        Query query = EntityMgr.getEntityManager().createNativeQuery(sqlQuery, User.class);
        query.setParameter(1, "%" + name + "%");
        query.setParameter(2, "%" + name + "%");

        @SuppressWarnings("unchecked")
        List<User> list = query.getResultList();
        
        {
            for (var i : list) {
                result += i.getFirstName() + " " + i.getLastName() +",";
            }
        }
        if (result.length() > 0) {
            result = result.substring(0,result.length() - 1);
        }

        return result;
    }

    public User getUserById(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            
            Root<User> userRoot = cq.from(User.class);
            
            Predicate idPredicate = cb.equal(userRoot.get("id"), id);
            
            cq.where(idPredicate);
            
            List<User> resultUSer = em.createQuery(cq).getResultList();
            
            // Return the first result or null if not found
            return resultUSer.isEmpty()? null: resultUSer.get(0);
        } finally {
            em.close();
        }
    }

    public static  EntityManager getEntityManager(){
        
        return getInstance().emf.createEntityManager();
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
