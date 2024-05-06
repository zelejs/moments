package com.jfeat.module.blacklist.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.blacklist.services.gen.persistence.model.EndUserBlacklist;
import com.jfeat.module.blacklist.services.gen.persistence.dao.EndUserBlacklistMapper;
import com.jfeat.module.blacklist.services.gen.crud.service.CRUDEndUserBlacklistService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDEndUserBlacklistService
 * @author Code generator
 * @since 2022-11-15
 */

@Service
public class CRUDEndUserBlacklistServiceImpl  extends CRUDServiceOnlyImpl<EndUserBlacklist> implements CRUDEndUserBlacklistService {





        @Resource
        protected EndUserBlacklistMapper endUserBlacklistMapper;

        @Override
        protected BaseMapper<EndUserBlacklist> getMasterMapper() {
                return endUserBlacklistMapper;
        }







}


