package com.wong.service.impl;

import com.wong.entity.UserLog;
import com.wong.mapper.UserLogMapper;
import com.wong.service.UserLogService;
import com.wong.vo.dto.UserLogDTO;
import com.wong.vo.loginCntVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class UserLogServiceImpl implements UserLogService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	private UserLogMapper userLogMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// 缓存存在，删除缓存
		String key = "UserLog-" + id;
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);
			logger.info("UserLogServiceImpl.deleteByPrimaryKey() : 从缓存中删除 ID >> " + id);
		}
		return userLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserLog record) {
		return userLogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserLog record) {
		return userLogMapper.insertSelective(record);
	}

	@Override
	public UserLog selectByPrimaryKey(Long id) {
		//HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		// 从缓存中获取城市信息
		String key = "UserLog-" + id;
		ValueOperations<String, UserLog> operations = redisTemplate.opsForValue();

		// 缓存存在
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			UserLog userLog = operations.get(key);

			logger.info("UserLogServiceImpl.selectByPrimaryKey() : 从缓存中获取  >> " + userLog.toString());
			return userLog;
		}

		// 插入缓存
		UserLog userLog = userLogMapper.selectByPrimaryKey(id);
		operations.set(key, userLog, 120, TimeUnit.MINUTES);//2小时过期
		logger.info("UserLogServiceImpl.selectByPrimaryKey() :  插入缓存 >> " + userLog.toString());

		return userLog;
	}

	@Override
	public int updateByPrimaryKeySelective(UserLog record) {
		// 缓存存在，删除缓存
		String key = "UserLog-" + record.getId();
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);
			logger.info("UserLogServiceImpl.updateByPrimaryKeySelective() : 从缓存中删除  >> " + record.toString());
		}
		return userLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserLog record) {
		// 缓存存在，删除缓存
		String key = "UserLog-" + record.getId();
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			redisTemplate.delete(key);
			logger.info("UserLogServiceImpl.updateByPrimaryKey() : 从缓存中删除  >> " + record.toString());
		}
		return userLogMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public void userLogInsert(int userId,int operation,int result) {
    	UserLog userLog = new UserLog();
    	userLog.setUserId(userId);
    	userLog.setOperation(operation);
    	userLog.setResult(result);
    	userLog.setCreateTime(new Date());
    	int suces = userLogMapper.insertSelective(userLog);
    	if (suces>0) {
    		//logger.info("userLog插入成功",userLog);
    		return;
		}
    	logger.info("userLog插入失败",userLog);
    	return;
    }

	@Override
	public List<UserLogDTO> userLogDTO() {
		return userLogMapper.userLogDTO();
	}

	@Override
	public List<loginCntVO> loginCnt() {
		return userLogMapper.loginCnt();
	}
}
