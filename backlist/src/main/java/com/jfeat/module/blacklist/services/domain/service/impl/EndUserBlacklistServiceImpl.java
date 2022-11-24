package com.jfeat.module.blacklist.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.blacklist.services.domain.service.EndUserBlacklistService;
import com.jfeat.module.blacklist.services.gen.crud.service.impl.CRUDEndUserBlacklistServiceImpl;
import com.jfeat.module.blacklist.services.gen.persistence.dao.EndUserBlacklistMapper;
import com.jfeat.module.blacklist.services.gen.persistence.model.EndUserBlacklist;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("endUserBlacklistService")
public class EndUserBlacklistServiceImpl extends CRUDEndUserBlacklistServiceImpl implements EndUserBlacklistService {

//    @Resource
//    UserAccountMapper userAccountMapper;

    @Resource
    EndUserBlacklistService endUserBlacklistService;

    @Resource
    EndUserBlacklistMapper endUserBlacklistMapper;

    @Override
    protected String entityName() {
        return "EndUserBlacklist";
    }


    @Override
    public Integer setShield(EndUserBlacklist entity) {
        Integer affect = 0;
        if (entity.getUserId()==null) {
            throw new BusinessException(BusinessCode.BadRequest,"userId不能为空");
        }
//        UserAccount userAccount = userAccountMapper.selectById(entity.getUserId());
//        if (userAccount==null){
//            throw new BusinessException(BusinessCode.UserNotExisted,"用户不存在");
//        }
        try {
            affect = endUserBlacklistService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return affect;
    }

    @Override
    @Transactional
    public Integer cancel(EndUserBlacklist entity) {
        QueryWrapper<EndUserBlacklist> queryWrapper = new QueryWrapper<>();
        Integer affect = 0;
        if (entity.getUserId()==null) {
            throw new BusinessException(BusinessCode.BadRequest,"userId不能为空");
        }
//        UserAccount userAccount = userAccountMapper.selectById(entity.getUserId());
//        if (userAccount==null){
//            throw new BusinessException(BusinessCode.UserNotExisted,"用户不存在");
//        }

        queryWrapper.eq(EndUserBlacklist.USER_ID,entity.getUserId());

        if (entity.getUserRange()!=null){
            queryWrapper.eq(EndUserBlacklist.USER_RANGE,entity.getUserRange());
        }

        if (entity.getOrgId()!=null){
            queryWrapper.eq(EndUserBlacklist.ORG_ID,entity.getOrgId());
        }

        if (entity.getAppid()!=null){
            queryWrapper.eq(EndUserBlacklist.APPID,entity.getAppid());
        }
        affect+=endUserBlacklistMapper.delete(queryWrapper);

        return affect;
    }

    @Override
    public Boolean isUserShield(Long userId) {
        QueryWrapper<EndUserBlacklist> queryWrapper = new QueryWrapper<>();
        Integer affect = 0;
        if (userId==null) {
            throw new BusinessException(BusinessCode.BadRequest,"userId不能为空");
        }
//        UserAccount userAccount = userAccountMapper.selectById(userId);
//        if (userAccount==null){
//            throw new BusinessException(BusinessCode.UserNotExisted,"用户不存在");
//        }

        queryWrapper.eq(EndUserBlacklist.USER_ID,userId);
        queryWrapper.eq(EndUserBlacklist.USER_RANGE,true);

        EndUserBlacklist endUserBlacklist = endUserBlacklistMapper.selectOne(queryWrapper);
        if (endUserBlacklist!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean isUserOrgIdShield(Long userId,Long orgId) {
        QueryWrapper<EndUserBlacklist> queryWrapper = new QueryWrapper<>();
        Integer affect = 0;
        if (userId==null) {
            throw new BusinessException(BusinessCode.BadRequest,"userId不能为空");
        }
        if (orgId==null){
            throw new BusinessException(BusinessCode.BadRequest,"orgId不能为空");
        }
//        UserAccount userAccount = userAccountMapper.selectById(userId);
//        if (userAccount==null){
//            throw new BusinessException(BusinessCode.UserNotExisted,"用户不存在");
//        }

        queryWrapper.eq(EndUserBlacklist.USER_ID,userId);
        queryWrapper.eq(EndUserBlacklist.ORG_ID,orgId);

        EndUserBlacklist endUserBlacklist = endUserBlacklistMapper.selectOne(queryWrapper);
        if (endUserBlacklist!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean isUserAppidShield(Long userId,String appid) {
        QueryWrapper<EndUserBlacklist> queryWrapper = new QueryWrapper<>();
        Integer affect = 0;
        if (userId==null) {
            throw new BusinessException(BusinessCode.BadRequest,"userId不能为空");
        }
        if (appid==null){
            throw new BusinessException(BusinessCode.BadRequest,"appid不能为空");
        }
//        UserAccount userAccount = userAccountMapper.selectById(userId);
//        if (userAccount==null){
//            throw new BusinessException(BusinessCode.UserNotExisted,"用户不存在");
//        }

        queryWrapper.eq(EndUserBlacklist.USER_ID,userId);
        queryWrapper.eq(EndUserBlacklist.APPID,appid);

        EndUserBlacklist endUserBlacklist = endUserBlacklistMapper.selectOne(queryWrapper);
        if (endUserBlacklist!=null){
            return true;
        }else {
            return false;
        }
    }

}
