
package com.jfeat.module.blacklist.api.manage;


import com.jfeat.crud.plus.META;
import com.jfeat.am.core.jwt.JWTKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.module.blacklist.services.domain.dao.QueryEndUserBlacklistDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.blacklist.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import java.math.BigDecimal;

import com.jfeat.module.blacklist.services.domain.service.*;
import com.jfeat.module.blacklist.services.domain.model.EndUserBlacklistRecord;
import com.jfeat.module.blacklist.services.gen.persistence.model.EndUserBlacklist;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2022-11-15
 */
@RestController
@Api("EndUserBlacklist")
@RequestMapping("/api/adm/cms/blacklist")
public class EndUserBlacklistEndpoint {

    @Resource
    EndUserBlacklistService endUserBlacklistService;

    @Resource
    QueryEndUserBlacklistDao queryEndUserBlacklistDao;


    @BusinessLog(name = "EndUserBlacklist", value = "create EndUserBlacklist")
    @Permission(EndUserBlacklistPermission.ENDUSERBLACKLIST_NEW)
    @PostMapping
    @ApiOperation(value = "新建 EndUserBlacklist", response = EndUserBlacklist.class)
    public Tip createEndUserBlacklist(@RequestBody EndUserBlacklist entity) {
        Integer affected = 0;
        try {
            affected = endUserBlacklistService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(EndUserBlacklistPermission.ENDUSERBLACKLIST_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 EndUserBlacklist", response = EndUserBlacklist.class)
    public Tip getEndUserBlacklist(@PathVariable Long id) {
        return SuccessTip.create(endUserBlacklistService.queryMasterModel(queryEndUserBlacklistDao, id));
    }

    @BusinessLog(name = "EndUserBlacklist", value = "update EndUserBlacklist")
    @Permission(EndUserBlacklistPermission.ENDUSERBLACKLIST_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 EndUserBlacklist", response = EndUserBlacklist.class)
    public Tip updateEndUserBlacklist(@PathVariable Long id, @RequestBody EndUserBlacklist entity) {
        entity.setId(id);
        return SuccessTip.create(endUserBlacklistService.updateMaster(entity));
    }

    @BusinessLog(name = "EndUserBlacklist", value = "delete EndUserBlacklist")
    @Permission(EndUserBlacklistPermission.ENDUSERBLACKLIST_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 EndUserBlacklist")
    public Tip deleteEndUserBlacklist(@PathVariable Long id) {
        return SuccessTip.create(endUserBlacklistService.deleteMaster(id));
    }

    @Permission(EndUserBlacklistPermission.ENDUSERBLACKLIST_VIEW)
    @ApiOperation(value = "EndUserBlacklist 列表信息", response = EndUserBlacklistRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "userId", dataType = "Long"),
            @ApiImplicitParam(name = "state", dataType = "Integer"),
            @ApiImplicitParam(name = "orgId", dataType = "Long"),
            @ApiImplicitParam(name = "appid", dataType = "String"),
            @ApiImplicitParam(name = "userRange", dataType = "Integer"),
            @ApiImplicitParam(name = "createTime", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", dataType = "Date"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryEndUserBlacklistPage(Page<EndUserBlacklistRecord> page,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         // for tag feature query
                                         @RequestParam(name = "tag", required = false) String tag,
                                         // end tag
                                         @RequestParam(name = "search", required = false) String search,

                                         @RequestParam(name = "userId", required = false) Long userId,

                                         @RequestParam(name = "state", required = false) Boolean state,

                                         @RequestParam(name = "orgId", required = false) Long orgId,

                                         @RequestParam(name = "appid", required = false) String appid,

                                         @RequestParam(name = "userRange", required = false) Boolean userRange,

                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                         @RequestParam(name = "createTime", required = false) Date createTime,

                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                         @RequestParam(name = "updateTime", required = false) Date updateTime,
                                         @RequestParam(name = "orderBy", required = false) String orderBy,
                                         @RequestParam(name = "sort", required = false) String sort) {

        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String sortPattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(sortPattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        EndUserBlacklistRecord record = new EndUserBlacklistRecord();
        record.setUserId(userId);
        record.setState(state);
        if (META.enabledSaas()) {
            record.setOrgId(JWTKit.getOrgId());
        }
        record.setAppid(appid);
        record.setUserRange(userRange);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);


        List<EndUserBlacklistRecord> endUserBlacklistPage = queryEndUserBlacklistDao.findEndUserBlacklistPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(endUserBlacklistPage);

        return SuccessTip.create(page);
    }
}

