package com.lxs.graduate.service;

import com.alibaba.fastjson.JSON;
import com.lxs.graduate.dao.EvaluateMapper;
import com.lxs.graduate.entity.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    EvaluateMapper evaluateMapper;

    @Override
    public int addEvaluate(Evaluate evaluate) {
        return   evaluateMapper.insert(evaluate);

    }

    @Override
    public List<Evaluate> getAllEvaluatesByPid(Integer id) {
        return evaluateMapper.getEvaluatesByPid(id);
    }
}
