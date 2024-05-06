package com.jfeat.module.blacklist.services.domain.service;

import com.jfeat.module.blacklist.services.domain.model.EndUserBlacklistRecord;
import com.jfeat.module.blacklist.services.gen.crud.service.CRUDEndUserBlacklistService;
import com.jfeat.module.blacklist.services.gen.persistence.model.EndUserBlacklist;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface EndUserBlacklistService extends CRUDEndUserBlacklistService {

    Integer setShield(EndUserBlacklist entity);

    Integer cancel(EndUserBlacklist entity);

    Boolean isUserShield(Long userId);

    Boolean isUserOrgIdShield(Long userId,Long orgId);

    Boolean isUserAppidShield(Long userId,String appid);
}