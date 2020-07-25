package models;

import java.util.Objects;

public class News {
    private int id;
    private String content;
    private String publisher;


    public News(String content, String publisher) {

        this.content= content;
        this.publisher=publisher;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setId(int id){
        this.id=id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(content, news.content) &&
                Objects.equals(publisher, news.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, publisher);
    }
}
