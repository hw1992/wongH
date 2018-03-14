package com.wong.mapper;

import com.wong.entity.UserLog;
import com.wong.vo.dto.UserLogDTO;
import com.wong.vo.loginCntVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);

    List<UserLogDTO> userLogDTO();

    List<loginCntVO> loginCnt();
}