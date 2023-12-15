package com.lld.service.user.model.resp;

import lombok.Data;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-12-09 13:05
 */
@Data
public class ImportUserResp {

    private List<String> successId;
    private List<String> errorId;

}
