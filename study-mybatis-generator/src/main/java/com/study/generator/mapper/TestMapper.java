package com.study.generator.mapper;

import com.study.generator.model.Test;

public interface TestMapper {
    int insert(Test record);

    int insertSelective(Test record);
}