package com.lld.service.friendship.service;

import com.lld.common.ResponseVO;
import com.lld.service.friendship.model.req.ImporFriendShipReq;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-12-09 19:32
 */
public interface ImFriendService {

    ResponseVO importFriendShip(ImporFriendShipReq req);

}
