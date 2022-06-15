package com.ucstu.guangbt.djzhaopin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;

import jakarta.annotation.Resource;

@SpringBootTest(classes = { DjzhaopinApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTester {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testAll() throws Exception {
        testRegisterAccount();
        testLoginAccount();
    }

    @Test
    @DisplayName("测试注册账号")
    public void testRegisterAccount() throws Exception {
        RegisterAccountRequest request = new RegisterAccountRequest();
        request.setAccountType(1);
        request.setUserName("2219454275@qq.com");
        request.setPassword("123456");
        request.setVerificationCode("2345");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/accountInfos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("测试登录账号")
    public void testLoginAccount() throws Exception {
        LoginAccountRequest request = new LoginAccountRequest();
        request.setUserName("2219454275@qq.com");
        request.setPassword("123456");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/accountInfos/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

}
