package com.lld.service.group.model.resp;

import com.lld.service.group.dao.ImGroupEntity;
import lombok.Data;

import java.util.List;

/**
 * @author: kunlunrepo
 * @description:
 **/
@Data
public class GetJoinedGroupResp {

    private Integer totalCount;

    private List<ImGroupEntity> groupList;

}
