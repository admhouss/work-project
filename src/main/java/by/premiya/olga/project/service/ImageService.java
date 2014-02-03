package by.premiya.olga.project.service;

import by.premiya.olga.project.entity.Image;

/**
 * @author vlad
 */
public interface ImageService {
    void save(Image image);

    Image getById(Integer id);

    void updateImage(Image image);

    void removeImage(Image image);
}
