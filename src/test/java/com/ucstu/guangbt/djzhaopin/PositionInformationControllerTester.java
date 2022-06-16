package com.ucstu.guangbt.djzhaopin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;

import jakarta.annotation.Resource;

@SpringBootTest(classes = { DjzhaopinApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PositionInformationControllerTester {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private MockMvc mockMvc;

    @Test
    @DisplayName("一键测试")
    public void testAll() throws Exception {
    }

    @Test
    @DisplayName("测试添加职位信息")
    public void testAddPositionInformation() throws Exception {
        PositionInformation positionInformation = new PositionInformation();
    }
}
