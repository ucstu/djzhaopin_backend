package com.ucstu.guangbt.djzhaopin.service;

import java.security.Principal;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;

public interface MessageRecordService {

    void sendUserMessage(Principal principal, MessageRecord messageRecord);

}
