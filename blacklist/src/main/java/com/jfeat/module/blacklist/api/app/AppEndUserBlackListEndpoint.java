package com.jfeat.module.blacklist.api.app;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.blacklist.services.domain.dao.QueryEndUserBlacklistDao;
import com.jfeat.module.blacklist.services.domain.model.EndUserBlacklistRecord;
import com.jfeat.module.blacklist.services.domain.service.EndUserBlacklistService;
import com.jfeat.module.blacklist.services.gen.persistence.model.EndUserBlacklist;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cms/blacklist")
public class AppEndUserBlackListEndpoint {

    @Resource
    EndUserBlacklistService endUserBlacklistService;

    @Resource
    QueryEndUserBlacklistDao queryEndUserBlacklistDao;



    @PostMapping("/{userId}/userAccount/shield")
    @ApiOperation("拉黑用户")
    public Tip setShieldUserAccount(@PathVariable("userId") Long userId,@RequestBody EndUserBlacklist entity){
        entity.setUserId(userId);
        entity.setUserRange(true);
        return SuccessTip.create(endUserBlacklistService.setShield(entity));
    }


    @PostMapping("/{userId}/userAccount/unshield")
    @ApiOperation("取消拉黑")
    public Tip cancelShieldUserAccount(@PathVariable("userId") Long userId){
        EndUserBlacklist entity = new EndUserBlacklist();
        entity.setUserId(userId);
        entity.setUserRange(true);
        return SuccessTip.create(endUserBlacklistService.cancel(entity));
    }


    @PostMapping("/{userId}/userAccount/status")
    @ApiOperation("获取用户是否被拉黑")
    public Tip getShieldUserAccountStatus(@PathVariable("userId") Long userId){
        return SuccessTip.create(endUserBlacklistService.isUserShield(userId));
    }

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
        record.setUserRange(true);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);

        List<EndUserBlacklistRecord> endUserBlacklistPage = queryEndUserBlacklistDao.findEndUserBlacklistPage(page, record, tag, search, orderBy, null, null);

        page.setRecords(endUserBlacklistPage);

        return SuccessTip.create(page);
    }
}
