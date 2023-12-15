package com.lld.service.user.model.resp;

import com.lld.common.model.UserSession;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: kunlunrepo
 * @version: 1.0
 */
@Data
public class UserOnlineStatusResp {

    private List<UserSession> session;

    private String customText;

    private Integer customStatus;

}
