package com.lld.service.group.model.req;

import com.lld.common.model.RequestBase;
import lombok.Data;

/**
 * @author: kunlunrepo
 * @description:
 **/
@Data
public class GetGroupReq extends RequestBase {

    private String groupId;

}
