package com.ucstu.guangbt.djzhaopin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

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
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;

@SpringBootTest(classes = { DjzhaopinApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CompanyInformationControllerTester {

        @Resource
        private ObjectMapper objectMapper;

        @Resource
        private MockMvc mockMvc;

        @Test
        @DisplayName("测试全部方法")
        public void testAll() throws Exception {
                testCreateCompany();
                testDeleteCompany();
                testUpdateCompany();
                testGetCompany();
                testGetHistory();
        }

        @Test
        @DisplayName("测试创建公司")
        public void testCreateCompany() throws Exception {
                CompanyInformation companyInformation = new CompanyInformation();
                List<AttentionRecord> attentionRecords = new ArrayList<>();
                attentionRecords.add(new AttentionRecord());
                Set<String> tags = new HashSet<>();
                tags.add("tag1");
                Map<String, Float> map = new HashMap<>();
                map.put("A", 1.0f);
                map.put("B", 2.0f);
                List<HrInformation> hrInformations = null;
                UUID uuid = UUID.randomUUID();
                companyInformation.setAbout("about")
                                .setAddress("address")
                                .setAttentionRecords(attentionRecords)
                                .setBenefits(tags)
                                .setCityName("cityName")
                                .setCompanyInformationId(uuid)
                                .setCompanyName("companyName")
                                .setComprehensionName("comprehensionName")
                                .setEstablishmentTime("establishmentTime")
                                .setFinancingStage(200).setFullName("fullName").setHrInformations(hrInformations)
                                .setLatitude(20.4f)
                                .setLegalRepresentative("legalRepresentative")
                                .setLocation(map);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/companyInfos")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(
                                                                companyInformation))
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                                .andReturn();
        }

        @Test
        @DisplayName("测试删除公司")
        public void testDeleteCompany() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders
                                                .delete("/companyInfos/{id}", "0253acd4-2808-40bf-8798-a27f2e47c2b3")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.jsonPath("message").exists())
                                .andReturn();
        }

        @Test
        @DisplayName("测试更新公司")
        public void testUpdateCompany() throws Exception {
                CompanyInformation companyInformation = new CompanyInformation();
                List<AttentionRecord> attentionRecords = new ArrayList<>();
                attentionRecords.add(new AttentionRecord());
                Set<String> tags = new HashSet<>();
                tags.add("tag1");
                Map<String, Float> map = new HashMap<>();
                map.put("A", 1.0f);
                map.put("B", 2.0f);
                List<HrInformation> hrInformations = null;
                UUID uuid = UUID.randomUUID();
                companyInformation.setAbout("about")
                                .setAddress("address")
                                .setAttentionRecords(attentionRecords)
                                .setBenefits(tags)
                                .setCityName("cityName")
                                .setCompanyInformationId(uuid)
                                .setCompanyName("companyName")
                                .setComprehensionName("comprehensionName")
                                .setEstablishmentTime("establishmentTime")
                                .setFinancingStage(200).setFullName("fullName").setHrInformations(hrInformations)
                                .setLatitude(20.4f)
                                .setLegalRepresentative("legalRepresentative")
                                .setLocation(map);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/companyInfos/{id}", "0253acd4-2808-40bf-8798-a27f2e47c2b3")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(
                                                                companyInformation))
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                                .andReturn();
        }

        @Test
        @DisplayName("测试获取公司")
        public void testGetCompany() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/companyInfos/{id}", "0253acd4-2808-40bf-8798-a27f2e47c2b3")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                                .andReturn();
        }

        @Test
        @DisplayName("测试查看历史大记录")
        public void testGetHistory() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders
                                                .get("/companyInfos/{companyInfoId}/bigData",
                                                                "0253acd4-2808-40bf-8798-a27f2e47c2b3")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                                .andReturn();
        }
}
