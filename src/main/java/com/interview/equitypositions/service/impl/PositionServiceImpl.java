package com.interview.equitypositions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.interview.equitypositions.pojo.Position;
import com.interview.equitypositions.service.EPService;
import com.interview.equitypositions.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionServiceImpl extends BaseServiceImpl implements PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);

    public Position getCount(int tradeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 查询的条件
        queryWrapper.eq("trade_id", tradeId);
        Position pos = (Position)(super.queryOne(queryWrapper));

        return pos;
    }
}
