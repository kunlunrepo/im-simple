package com.lld.service.friendship.service;

import com.lld.common.ResponseVO;
import com.lld.common.model.RequestBase;
import com.lld.service.friendship.model.req.*;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-12-09 19:32
 */
public interface ImFriendShipService {

    ResponseVO importFriendShip(ImporFriendShipReq req);

    public ResponseVO addFriend(AddFriendReq req);

    public ResponseVO updateFriend(UpdateFriendReq req);

    public ResponseVO deleteFriend(DeleteFriendReq req);

    public ResponseVO deleteAllFriend(DeleteFriendReq req);

    public ResponseVO getAllFriendShip(GetAllFriendShipReq req);

    public ResponseVO getRelation(GetRelationReq req);

    public ResponseVO doAddFriend(RequestBase requestBase, String fromId, FriendDto dto, Integer appId);

    public ResponseVO checkFriendship(CheckFriendShipReq req);

    public ResponseVO addBlack(AddFriendShipBlackReq req);

    public ResponseVO deleteBlack(DeleteBlackReq req);

    public ResponseVO checkBlck(CheckFriendShipReq req);
}
