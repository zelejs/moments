package com.jfeat.am.module.ff.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.ff.services.crud.service.impl.CRUDStockFavoriteServiceImpl;
import com.jfeat.am.module.ff.services.domain.service.StockFavoriteService;
import com.jfeat.am.module.ff.services.persistence.dao.StockFavoriteMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import com.jfeat.crud.base.request.Ids;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("StockFavoriteService")
public class StockFavoriteServiceImpl extends CRUDStockFavoriteServiceImpl implements StockFavoriteService {


    @Resource
    StockFavoriteMapper favoriteMapper;
/*    @Resource
    UserNotifyService userNotifyService;*/

    /**
     * 我的收藏(文章/商品 etc....)
     * @return
     */
    @Override
    public List<StockFavorite> myFavorites(Page<StockFavorite> page, Long memberId, String stockType) {


        Page<StockFavorite> stockFavoritePage = favoriteMapper.selectPage(page, new QueryWrapper<StockFavorite>().eq(StockFavorite.MEMBER_ID, memberId)
                .eq(StockFavorite.STOCK_TYPE, stockType)
                .orderBy(true, false, StockFavorite.CREATE_TIME));
        return stockFavoritePage.getRecords();

    }


    /**
     * 单个商品收藏列表
     */
    @Override
    public List<StockFavorite> stockFavorites(Page<StockFavorite> page, Long stockId, String stockType) {
        List<StockFavorite> favorites = favoriteMapper.selectList(new QueryWrapper<StockFavorite>()
                .eq(StockFavorite.STOCK_ID, stockId)
                .eq(StockFavorite.STOCK_TYPE, stockType).orderBy(true, false,StockFavorite.CREATE_TIME));
        return favorites;
    }


    /**
     * 是否 已收藏 改 物体
     */
    @Override
    public Integer isFavorited(Long userId, Long stockId, String stockType) {
        StockFavorite origin = null;
        if(userId != null) {
            QueryWrapper<StockFavorite> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("member_id",userId);
            queryWrapper.eq("stock_id",stockId);
            queryWrapper.eq("stock_type",stockType);
/*            StockFavorite favorite = new StockFavorite();
            favorite.setMemberId(userId);
            favorite.setStockId(stockId);
            favorite.setStockType(stockType);*/
            origin = favoriteMapper.selectOne(queryWrapper);
        }
        return origin == null ? Integer.valueOf(0) : Integer.valueOf(1);
    }

    /**
     * 是否 已收藏 改 物体
     */
    @Override
    public StockFavorite favorited(Long userId, Long stockId, String stockType) {
        QueryWrapper<StockFavorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",userId);
        queryWrapper.eq("stock_id",stockId);
        queryWrapper.eq("stock_type",stockType);
/*        StockFavorite favorite = new StockFavorite();
        favorite.setMemberId(userId);
        favorite.setStockId(stockId);
        favorite.setStockType(stockType);*/
        StockFavorite origin = favoriteMapper.selectOne(queryWrapper);
        if (origin == null)
            return null;
        return origin;
    }

    /**
     * 收藏/or cancel  物体
     */
    @Override
    public Integer favoritedOrNot(StockFavorite favorite,  Long originId, String originType) {
        Integer affected = 0;
        QueryWrapper<StockFavorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",favorite.getMemberId());
        queryWrapper.eq("stock_id",favorite.getStockId());
        queryWrapper.eq("stock_type",favorite.getStockType());
        StockFavorite origin = favoriteMapper.selectOne(queryWrapper);
/*        Notify notify = new Notify();
        notify.setTargetId(favorite.getStockId());
        notify.setTargetType(favorite.getStockType());
        notify.setOriginType(originType);
        notify.setOriginId(originId);
        notify.setSenderId(favorite.getMemberId());
        notify.setContent("");*/
        if (origin == null) {
            affected += favoriteMapper.insert(favorite);
           /* notify.setSourceId(favorite.getId());
            notify.setSourceType("Favorite");
            notify.setAction("Favorite");
            userNotifyService.createRemind(notify);*/
            return affected;
        }
//        notify.setSourceId(favorite.getId());
//        notify.setSourceType("Favorite");
//        notify.setAction("UnFavorite");
//        userNotifyService.createRemind(notify);
        affected += favoriteMapper.deleteById(origin.getId());
        return affected;
    }


    /**
     * enhance  bulk cancel Favorite
     */
    /**
     * enhance  bulk cancel Favorite
     */
    @Override
    public Integer bulkCancelFavorite(Long userId, Ids ids) {
        int affected = 0;
        for (Long id : ids.getIds()) {
            StockFavorite favorite = favoriteMapper.selectById(id);
            if (favorite == null) {

            } else {
                if (favorite.getMemberId().compareTo(userId) == 0) {
                    affected += favoriteMapper.deleteById(id);
                } else {

                }
            }
        }
        return affected;
    }
}
