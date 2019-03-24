package com.lxs.graduate.dao;

import com.lxs.graduate.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer eId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer eId);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

    List<Evaluate>  getEvaluatesByPid(Integer id);
}
