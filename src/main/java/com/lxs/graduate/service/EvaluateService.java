package com.lxs.graduate.service;


import com.lxs.graduate.entity.Evaluate;

import java.util.List;


public interface EvaluateService {

    public int addEvaluate(Evaluate evaluate);

    public List<Evaluate> getAllEvaluatesByPid(Integer id);
}
