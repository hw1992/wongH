package com.wong.elasticsearch.repository;

import com.wong.entity.BookInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

//泛型的参数分别是实体类型和主键类型
@Component
public interface  BookInfoSearchRepository extends ElasticsearchRepository<BookInfo, Integer> {

    List<BookInfo> findByTitleLike(String title);

    List<BookInfo> findByAuthorLike(String author);

    List<BookInfo> findBookInfoByTitleContainingOrAuthorContaining(String title, String author);
}
