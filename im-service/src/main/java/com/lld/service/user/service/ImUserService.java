package com.lld.service.user.service;

import com.lld.common.ResponseVO;
import com.lld.service.user.dao.ImUserDataEntity;
import com.lld.service.user.model.req.DeleteUserReq;
import com.lld.service.user.model.req.GetUserInfoReq;
import com.lld.service.user.model.req.ImportUserReq;
import com.lld.service.user.model.req.ModifyUserInfoReq;
import com.lld.service.user.model.resp.GetUserInfoResp;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-12-09 12:47
 */
public interface ImUserService {

    ResponseVO importUser(ImportUserReq req);

    ResponseVO<GetUserInfoResp> getUserInfo(GetUserInfoReq req);

    ResponseVO<ImUserDataEntity> getSingleUserInfo(String userId, Integer appId);

    public ResponseVO deleteUser(DeleteUserReq req);

    ResponseVO modifyUserInfo(ModifyUserInfoReq req);

}
