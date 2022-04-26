package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.util.CityInformation;
import com.ucstu.guangbt.djzhaopin.model.util.DirectionTag;
import com.ucstu.guangbt.djzhaopin.model.util.FilterInformation;
import com.ucstu.guangbt.djzhaopin.model.util.areainformation.AreaInformation;
import com.ucstu.guangbt.djzhaopin.model.util.positiontype.PositionType;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UtilService {

    Optional<List<AreaInformation>> getAreaInformations(String city);

    Optional<FilterInformation> getFilterInformation();

    Optional<List<PositionType>> getPositionTypes();

    Optional<List<DirectionTag>> getDirectionTags(String positionName);

    Optional<List<CityInformation>> getCityInformations();

    Optional<List<UUID>> getRecommendations(Pageable pageable);

    Optional<List<MessageRecord>> getMessageRecords(Pageable pageable);

    Optional<String> getVerificationCode(String phoneNumber);

    Optional<String> getNewVersion();

    Optional<String> uploadFile(MultipartFile file);

    Optional<String> uploadAvatar(MultipartFile avatar);

}
