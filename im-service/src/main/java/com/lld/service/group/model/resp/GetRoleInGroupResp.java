package com.lld.service.group.model.resp;

import lombok.Data;

/**
 * @author: kunlunrepo
 * @description:
 **/
@Data
public class GetRoleInGroupResp {

    private Long groupMemberId;

    private String memberId;

    private Integer role;

    private Long speakDate;

}
