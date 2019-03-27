package com.study.excel.service.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @param <T>
 * @author walle
 */
public abstract class BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    /**
     * 根据id查询
     *
     * @param id
     * @return T
     */
    public T queryById(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件查询一条数据
     *
     * @param model
     * @return T
     */
    public T queryOne(T model) {
        return this.mapper.selectOne(model);
    }

    /**
     * 查询所有数据
     *
     * @return List<T>
     */
    public List<T> queryAll() {
        return this.mapper.selectAll();
    }

    /**
     * 根据条件查询数据列表
     *
     * @param model
     * @return List<T>
     */
    public List<T> queryListByWhere(T model) {
        return this.mapper.select(model);
    }

    /**
     * 分页查询数据列表
     *
     * @param model    查询条件
     * @param pageNo   起始页
     * @param pageSize 每页大小
     * @return PageInfo<T>
     */
    public PageInfo<T> queryPageListByWhere(T model, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> list = this.mapper.select(model);
        return new PageInfo<>(list);
    }

    /**
     * 分页查询数据列表-基于example的复杂组合条件
     *
     * @param example 复杂组合条件封装
     * @param pageNo   起始页
     * @param pageSize 每页大小
     * @return PageInfo<T>
     */
    public PageInfo<T> queryPageListByWhereExample(Example example, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> list = this.mapper.selectByExample(example);
        return new PageInfo<>(list);
    }

}
