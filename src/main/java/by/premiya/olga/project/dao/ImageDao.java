package by.premiya.olga.project.dao;

import by.premiya.olga.project.entity.Image;

/**
 * @author vlad
 */
public interface ImageDao {

    void save(Image image);

    Image getById(Integer id);

    void updateImage(Image image);

    void removeImage(Image image);
}
