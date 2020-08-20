package com.interview.equitypositions.controller;

import com.interview.equitypositions.pojo.Trade;
import com.interview.equitypositions.service.EPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ep")
public class EPController {

    @Autowired
    private EPService epService;

    @ResponseBody
    @GetMapping("/insertTrade")
    public Map<String, String > insertTrade(List<Trade> tradeList) {
        return epService.insert(tradeList);
    }
}
