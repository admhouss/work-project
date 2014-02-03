package by.premiya.olga.project.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vlad
 */
@Entity
@Table(name = "IMAGES")
public class Image implements Serializable {
    private static final long serialVersionUID = -6793635556653893357L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Column(name = "PATH")
    private String path;

    public Image() {

    }

    public Image(String type, String contentType, String path) {
        this.type = type;
        this.contentType = contentType;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        return !(id != null ? !id.equals(image.id) : image.id != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", contentType='" + contentType + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
