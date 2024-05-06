package com.jfeat.module.blacklist.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2022-11-15
 */
@TableName("t_end_user_blacklist")
@ApiModel(value = "EndUserBlacklist对象", description = "")
public class EndUserBlacklist extends Model<EndUserBlacklist> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "是否被拉黑")
    private Boolean state;

    @ApiModelProperty(value = "社区范围拉黑")
    private Long orgId;

    @ApiModelProperty(value = "软件范围拉黑")
    private String appid;

    @ApiModelProperty(value = "是否拉黑整个账号")
    private Boolean userRange;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public EndUserBlacklist setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public EndUserBlacklist setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getOrgId() {
        return orgId;
    }

    public EndUserBlacklist setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public EndUserBlacklist setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public Boolean getUserRange() {
        return userRange;
    }

    public void setUserRange(Boolean userRange) {
        this.userRange = userRange;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public EndUserBlacklist setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public EndUserBlacklist setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String STATE = "state";

    public static final String ORG_ID = "org_id";

    public static final String APPID = "appid";

    public static final String USER_RANGE = "user_range";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EndUserBlacklist{" +
                "id=" + id +
                ", userId=" + userId +
                ", state=" + state +
                ", orgId=" + orgId +
                ", appid=" + appid +
                ", userRange=" + userRange +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
