package com.ucstu.guangbt.djzhaopin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
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
                testUpdatePassword();
                testForgetPassword();
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
                                // .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Bad Request"))
                                .andReturn();
        }

        @Test
        @DisplayName("测试登录账号")
        public void testLoginAccount() throws Exception {
                LoginAccountRequest request = new LoginAccountRequest();
                request.setUserName("3074291765@qq.com");
                request.setPassword("123456");
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/accountInfos/login")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(
                                                                request))
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers
                                                .jsonPath("body.accountInformation.accountInformationId").exists())
                                .andReturn();
        }

        @Test
        @DisplayName("测试修改密码")
        public void testUpdatePassword() throws Exception {
                ChangePasswordRequest request = new ChangePasswordRequest();
                request.setPassword("123456");
                request.setVerificationCode("2345");
                mockMvc.perform(
                                MockMvcRequestBuilders.put("/accountInfos/0253acd4-2808-40bf-8798-a27f2e47c2b3")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(
                                                                request))
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Bad Request"))
                                .andReturn();
        }

        @Test
        @DisplayName("测试遗忘密码")
        public void testForgetPassword() throws Exception {
                ForgetPasswordRequest request = new ForgetPasswordRequest();
                request.setUserName("3074291765@qq.com");
                request.setVerificationCode("2345");
                request.setPassword("1234566");
                mockMvc.perform(
                                MockMvcRequestBuilders.put("/accountInfos/forget")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(
                                                                new LoginAccountRequest()))
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Bad Request"))
                                .andReturn();
        }
}
