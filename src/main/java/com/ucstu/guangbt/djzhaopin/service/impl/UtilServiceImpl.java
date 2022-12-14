package com.ucstu.guangbt.djzhaopin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.util.CityInformation;
import com.ucstu.guangbt.djzhaopin.model.util.DirectionTag;
import com.ucstu.guangbt.djzhaopin.model.util.FilterInformation;
import com.ucstu.guangbt.djzhaopin.model.util.areainformation.AreaInformation;
import com.ucstu.guangbt.djzhaopin.model.util.positiontype.Direction;
import com.ucstu.guangbt.djzhaopin.model.util.positiontype.PositionType;
import com.ucstu.guangbt.djzhaopin.repository.MessageRecordRepository;
import com.ucstu.guangbt.djzhaopin.service.UtilService;
import com.ucstu.guangbt.djzhaopin.utils.EmailMessageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UtilServiceImpl implements UtilService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private EmailMessageUtil emailMessageUtil;

    @Resource
    private RedisTemplate<String, String> verificationCodeTemplate;

    @Resource
    private MessageRecordRepository messageRecordRepository;

    @Override
    public ServiceToControllerBody<List<AreaInformation>> getAreaInformations(String cityName) {
        ServiceToControllerBody<List<AreaInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<AreaInformation> areaInformations = new ArrayList<>();
        List<String> cityList = new ArrayList<>();
        cityList.add("?????????");
        cityList.add("??????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("??????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("??????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        cityList = new ArrayList<>();
        cityList.add("??????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("?????????");
        cityList.add("????????????");
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        areaInformations.add(new AreaInformation(cityList, "?????????"));
        return serviceToControllerBody.success(areaInformations);
    }

    @Override
    public ServiceToControllerBody<FilterInformation> getFilterInformation() {
        ServiceToControllerBody<FilterInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        FilterInformation filterInformation = new FilterInformation();
        List<String> occupationalBreakdown = new ArrayList<>();
        occupationalBreakdown.add("???????????????");
        occupationalBreakdown.add("???????????????");
        occupationalBreakdown.add("???????????????");
        occupationalBreakdown.add("?????????????????????");
        occupationalBreakdown.add("?????????????????????");
        occupationalBreakdown.add("?????????????????????");
        filterInformation.setOccupationalBreakdown(occupationalBreakdown);
        List<String> expectedSalary = new ArrayList<>();
        expectedSalary.add("2000??????");
        expectedSalary.add("2000-3000");
        expectedSalary.add("3000-4000");
        expectedSalary.add("4000-5000");
        expectedSalary.add("5000-6000");
        filterInformation.setExpectedSalary(expectedSalary);
        List<String> workExperience = new ArrayList<>();
        workExperience.add("????????????");
        workExperience.add("??????/??????");
        workExperience.add("3????????????");
        workExperience.add("3-5???");
        workExperience.add("5-10???");
        workExperience.add("10?????????");
        filterInformation.setWorkExperience(workExperience);
        List<String> education = new ArrayList<>();
        education.add("?????????");
        education.add("??????");
        education.add("??????");
        education.add("??????");
        education.add("??????");
        filterInformation.setEducation(education);
        List<String> natureWork = new ArrayList<>();
        natureWork.add("??????");
        natureWork.add("??????");
        natureWork.add("??????");
        filterInformation.setNatureWork(natureWork);
        List<String> companySize = new ArrayList<>();
        companySize.add("??????15???");
        companySize.add("15-50???");
        companySize.add("50-150???");
        companySize.add("150-500???");
        companySize.add("500-2000???");
        companySize.add("2000??????");
        filterInformation.setCompanySize(companySize);
        List<String> financingStage = new ArrayList<>();
        financingStage.add("?????????");
        financingStage.add("?????????");
        financingStage.add("A???");
        financingStage.add("B???");
        financingStage.add("C???");
        financingStage.add("D????????????");
        financingStage.add("????????????");
        financingStage.add("???????????????");
        filterInformation.setFinancingStage(financingStage);
        List<String> industryField = new ArrayList<>();
        industryField.add("??????|??????|??????");
        industryField.add("??????|??????|??????");
        industryField.add("??????|??????");
        industryField.add("??????|BI|??????");
        industryField.add("??????");
        filterInformation.setIndustryField(industryField);
        return serviceToControllerBody.success(filterInformation);
    }

    @Override
    public ServiceToControllerBody<List<PositionType>> getPositionTypes() {
        ServiceToControllerBody<List<PositionType>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<PositionType> positionTypes = new ArrayList<>();
        List<Direction> directions = new ArrayList<>();
        List<String> positions = new ArrayList<>();
        positions.add("JAVA?????????");
        positions.add("???????????????");
        positions.add("???????????????");
        positions.add("??????????????????");
        positions.add("???????????????");
        positions.add("???????????????");
        positions.add("???????????????");
        positions.add("????????????????????????");
        positions.add("????????????????????????");
        positions.add("?????????");
        directions.add(new Direction("????????????", positions));
        positions = new ArrayList<>();
        positions.add("????????????|??????");
        positions.add("????????????");
        positions.add("CTO|CIO");
        directions.add(new Direction("??????????????????", positions));
        positions = new ArrayList<>();
        positions.add("???????????????");
        positions.add("???????????????");
        positions.add("????????????");
        directions.add(new Direction("??????", positions));
        positions = new ArrayList<>();
        positions.add("????????????|??????");
        positions.add("????????????");
        directions.add(new Direction("????????????", positions));
        positions = new ArrayList<>();
        positions.add("????????????");
        positions.add("??????????????????");
        positions.add("????????????");
        positions.add("????????????");
        directions.add(new Direction("????????????|??????", positions));
        directions.add(new Direction("??????|????????????", new ArrayList<>()));
        directions.add(new Direction("??????|DBA??????", new ArrayList<>()));
        positionTypes.add(new PositionType("??????|??????|??????", directions));
        positionTypes.add(new PositionType("??????|??????|??????", new ArrayList<>()));
        positionTypes.add(new PositionType("??????|??????", new ArrayList<>()));
        positionTypes.add(new PositionType("??????|BI|??????", new ArrayList<>()));
        positionTypes.add(new PositionType("??????", new ArrayList<>()));
        return serviceToControllerBody.success(positionTypes);
    }

    @Override
    public ServiceToControllerBody<List<DirectionTag>> getDirectionTags(String positionName) {
        ServiceToControllerBody<List<DirectionTag>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<DirectionTag> directionTags = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("C??????");
        tags.add("C++");
        tags.add("C#");
        tags.add("PHP");
        tags.add("Python");
        tags.add("JavaScript");
        tags.add("TypeScript");
        tags.add("HTML");
        tags.add("CSS");
        tags.add("Objective-C");
        tags.add("Swift");
        tags.add("Go");
        tags.add("Ruby");
        tags.add("SQL");
        tags.add("Shell");
        tags.add("Perl");
        tags.add("R");
        tags.add("MATLAB");
        tags.add("VB");
        tags.add("Delphi");
        tags.add("Kotlin");
        tags.add("Scala");
        tags.add("Groovy");
        tags.add("Rust");
        tags.add("Erlang");
        directionTags.add(new DirectionTag("????????????", tags));
        tags = new ArrayList<>();
        tags.add("VC");
        tags.add("VC++");
        tags.add("VC.Net");
        tags.add("STL");
        tags.add("Socket");
        tags.add(".NET");
        tags.add("WPF");
        tags.add("Django");
        tags.add("Flask");
        tags.add("Spring");
        tags.add("Struts");
        tags.add("Hibernate");
        tags.add("Node.js");
        tags.add("Express");
        tags.add("Spark");
        directionTags.add(new DirectionTag("????????????", tags));
        tags = new ArrayList<>();
        tags.add("Linux");
        tags.add("Windows");
        tags.add("MacOS");
        tags.add("Android");
        tags.add("iOS");
        tags.add("Windows Phone");
        tags.add("Ubuntu");
        tags.add("CentOS");
        tags.add("Debian");
        tags.add("RedHat");
        tags.add("Oracle");
        directionTags.add(new DirectionTag("????????????", tags));
        tags = new ArrayList<>();
        tags.add("MySQL");
        tags.add("Oracle");
        tags.add("SQLite");
        tags.add("MongoDB");
        tags.add("Redis");
        tags.add("PostgreSQL");
        tags.add("MSSQL");
        tags.add("SQL Server");
        tags.add("HBase");
        tags.add("Cassandra");
        tags.add("Apache Hive");
        tags.add("Apache Drill");
        directionTags.add(new DirectionTag("?????????", tags));
        return serviceToControllerBody.success(directionTags);
    }

    @Override
    public ServiceToControllerBody<List<CityInformation>> getCityInformations() {
        ServiceToControllerBody<List<CityInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<CityInformation> cityInformations = new ArrayList<>();
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("????????????");
                add("????????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("????????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
                add("?????????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("?????????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
                add("?????????");
                add("??????");
                add("?????????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("????????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
                add("?????????");
                add("??????");
                add("??????");
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("????????????");
                add("????????????");
                add("?????????");
                add("??????");
                add("??????");
                add("????????????");
                add("????????????");
                add("?????????");
                add("????????????");
                add("??????");
                add("??????");
                add("??????");
                add("??????");
                add("?????????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
            }
        }));
        cityInformations.add(new CityInformation("??????", new ArrayList<>() {
            {
                add("??????");
            }
        }));
        return serviceToControllerBody.success(cityInformations);
    }

    @Override
    public ServiceToControllerBody<List<UUID>> getRecommendations(Pageable pageable) {
        ServiceToControllerBody<List<UUID>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<UUID> uuids = new ArrayList<>();
        uuids.add(UUID.fromString("a9f8f8f8-f8f8-f8f8-f8f8-f8f8f8f8f8f8"));
        uuids.add(UUID.fromString("a9f8f8f8-f8f8-f8f8-f8f8-f8f8f8f8f8f8"));
        uuids.add(UUID.fromString("a9f8f8f8-f8f8-f8f8-f8f8-f8f8f8f8f8f8"));
        uuids.add(UUID.fromString("a9f8f8f8-f8f8-f8f8-f8f8-f8f8f8f8f8f8"));
        return serviceToControllerBody.success(uuids);
    }

    @Override
    public ServiceToControllerBody<List<MessageRecord>> getMessageRecords(Pageable pageable) {
        ServiceToControllerBody<List<MessageRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<MessageRecord> messageRecords = messageRecordRepository.findAll(pageable);
        if (messageRecords.hasContent()) {
            return serviceToControllerBody.success(messageRecords.getContent());
        }
        return serviceToControllerBody.success(new ArrayList<>());
    }

    @Override
    public ServiceToControllerBody<String> getVerificationCode(String email) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        String verificationCode = String.valueOf(new Random().nextInt(8999) + 1000);
        emailMessageUtil.sendEmail(email, "????????????-?????????", "?????????????????????" + verificationCode + "?????????5??????????????????");
        // ??????????????????redis,?????????????????????5??????
        verificationCodeTemplate.opsForValue().set(email, verificationCode, 5, TimeUnit.MINUTES);
        return serviceToControllerBody.success("???????????????????????????????????????");
    }

    @Override
    public ServiceToControllerBody<String> getNewVersion() {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        String version = "1.0.0";
        return serviceToControllerBody.success(version);
    }

    @Override
    public ServiceToControllerBody<String> uploadFile(MultipartFile file) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        if (file.isEmpty()) {
            return serviceToControllerBody.error("file", "????????????", null);
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + suffixName;
        File dest = new File("/var/www/html/file/" + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("??????????????????:{}", e);
            return serviceToControllerBody.error("file", "??????????????????", file.getOriginalFilename());
        }
        return serviceToControllerBody.success("/file/" + fileName);
    }

    @Override
    public ServiceToControllerBody<String> uploadAvatar(MultipartFile avatar) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        if (avatar.isEmpty()) {
            return serviceToControllerBody.error("file", "????????????", null);
        }
        String avatarName = avatar.getOriginalFilename();
        String suffixName = avatarName.substring(avatarName.lastIndexOf("."));
        avatarName = UUID.randomUUID() + suffixName;
        File dest = new File("/var/www/html/avatar/" + avatarName);
        try {
            avatar.transferTo(dest);
        } catch (IOException e) {
            log.error("??????????????????:{}", e);
            return serviceToControllerBody.error("avatar", "??????????????????", avatar.getOriginalFilename());
        }
        return serviceToControllerBody.success("/avatar/" + avatarName);
    }

}
