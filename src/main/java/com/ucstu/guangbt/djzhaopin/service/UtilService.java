package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.util.CityInformation;
import com.ucstu.guangbt.djzhaopin.model.util.DirectionTag;
import com.ucstu.guangbt.djzhaopin.model.util.FilterInformation;
import com.ucstu.guangbt.djzhaopin.model.util.areainformation.AreaInformation;
import com.ucstu.guangbt.djzhaopin.model.util.positiontype.PositionType;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UtilService {

    public ServiceToControllerBody<List<AreaInformation>> getAreaInformations(String cityName);

    public ServiceToControllerBody<FilterInformation> getFilterInformation();

    public ServiceToControllerBody<List<PositionType>> getPositionTypes();

    public ServiceToControllerBody<List<DirectionTag>> getDirectionTags(String positionName);

    public ServiceToControllerBody<List<CityInformation>> getCityInformations();

    public ServiceToControllerBody<List<UUID>> getRecommendations(Pageable pageable);

    public ServiceToControllerBody<List<MessageRecord>> getMessageRecords(Pageable pageable);

    public ServiceToControllerBody<String> getVerificationCode(String email);

    public ServiceToControllerBody<String> getNewVersion();

    public ServiceToControllerBody<String> uploadFile(MultipartFile file);

    public ServiceToControllerBody<String> uploadAvatar(MultipartFile avatar);

}
