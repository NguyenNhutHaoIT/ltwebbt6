package vn.iotstar.dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import vn.iotstar.dao.UserDao;
import vn.iotstar.entity.User;

public class UserDaoImpl implements UserDao {

    private EntityManager em;

    public UserDaoImpl() {
        em = Persistence.createEntityManagerFactory("dataSource").createEntityManager();
    }

    @Override
    public User login(String username, String password) {
        try {
            return em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :un AND u.password = :pw", User.class)
                    .setParameter("un", username)
                    .setParameter("pw", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) transaction.rollback();
        }
    }

    @Override
    public void update(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) transaction.rollback();
        }
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            User u = em.find(User.class, id);
            if (u != null) {
                em.remove(u);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) transaction.rollback();
        }
    }

    @Override
    public List<User> search(String keyword) {
        return em.createQuery(
                "SELECT u FROM User u WHERE u.username LIKE :kw OR u.fullname LIKE :kw", User.class)
                .setParameter("kw", "%" + keyword + "%")
                .getResultList();
    }
}