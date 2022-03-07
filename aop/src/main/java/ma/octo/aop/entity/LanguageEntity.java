package ma.octo.aop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="language")
public class LanguageEntity {


    @Id
    private String id;

    private String name;

    private String author;

    private String fileExtension;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public LanguageEntity() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return "LanguageEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }

    public LanguageEntity(String id, String name, String author, String fileExtension) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.fileExtension = fileExtension;
    }
}
