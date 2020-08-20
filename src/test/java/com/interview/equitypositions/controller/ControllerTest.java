package com.interview.equitypositions.controller;

import com.interview.equitypositions.service.EPService;
import com.interview.equitypositions.service.PositionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(EPController.class)
public class ControllerTest {

    @MockBean
    private PositionService positionService;

    @MockBean
    private EPService epService;

    @Before
    public void setup() {

    }

    @Test
    public void testInsertTrade() {

    }
}
