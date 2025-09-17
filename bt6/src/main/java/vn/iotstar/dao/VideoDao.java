package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.Video;

public interface VideoDao {
    void create(Video video);
    void update(Video video);
    void delete(int id);
    Video findById(int id);
    List<Video> findAll();
    List<Video> search(String keyword);
}