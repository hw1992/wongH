package com.wong.service;

import com.wong.entity.UserLog;
import com.wong.vo.dto.UserLogDTO;
import com.wong.vo.loginCntVO;

import java.util.List;

public interface UserLogService {

    int deleteByPrimaryKey(Long id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);
    
    void userLogInsert(int userId, int operation, int result);

    List<UserLogDTO> userLogDTO();

    List<loginCntVO> loginCnt();
}