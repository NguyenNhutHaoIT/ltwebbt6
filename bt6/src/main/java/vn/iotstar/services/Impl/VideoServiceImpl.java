package vn.iotstar.services.Impl;

import java.util.List;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.dao.Impl.VideoDaoImpl;
import vn.iotstar.entity.Video;
import vn.iotstar.services.VideoService;

public class VideoServiceImpl implements VideoService {

    VideoDao videoDao = new VideoDaoImpl();

    @Override
    public void create(Video video) {
        videoDao.create(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void delete(int id) {
        videoDao.delete(id);
    }

    @Override
    public Video findById(int id) {
        return videoDao.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> search(String keyword) {
        return videoDao.search(keyword);
    }
}
