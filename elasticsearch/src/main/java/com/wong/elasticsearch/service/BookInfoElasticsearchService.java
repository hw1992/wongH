package com.wong.elasticsearch.service;

import com.wong.entity.BookInfo;

import java.util.List;

public interface BookInfoElasticsearchService {

    void save(BookInfo bookInfo);

    void delete(Integer id);

    BookInfo findOne(Integer id);

    List<BookInfo> findByTitleLike(String title);

    List<BookInfo> findByAuthorLike(String author);

    List<BookInfo> findBookInfoByTitleContainingOrAuthorContaining(String keyword);
}
