package vn.iotstar.dao.Impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.entity.Video;

public class VideoDaoImpl implements VideoDao {

    private EntityManager em;

    public VideoDaoImpl() {
        em = Persistence.createEntityManagerFactory("dataSource").createEntityManager();
    }

    @Override
    public void create(Video video) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(video);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Video video) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(video);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Video v = em.find(Video.class, id);
            if (v != null) {
                em.remove(v);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Video findById(int id) {
        return em.find(Video.class, id);
    }

    @Override
    public List<Video> findAll() {
        return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
    }

    @Override
    public List<Video> search(String keyword) {
        return em.createQuery(
                "SELECT v FROM Video v WHERE v.title LIKE :kw OR v.description LIKE :kw", Video.class)
                .setParameter("kw", "%" + keyword + "%")
                .getResultList();
    }
}
