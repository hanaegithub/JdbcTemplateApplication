package ma.octo.aop.entity;

import java.util.Objects;

public class Language {
  private  String id;
  private  String name;
  private  String author;
  private  String fileExtension;

  public Language(final String id, final String name, final String author, final String fileExtension) {
    Objects.requireNonNull(id);
    this.id = id;
    this.name = name;
    this.author = author;
    this.fileExtension = fileExtension;
  }

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

  public void setId(String id){
    this.id=id;
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
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof Language)) return false;
    final Language language = (Language) o;
    return id.equals(language.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Language{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", author='" + author + '\'' +
        ", fileExtension='" + fileExtension + '\'' +
        '}';
  }
  public static Language create(String id, String name, String author, String fileExtension) {
    Language person = new Language(id, name, author, fileExtension);
    return person;
  }

}
