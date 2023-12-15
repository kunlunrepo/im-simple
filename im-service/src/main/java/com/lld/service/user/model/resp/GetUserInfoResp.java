package com.lld.service.user.model.resp;

import com.lld.service.user.dao.ImUserDataEntity;
import lombok.Data;

import java.util.List;

/**
 * @author: kunlunrepo
 * @description:
 **/
@Data
public class GetUserInfoResp {

    private List<ImUserDataEntity> userDataItem;

    private List<String> failUser;


}
