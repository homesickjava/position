package com.interview.equitypositions.service;

import com.interview.equitypositions.pojo.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EPService {

    Map<String, String> insert(List<Trade> tradeList);
}
