package com.lld.service.user.model.req;

import com.lld.common.ResponseVO;
import com.lld.common.model.RequestBase;
import com.lld.service.user.dao.ImUserDataEntity;
import com.lld.service.user.model.resp.GetUserInfoResp;
import lombok.Data;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-12-09 12:51
 */
@Data
public class ImportUserReq  extends RequestBase {

    private List<ImUserDataEntity> userList;

}
