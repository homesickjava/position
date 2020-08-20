package com.interview.equitypositions.service;

import com.interview.equitypositions.pojo.Position;
import org.springframework.stereotype.Service;

@Service
public interface PositionService {

    Position getCount(int tradeId);
}
