package com.lld.service.group.model.req;

import com.lld.common.model.RequestBase;
import lombok.Data;

import java.util.List;

/**
 * @author: kunlunrepo
 * @description:
 **/
@Data
public class GetRoleInGroupReq extends RequestBase {

    private String groupId;

    private List<String> memberId;
}
