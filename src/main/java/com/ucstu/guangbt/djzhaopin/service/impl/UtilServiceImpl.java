package com.ucstu.guangbt.djzhaopin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
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
        cityList.add("西北旺");
        cityList.add("上地");
        cityList.add("双榆树");
        cityList.add("中关村");
        cityList.add("五道口");
        cityList.add("马连洼");
        cityList.add("清河");
        cityList.add("西二旗");
        cityList.add("万泉河");
        cityList.add("北下关");
        cityList.add("西三旗");
        cityList.add("杜丹园");
        cityList.add("万柳");
        cityList.add("学院路");
        cityList.add("知春路");
        cityList.add("苏州街");
        areaInformations.add(new AreaInformation(cityList, "海淀区"));
        cityList = new ArrayList<>();
        cityList.add("望京");
        cityList.add("大山子");
        cityList.add("来广营");
        cityList.add("酒仙桥");
        cityList.add("建外大街");
        areaInformations.add(new AreaInformation(cityList, "朝阳区"));
        areaInformations.add(new AreaInformation(cityList, "东城区"));
        areaInformations.add(new AreaInformation(cityList, "西城区"));
        areaInformations.add(new AreaInformation(cityList, "大丰区"));
        areaInformations.add(new AreaInformation(cityList, "丰台区"));
        areaInformations.add(new AreaInformation(cityList, "昌平区"));
        areaInformations.add(new AreaInformation(cityList, "通州区"));
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
        workExperience.add("经验不限");
        workExperience.add("在校/应届");
        workExperience.add("3年及以下");
        workExperience.add("3-5年");
        workExperience.add("5-10年");
        workExperience.add("10年以上");
        filterInformation.setWorkExperience(workExperience);
        List<String> education = new ArrayList<>();
        education.add("不要求");
        education.add("大专");
        education.add("本科");
        education.add("硕士");
        education.add("博士");
        filterInformation.setEducation(education);
        List<String> natureWork = new ArrayList<>();
        natureWork.add("全职");
        natureWork.add("兼职");
        natureWork.add("实习");
        filterInformation.setNatureWork(natureWork);
        List<String> companySize = new ArrayList<>();
        companySize.add("少于15人");
        companySize.add("15-50人");
        companySize.add("50-150人");
        companySize.add("150-500人");
        companySize.add("500-2000人");
        companySize.add("2000以上");
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
        industryField.add("技术|测试|运维");
        industryField.add("产品|运营|策划");
        industryField.add("艺术|设计");
        industryField.add("数据|BI|用研");
        industryField.add("游戏");
        filterInformation.setIndustryField(industryField);
        return serviceToControllerBody.success(filterInformation);
    }

    @Override
    public ServiceToControllerBody<List<PositionType>> getPositionTypes() {
        ServiceToControllerBody<List<PositionType>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<PositionType> positionTypes = new ArrayList<>();
        List<Direction> directions = new ArrayList<>();
        List<String> positions = new ArrayList<>();
        positions.add("JAVA工程师");
        positions.add("后端工程师");
        positions.add("前端工程师");
        positions.add("移动端工程师");
        positions.add("软件工程师");
        positions.add("全栈工程师");
        positions.add("算法工程师");
        positions.add("大数据开发工程师");
        positions.add("嵌入式软件工程师");
        positions.add("架构师");
        directions.add(new Direction("技术开发", positions));
        positions = new ArrayList<>();
        positions.add("技术经理|主管");
        positions.add("技术总监");
        positions.add("CTO|CIO");
        directions.add(new Direction("技术开发管理", positions));
        positions = new ArrayList<>();
        positions.add("测试工程师");
        positions.add("自动化测试");
        positions.add("测试开发");
        directions.add(new Direction("测试", positions));
        positions = new ArrayList<>();
        positions.add("测试经理|主管");
        positions.add("测试总监");
        directions.add(new Direction("测试管理", positions));
        positions = new ArrayList<>();
        positions.add("游戏主程");
        positions.add("游戏引擎开发");
        positions.add("游戏测试");
        positions.add("游戏编程");
        directions.add(new Direction("游戏开发|测试", positions));
        directions.add(new Direction("运维|技术支持", new ArrayList<>()));
        directions.add(new Direction("运维|DBA管理", new ArrayList<>()));
        positionTypes.add(new PositionType("技术|测试|运维", directions));
        positionTypes.add(new PositionType("产品|运营|策划", new ArrayList<>()));
        positionTypes.add(new PositionType("艺术|设计", new ArrayList<>()));
        positionTypes.add(new PositionType("数据|BI|用研", new ArrayList<>()));
        positionTypes.add(new PositionType("游戏", new ArrayList<>()));
        return serviceToControllerBody.success(positionTypes);
    }

    @Override
    public ServiceToControllerBody<List<DirectionTag>> getDirectionTags(String positionName) {
        ServiceToControllerBody<List<DirectionTag>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<DirectionTag> directionTags = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("C语言");
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
        directionTags.add(new DirectionTag("开发语言", tags));
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
        directionTags.add(new DirectionTag("开发框架", tags));
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
        directionTags.add(new DirectionTag("操作系统", tags));
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
        directionTags.add(new DirectionTag("数据库", tags));
        return serviceToControllerBody.success(directionTags);
    }

    @Override
    public ServiceToControllerBody<List<CityInformation>> getCityInformations() {
        ServiceToControllerBody<List<CityInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        List<CityInformation> cityInformations = new ArrayList<>();
        cityInformations.add(new CityInformation("北京", new ArrayList<>() {
            {
                add("海淀区");
                add("东城区");
                add("西城区");
                add("朝阳区");
                add("丰台区");
                add("石景山区");
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
    public ServiceToControllerBody<String> getVerificationCode(String email) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        String verificationCode = String.valueOf(new Random().nextInt(8999) + 1000);
        emailMessageUtil.sendEmail(email, "东江招聘-验证码", "您的验证码为：" + verificationCode + "，请在5分钟内使用。");
        verificationCodeTemplate.opsForValue().set(email, verificationCode, 5, TimeUnit.MINUTES);
        return serviceToControllerBody.success("验证码已发送，请注意查收。");
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
            return serviceToControllerBody.error("file", "文件为空", null);
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + suffixName;
        File dest = new File("/var/www/html/file/" + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("文件上传失败:{}", e);
            return serviceToControllerBody.error("file", "文件上传失败", file.getOriginalFilename());
        }
        return serviceToControllerBody.success("/file/" + fileName);
    }

    @Override
    public ServiceToControllerBody<String> uploadAvatar(MultipartFile avatar) {
        ServiceToControllerBody<String> serviceToControllerBody = new ServiceToControllerBody<>();
        if (avatar.isEmpty()) {
            return serviceToControllerBody.error("file", "文件为空", null);
        }
        String avatarName = avatar.getOriginalFilename();
        String suffixName = avatarName.substring(avatarName.lastIndexOf("."));
        avatarName = UUID.randomUUID() + suffixName;
        File dest = new File("/var/www/html/avatar/" + avatarName);
        try {
            avatar.transferTo(dest);
        } catch (IOException e) {
            log.error("头像上传失败:{}", e);
            return serviceToControllerBody.error("avatar", "头像上传失败", avatar.getOriginalFilename());
        }
        return serviceToControllerBody.success("/avatar/" + avatarName);
    }

}
