package com.jfeat.module.blacklist.services.domain.dao;

import com.jfeat.module.blacklist.services.domain.model.EndUserBlacklistRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.blacklist.services.gen.persistence.model.EndUserBlacklist;
import com.jfeat.module.blacklist.services.gen.crud.model.EndUserBlacklistModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-11-15
 */
public interface QueryEndUserBlacklistDao extends QueryMasterDao<EndUserBlacklist> {
   /*
    * Query entity list by page
    */
    List<EndUserBlacklistRecord> findEndUserBlacklistPage(Page<EndUserBlacklistRecord> page, @Param("record") EndUserBlacklistRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    EndUserBlacklistModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<EndUserBlacklistModel> queryMasterModelList(@Param("masterId") Object masterId);
}