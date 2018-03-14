package com.wong.service.impl;

import com.wong.entity.BookInfo;
import com.wong.mapper.BookInfoMapper;
import com.wong.service.BookInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	private BookInfoMapper bookInfoMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BookInfo record) {
		return bookInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(BookInfo record) {
		return bookInfoMapper.insertSelective(record);
	}

	@Override
	public BookInfo selectByPrimaryKey(Integer id) {
		return bookInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookInfo record) {
		return bookInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookInfo record) {
		return bookInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BookInfo> getBookInfos() {
		return bookInfoMapper.getBookInfos();
	}
}
