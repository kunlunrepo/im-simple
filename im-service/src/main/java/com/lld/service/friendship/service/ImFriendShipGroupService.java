package com.lld.service.friendship.service;

import com.lld.common.ResponseVO;
import com.lld.service.friendship.dao.ImFriendShipGroupEntity;
import com.lld.service.friendship.model.req.AddFriendShipGroupReq;
import com.lld.service.friendship.model.req.DeleteFriendShipGroupReq;

/**
 * @author: kunlunrepo
 * @description:
 **/
public interface ImFriendShipGroupService {

    public ResponseVO addGroup(AddFriendShipGroupReq req);

    public ResponseVO deleteGroup(DeleteFriendShipGroupReq req);

    public ResponseVO<ImFriendShipGroupEntity> getGroup(String fromId, String groupName, Integer appId);


}
