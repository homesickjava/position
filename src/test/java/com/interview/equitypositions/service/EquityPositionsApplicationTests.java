package com.interview.equitypositions.service;

import com.interview.equitypositions.controller.EPController;
import com.interview.equitypositions.mapper.EPMapper;
import com.interview.equitypositions.mapper.PositionMapper;
import com.interview.equitypositions.pojo.Trade;
import com.interview.equitypositions.service.EPService;
import com.interview.equitypositions.service.PositionService;
import com.interview.equitypositions.util.Const;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class EquityPositionsApplicationTests {

    @Autowired
    private EPService epService;

    @Autowired
    private PositionService positionService;

    @MockBean
    private EPMapper epMapper;

    @MockBean
    private PositionMapper positionMapper;

    @Test
    void contextLoads() {
    }

    @Before
    public void test() {
        System.out.println("start =======================");
    }

    @Test
    @Rollback
    public void validateTradeTest() {
        List<Trade> tradelist = new ArrayList<>();
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setSecurityCode("REL");
        trade.setQuantity(50);
        trade.setOption("INSERT");
        trade.setBuyOrSell("Buy");
        tradelist.add(trade);
        Trade trade2 = new Trade();
        trade2.setTradeId(2);
        trade2.setSecurityCode("ITC");
        trade2.setQuantity(40);
        trade2.setOption("UPDATE");
        trade2.setBuyOrSell("Buy");
        tradelist.add(trade2);

        Map<String, String> result = epService.insert(tradelist);
        assert result.get("REL").equals("+50");
        assert result.get("ITC").equals(Const.NON_START_WITH_INSERT);
    }

    @Test
    @Rollback
    public void insertTradeTest() {
        List<Trade> tradelist = new ArrayList<>();
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setSecurityCode("REL");
        trade.setQuantity(50);
        trade.setOption("INSERT");
        trade.setBuyOrSell("Buy");
        tradelist.add(trade);
        Trade trade2 = new Trade();
        trade2.setTradeId(2);
        trade2.setSecurityCode("ITC");
        trade2.setQuantity(40);
        trade2.setOption("INSERT");
        trade2.setBuyOrSell("Sell");
        tradelist.add(trade2);

        Map<String, String> result = epService.insert(tradelist);
        assert result.get("REL").equals("+50");
        assert result.get("ITC").equals("-40");
    }


}
