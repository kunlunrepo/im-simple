package com.lld.service.user.model.req;

import com.lld.common.model.RequestBase;
import lombok.Data;

/**
 * @description:
 * @
 * @version: 1.0
 */
@Data
public class GetUserSequenceReq extends RequestBase {

    private String userId;

}
