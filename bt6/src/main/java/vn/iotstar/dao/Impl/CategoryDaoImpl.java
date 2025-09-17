package vn.iotstar.dao.Impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

    private EntityManager em;

    public CategoryDaoImpl() {
        em = Persistence.createEntityManagerFactory("dataSource").createEntityManager();
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public void create(Category category) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Category c = em.find(Category.class, id);
            if (c != null) {
                em.remove(c);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(int id) {
        return em.find(Category.class, id);
    }

    // 👇 Implement findByUserId
    @Override
    public List<Category> findByUserId(int userId) {
        return em.createQuery("SELECT c FROM Category c WHERE c.user.id = :uid", Category.class)
                 .setParameter("uid", userId)
                 .getResultList();
    }
}