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

    ServiceToControllerBody<List<AreaInformation>> getAreaInformations(String city);

    ServiceToControllerBody<FilterInformation> getFilterInformation();

    ServiceToControllerBody<List<PositionType>> getPositionTypes();

    ServiceToControllerBody<List<DirectionTag>> getDirectionTags(String positionName);

    ServiceToControllerBody<List<CityInformation>> getCityInformations();

    ServiceToControllerBody<List<UUID>> getRecommendations(Pageable pageable);

    ServiceToControllerBody<List<MessageRecord>> getMessageRecords(Pageable pageable);

    ServiceToControllerBody<String> getVerificationCode(String phoneNumber);

    ServiceToControllerBody<String> getNewVersion();

    ServiceToControllerBody<String> uploadFile(MultipartFile file);

    ServiceToControllerBody<String> uploadAvatar(MultipartFile avatar);

}
