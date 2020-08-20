package com.interview.equitypositions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.interview.equitypositions.pojo.BasePojo;
import com.interview.equitypositions.pojo.Position;
import com.interview.equitypositions.pojo.Trade;
import com.interview.equitypositions.pojo.WebResult;
import com.interview.equitypositions.service.EPService;
import com.interview.equitypositions.service.PositionService;
import com.interview.equitypositions.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class EPServiceImpl extends BaseServiceImpl implements EPService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EPService.class);

    @Autowired
    private PositionService pService;

    /**
     * 插入一批交易记录后，返回结果
     */
    @Override
    public Map<String, String> insert(List<Trade> tradeList) {
        Map<String, String> flag = validateTrade(tradeList);
        Map<String, String> result = new HashMap();

        if (flag == null) {
            outer:
            for (int i = 0; i < tradeList.size(); i++) {
                Trade trade = tradeList.get(i);
                int tradeId = trade.getTradeId();
                //避免重复计算
                if(result.containsKey(trade.getSecurityCode())) {
                    continue outer;
                }
                //如果是CANCEL，交易记录为0
                if(Const.OPTION_CANCEL.equals(trade.getOption())) {
                    result.put(trade.getSecurityCode(), "0");
                    continue outer;
                }

                Position pos = pService.getCount(tradeId);
                int count = pos.getCount(); //获取交易当前记录
                count = getCount(count, trade); //执行当前交易后的交易记录
                //查找所有交易id为tradeId的记录，并计算所有的数据的交易记录
                for (int j = i+1; j<tradeList.size();j++) {
                    Trade trade2 = tradeList.get(j);
                    int tradeId2 = trade2.getTradeId();
                    String option2 = trade2.getOption();
                    if (tradeId == tradeId2) {
                        count = getCount(count, trade2);
                    }
                }
                result.put(trade.getSecurityCode(), count>0?"+"+count:"-"+count);

                //将数据保存到数据库中
                saveData(trade);
                Position posObj = new Position();
                posObj.setTradeId(trade.getTradeId());
                posObj.setCount(count);
                posObj.setSecurityCode(trade.getSecurityCode());
                saveData(posObj);
            }


        }

        return result;
    }

    private void saveData(BasePojo basePojo) {
        super.save(basePojo);
    }

    /**
     * 计算完成一笔交易后的数量
     * 如果是Buy，数量相减
     * 如果是Sell，数量相加
     * @param count 该笔交易的现有数量
     * @param trade  交易对象
     * @return
     */
    private int getCount(int count, Trade trade) {
        if(Const.BUY.equals(trade.getBuyOrSell())) {
            //如果是buy，现有数量加上交易数量
            count = count + trade.getQuantity();
        } else if(Const.SELL.equals(trade.getBuyOrSell())) {
            //如果是sell，现有数量减去交易数量
            count = count - trade.getQuantity();
        }
        return count;
    }

    /**
     * 校验数据的合法性
     * 如果一笔交易(Trade)在数据库中没有记录，而该笔交易的option不为INSERT，则抛出异常
     * @param tradeList 待校验的交易列表
     * @return
     */
    private Map<String, String> validateTrade(List<Trade> tradeList){
        int tradeId;
        String securityCode;
        Collections.sort(tradeList);
        WebResult wr = new WebResult();
        Map<String, String> result = null;

        for (int i = 0; i < tradeList.size(); i++) {
            Trade trade = tradeList.get(i);
            tradeId = trade.getTradeId();
            String option = trade.getOption();

            //查询交易的历史记录
            QueryWrapper queryWrapper = new QueryWrapper();
            // 查询的条件
            queryWrapper.eq("trade_id", tradeId);
            List<Trade> oldTrades = super.queryList(queryWrapper);

            //如果没有交易历史记录，且操作为UPDATE 或 CANCEL，抛出异常
            if(oldTrades==null || (oldTrades!=null && oldTrades.size()==0)) {
                if(!Const.OPTION_INSERT.equals(option)) {
                    LOGGER.info(Const.NON_START_WITH_INSERT);
                    result = wr.setMsg(trade.getSecurityCode(), Const.NON_START_WITH_INSERT);
                    return result;
                }
            }
        }
        return result;
    }
}
