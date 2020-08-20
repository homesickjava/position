package com.interview.equitypositions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.interview.equitypositions.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T extends BasePojo> {

    @Autowired
    private BaseMapper<T> mapper;

    /**
     * 根据条件查询数据列表
     *
     * @param wrapper
     * @return
     */
    public List<T> queryList(QueryWrapper wrapper) {
        return this.mapper.selectList(wrapper);
    }

    public T queryOne(QueryWrapper wrapper) {
        return (T)this.mapper.selectOne(wrapper);
    }

    /**
     * 保存数据
     *
     * @param record
     * @return
     */
    public Integer save(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return this.mapper.insert(record);
    }
}
