package com.lld.service.friendship.service;

import com.lld.common.ResponseVO;
import com.lld.service.friendship.model.req.AddFriendShipGroupMemberReq;
import com.lld.service.friendship.model.req.DeleteFriendShipGroupMemberReq;

/**
 * @author: kunlunrepo
 * @description:
 **/
public interface ImFriendShipGroupMemberService {

    public ResponseVO addGroupMember(AddFriendShipGroupMemberReq req);

    public ResponseVO delGroupMember(DeleteFriendShipGroupMemberReq req);

    public int doAddGroupMember(Long groupId, String toId);

    public int clearGroupMember(Long groupId);
}
