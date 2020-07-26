package DAO;


import models.News;

import java.util.List;

public interface NewsDao {
    void add(News news);// putting add method mandatory for all hero objects
    News findById(int id);
    List<News> getAllNews();
    void updateNews(int id, String content, String publisher);
    void deleteNewsById(int id);
    void deleteAllNews();
}