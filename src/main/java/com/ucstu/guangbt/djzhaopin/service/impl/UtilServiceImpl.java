package com.ucstu.guangbt.djzhaopin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

@Service
public class UtilServiceImpl implements UtilService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MessageRecordRepository messageRecordRepository;

    @Override
    public ServiceToControllerBody<List<AreaInformation>> getAreaInformations(String cityName) {
        ServiceToControllerBody<List<AreaInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<AreaInformation> areaInformations = new ArrayList<>();
        List<String> cityList = new ArrayList<>();
        cityList.add("紫荆");
        cityList.add("鹭岛国际");
        cityList.add("红牌楼");
        cityList.add("簇桥");
        cityList.add("五大花园");
        cityList.add("航空路");
        cityList.add("火车南站");
        cityList.add("红瓦寺");
        cityList.add("磨子桥");
        cityList.add("科华中路王府井");
        cityList.add("桐梓林");
        cityList.add("新南门");
        cityList.add("华西坝");
        cityList.add("省体育馆");
        cityList.add("新会展中心");
        cityList.add("天府长城");
        cityList.add("九方购物中心");
        cityList.add("孵化园");
        cityList.add("外双楠");
        cityList.add("龙腾路");
        cityList.add("大石路");
        cityList.add("高升桥");
        cityList.add("石羊场");
        cityList.add("高朋大道");
        cityList.add("清水河公园");
        cityList.add("伊藤/世豪广");
        cityList.add("复城国际广场");
        cityList.add("环球中心");
        cityList.add("金花");
        cityList.add("保利花园");
        cityList.add("机投");
        cityList.add("玉林/芳草");
        cityList.add("武侯祠");
        cityList.add("银泰城");
        cityList.add("龙湖金楠天街");
        cityList.add("汇锦城/铁像");
        cityList.add("少陵路");
        cityList.add("武侯万达");
        cityList.add("跳伞塔");
        cityList.add("中粮大悦城");
        cityList.add("新街里");
        cityList.add("双楠");
        cityList.add("高新区");
        cityList.add("耍都");
        cityList.add("肖家河");
        cityList.add("神仙树");
        areaInformations.add(new AreaInformation(cityList, "武侯区"));
        return serviceToControllerBody.success(areaInformations);
    }

    @Override
    public ServiceToControllerBody<FilterInformation> getFilterInformation() {
        ServiceToControllerBody<FilterInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        FilterInformation filterInformation = new FilterInformation();
        List<String> occupationalBreakdown = new ArrayList<>();
        occupationalBreakdown.add("计算机软件");
        occupationalBreakdown.add("计算机硬件");
        occupationalBreakdown.add("计算机网络");
        occupationalBreakdown.add("计算机系统集成");
        occupationalBreakdown.add("计算机应用技术");
        occupationalBreakdown.add("计算机网络技术");
        filterInformation.setOccupationalBreakdown(occupationalBreakdown);
        List<String> expectedSalary = new ArrayList<>();
        expectedSalary.add("2000以下");
        expectedSalary.add("2000-3000");
        expectedSalary.add("3000-4000");
        expectedSalary.add("4000-5000");
        expectedSalary.add("5000-6000");
        filterInformation.setExpectedSalary(expectedSalary);
        List<String> workExperience = new ArrayList<>();
        workExperience.add("无");
        workExperience.add("1年以下");
        workExperience.add("1-3年");
        workExperience.add("3-5年");
        workExperience.add("5-10年");
        workExperience.add("10年以上");
        filterInformation.setWorkExperience(workExperience);
        List<String> education = new ArrayList<>();
        education.add("大专");
        education.add("本科");
        education.add("硕士");
        education.add("博士");
        filterInformation.setEducation(education);
        List<String> natureWork = new ArrayList<>();
        natureWork.add("全职");
        natureWork.add("兼职");
        natureWork.add("实习");
        natureWork.add("其他");
        filterInformation.setNatureWork(natureWork);
        List<String> companySize = new ArrayList<>();
        companySize.add("50人以下");
        companySize.add("50-100人");
        companySize.add("100-500人");
        companySize.add("500-1000人");
        companySize.add("1000人以上");
        filterInformation.setCompanySize(companySize);
        List<String> financingStage = new ArrayList<>();
        financingStage.add("未融资");
        financingStage.add("天使轮");
        financingStage.add("A轮");
        financingStage.add("B轮");
        financingStage.add("C轮");
        financingStage.add("D轮及以上");
        financingStage.add("上市公司");
        financingStage.add("不需要融资");
        filterInformation.setFinancingStage(financingStage);
        List<String> industryField = new ArrayList<>();
        industryField.add("移动互联网");
        industryField.add("电子商务");
        industryField.add("企业服务");
        industryField.add("O2O");
        industryField.add("教育");
        industryField.add("金融");
        industryField.add("游戏");
        industryField.add("文化娱乐");
        industryField.add("硬件");
        industryField.add("社交");
        industryField.add("旅游");
        industryField.add("生活服务");
        filterInformation.setIndustryField(industryField);
        return serviceToControllerBody.success(filterInformation);
    }

    @Override
    public ServiceToControllerBody<List<PositionType>> getPositionTypes() {
        ServiceToControllerBody<List<PositionType>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<PositionType> positionTypes = new ArrayList<>();
        List<Direction> directions = new ArrayList<>();
        List<String> positions = new ArrayList<>();
        positions.add("Java");
        positions.add("C++");
        positions.add("C#");
        positions.add("Python");
        positions.add("PHP");
        positions.add("Android");
        directions.add(new Direction("计算机软件", positions));
        positions = new ArrayList<>();
        positions.add("路由器");
        positions.add("交换机");
        positions.add("网卡");
        positions.add("硬件");
        directions.add(new Direction("计算机网络", positions));
        positionTypes.add(new PositionType("计算机", directions));
        directions = new ArrayList<>();
        positions = new ArrayList<>();
        positions.add("软件工程师");
        positions.add("系统工程师");
        positions.add("网络工程师");
        positions.add("系统管理员");
        positions.add("网络安全工程师");
        positions.add("系统架构设计师");
        positions.add("系统分析师");
        positions.add("系统测试");
        positions.add("系统技术员");
        directions.add(new Direction("计算机软件", positions));
        positions = new ArrayList<>();
        positions.add("网络工程师");
        positions.add("网络安全工程师");
        positions.add("系统工程师");
        positions.add("系统管理员");
        directions.add(new Direction("计算机网络", positions));
        positionTypes.add(new PositionType("其它", directions));
        return serviceToControllerBody.success(positionTypes);
    }

    @Override
    public ServiceToControllerBody<List<DirectionTag>> getDirectionTags(String positionName) {
        ServiceToControllerBody<List<DirectionTag>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<DirectionTag> directionTags = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("C++");
        tags.add("C#");
        tags.add("Python");
        tags.add("PHP");
        tags.add("Android");
        directionTags.add(new DirectionTag("计算机软件", tags));
        tags = new ArrayList<>();
        tags.add("路由器");
        tags.add("交换机");
        tags.add("网卡");
        tags.add("硬件");
        directionTags.add(new DirectionTag("计算机网络", tags));
        return serviceToControllerBody.success(directionTags);
    }

    @Override
    public ServiceToControllerBody<List<CityInformation>> getCityInformations() {
        ServiceToControllerBody<List<CityInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<CityInformation> cityInformations = new ArrayList<>();
        cityInformations.add(new CityInformation("北京", new ArrayList<>() {
            {
                add("东城区");
                add("西城区");
                add("朝阳区");
                add("丰台区");
                add("石景山区");
                add("海淀区");
                add("门头沟区");
                add("房山区");
                add("通州区");
                add("顺义区");
                add("昌平区");
                add("大兴区");
                add("怀柔区");
                add("平谷区");
                add("密云县");
                add("延庆县");
            }
        }));
        cityInformations.add(new CityInformation("上海", new ArrayList<>() {
            {
                add("黄浦区");
                add("卢湾区");
                add("徐汇区");
                add("长宁区");
                add("静安区");
                add("普陀区");
                add("闸北区");
                add("虹口区");
                add("杨浦区");
                add("闵行区");
                add("宝山区");
                add("嘉定区");
                add("浦东新区");
                add("金山区");
                add("松江区");
                add("青浦区");
                add("南汇区");
                add("奉贤区");
                add("崇明县");
            }
        }));
        cityInformations.add(new CityInformation("广州", new ArrayList<>() {
            {
                add("荔湾区");
                add("越秀区");
                add("海珠区");
                add("天河区");
                add("白云区");
                add("黄埔区");
                add("番禺区");
                add("花都区");
                add("南沙区");
                add("萝岗区");
                add("增城市");
                add("从化市");
            }
        }));
        cityInformations.add(new CityInformation("湖北", new ArrayList<>() {
            {
                add("武汉");
                add("黄石");
                add("十堰");
                add("宜昌");
                add("襄阳");
                add("鄂州");
                add("荆门");
                add("孝感");
                add("荆州");
                add("黄冈");
                add("咸宁");
                add("随州");
                add("恩施");
                add("仙桃");
                add("潜江");
                add("天门");
                add("神农架");
            }
        }));
        cityInformations.add(new CityInformation("湖南", new ArrayList<>() {
            {
                add("长沙");
                add("株洲");
                add("湘潭");
                add("衡阳");
                add("邵阳");
                add("岳阳");
                add("常德");
                add("张家界");
                add("益阳");
                add("郴州");
                add("永州");
                add("怀化");
                add("娄底");
                add("湘西");
            }
        }));
        cityInformations.add(new CityInformation("广东", new ArrayList<>() {
            {
                add("广州");
                add("韶关");
                add("深圳");
                add("珠海");
                add("汕头");
                add("佛山");
                add("江门");
                add("湛江");
                add("茂名");
                add("肇庆");
                add("惠州");
                add("梅州");
                add("汕尾");
                add("河源");
                add("阳江");
                add("清远");
                add("东莞");
                add("中山");
                add("潮州");
                add("揭阳");
                add("云浮");
            }
        }));
        cityInformations.add(new CityInformation("广西", new ArrayList<>() {
            {
                add("南宁");
                add("柳州");
                add("桂林");
                add("梧州");
                add("北海");
                add("防城港");
                add("钦州");
                add("贵港");
                add("玉林");
                add("百色");
                add("贺州");
                add("河池");
                add("来宾");
                add("崇左");
            }
        }));
        cityInformations.add(new CityInformation("海南", new ArrayList<>() {
            {
                add("海口");
                add("三亚");
                add("五指山");
                add("琼海");
                add("儋州");
                add("文昌");
                add("万宁");
                add("东方");
                add("定安");
                add("屯昌");
                add("澄迈");
                add("临高");
                add("白沙");
                add("昌江");
                add("乐东");
                add("陵水");
                add("保亭");
                add("琼中");
            }
        }));
        cityInformations.add(new CityInformation("四川", new ArrayList<>() {
            {
                add("成都");
                add("自贡");
                add("攀枝花");
                add("泸州");
                add("德阳");
                add("绵阳");
                add("广元");
                add("遂宁");
                add("内江");
                add("乐山");
                add("南充");
                add("眉山");
                add("宜宾");
                add("广安");
                add("达州");
                add("雅安");
                add("巴中");
                add("资阳");
                add("阿坝");
                add("甘孜");
                add("凉山");
            }
        }));
        cityInformations.add(new CityInformation("贵州", new ArrayList<>() {
            {
                add("贵阳");
                add("六盘水");
                add("遵义");
                add("安顺");
                add("铜仁");
                add("黔西南");
                add("毕节");
                add("黔东南");
                add("黔南");
            }
        }));
        cityInformations.add(new CityInformation("云南", new ArrayList<>() {
            {
                add("昆明");
                add("曲靖");
                add("玉溪");
                add("保山");
                add("昭通");
                add("丽江");
                add("普洱");
                add("临沧");
                add("楚雄");
                add("红河");
                add("文山");
                add("西双版纳");
                add("大理");
                add("德宏");
                add("怒江");
                add("迪庆");
            }
        }));
        cityInformations.add(new CityInformation("西藏", new ArrayList<>() {
            {
                add("拉萨");
                add("昌都");
                add("山南");
                add("日喀则");
                add("那曲");
                add("阿里");
                add("林芝");
            }
        }));
        cityInformations.add(new CityInformation("陕西", new ArrayList<>() {
            {
                add("西安");
                add("铜川");
                add("宝鸡");
                add("咸阳");
                add("渭南");
                add("延安");
                add("汉中");
                add("榆林");
                add("安康");
                add("商洛");
            }
        }));
        cityInformations.add(new CityInformation("甘肃", new ArrayList<>() {
            {
                add("兰州");
                add("嘉峪关");
                add("金昌");
                add("白银");
                add("天水");
                add("武威");
                add("张掖");
                add("平凉");
                add("酒泉");
                add("庆阳");
                add("定西");
                add("陇南");
                add("临夏");
                add("甘南");
            }
        }));
        cityInformations.add(new CityInformation("青海", new ArrayList<>() {
            {
                add("西宁");
                add("海东");
                add("海北");
                add("黄南");
                add("海南");
                add("果洛");
                add("玉树");
                add("海西");
            }
        }));
        cityInformations.add(new CityInformation("宁夏", new ArrayList<>() {
            {
                add("银川");
                add("石嘴山");
                add("吴忠");
                add("固原");
                add("中卫");
            }
        }));
        cityInformations.add(new CityInformation("新疆", new ArrayList<>() {
            {
                add("乌鲁木齐");
                add("克拉玛依");
                add("吐鲁番");
                add("哈密");
                add("昌吉");
                add("博尔塔拉");
                add("巴音郭楞");
                add("阿克苏");
                add("克孜勒苏");
                add("喀什");
                add("和田");
                add("伊犁");
                add("塔城");
                add("阿勒泰");
            }
        }));
        cityInformations.add(new CityInformation("香港", new ArrayList<>() {
            {
                add("香港");
            }
        }));
        cityInformations.add(new CityInformation("澳门", new ArrayList<>() {
            {
                add("澳门");
            }
        }));
        cityInformations.add(new CityInformation("台湾", new ArrayList<>() {
            {
                add("台湾");
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
    public ServiceToControllerBody<String> getVerificationCode(String phoneNumber) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        String verificationCode = "2345";
        return serviceToControllerBody.success(verificationCode);
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
        File newFile = new File("/var/www/html/file/" + file.getOriginalFilename());
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            return serviceToControllerBody.error("file", "文件上传失败", file.getOriginalFilename());
        }
        return serviceToControllerBody.success("/file/" + file.getOriginalFilename());
    }

    @Override
    public ServiceToControllerBody<String> uploadAvatar(MultipartFile avatar) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        File newAvatar = new File("/var/www/html/avatar/" + avatar.getOriginalFilename());
        try {
            avatar.transferTo(newAvatar);
        } catch (IOException e) {
            return serviceToControllerBody.error("avatar", "头像上传失败", avatar.getOriginalFilename());
        }
        return serviceToControllerBody.success("/avatar/" + avatar.getOriginalFilename());
    }

}
