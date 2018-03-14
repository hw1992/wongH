package com.wong.elasticsearch.service.impl;

import com.wong.elasticsearch.repository.BookInfoSearchRepository;
import com.wong.elasticsearch.service.BookInfoElasticsearchService;
import com.wong.entity.BookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookInfoElasticsearchServiceImpl implements BookInfoElasticsearchService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookInfoSearchRepository bookInfoSearchRepository;

    @Override
    public void save(BookInfo bookInfo) {
        bookInfoSearchRepository.save(bookInfo);
    }

    @Override
    public void delete(Integer id) {
        bookInfoSearchRepository.deleteById(id);
    }

    @Override
    public BookInfo findOne(Integer id) {
        Optional<BookInfo> bookInfo = bookInfoSearchRepository.findById(id);
        return bookInfo.get();
    }

    @Override
    public List<BookInfo> findByTitleLike(String title) {
        return bookInfoSearchRepository.findByTitleLike(title);
    }

    @Override
    public List<BookInfo> findByAuthorLike(String author) {
        return bookInfoSearchRepository.findByAuthorLike(author);
    }

    @Override
    public List<BookInfo> findBookInfoByTitleContainingOrAuthorContaining(String keyword) {

        List<BookInfo> result = bookInfoSearchRepository.findBookInfoByTitleContainingOrAuthorContaining(keyword,keyword);
        if (result != null && !result.isEmpty() && result.size() > 10) {
            return result.subList(0,10);
        }
        return result;
    }
}
