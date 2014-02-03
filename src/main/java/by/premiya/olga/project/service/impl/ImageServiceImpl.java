package by.premiya.olga.project.service.impl;

import by.premiya.olga.project.dao.ImageDao;
import by.premiya.olga.project.entity.Image;
import by.premiya.olga.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author vlad
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Image image) {
        imageDao.save(image);
    }

    @Override
    @Transactional(readOnly = true)
    public Image getById(Integer id) {
        return imageDao.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateImage(Image image) {
        imageDao.updateImage(image);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeImage(Image image) {
        imageDao.removeImage(image);
    }
}
