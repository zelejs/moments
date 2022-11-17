package com.jfeat.am.module.ff.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.ff.services.crud.service.impl.CRUDStockFlowerServiceImpl;
import com.jfeat.am.module.ff.services.domain.service.StockFlowerService;
import com.jfeat.am.module.ff.services.persistence.dao.StockFlowerMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
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
@Service("StockFlowerService")
public class StockFlowerServiceImpl extends CRUDStockFlowerServiceImpl implements StockFlowerService {


    @Resource
    StockFlowerMapper flowerMapper;
/*    @Resource
    UserNotifyService userNotifyService;*/


    /**
     * 点赞 记录 我的点赞 记录
     */
    @Override
    public List<StockFlower> myFlowers(Long memberId,String stockType) {

        List<StockFlower> stockFlowerPage = flowerMapper.selectList( new QueryWrapper<StockFlower>().eq(StockFlower.MEMBER_ID, memberId)
                .eq(StockFlower.STOCK_TYPE, stockType));
        return stockFlowerPage;

    }

    /**
     * 单个商品点赞 记录
     */
    @Override
    public List<StockFlower> stockFlowers( Long stockId, String stockType) {
        List<StockFlower> stockFlowerPage = flowerMapper.selectList(new QueryWrapper<StockFlower>()
                .eq(StockFlower.STOCK_ID, stockId)
                .eq(StockFlower.STOCK_TYPE, stockType).orderBy(true, false, StockFlower.CREATE_TIME));
        return stockFlowerPage;
    }


    /**
     * 是否 已点赞
     */
    public Integer isFlowered(Long userId, Long stockId, String stockType) {
        StockFlower origin = null;
        if(userId != null) {
            QueryWrapper<StockFlower> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("member_id",userId);
            queryWrapper.eq("stock_id",stockId);
            queryWrapper.eq("stock_type",stockType);
            origin = flowerMapper.selectOne(queryWrapper);
        }
        return origin == null ? Integer.valueOf(0) : Integer.valueOf(1);
    }

    /**
     * 是否 已点赞
     */
    public StockFlower flowered(Long userId, Long stockId, String stockType) {
        QueryWrapper<StockFlower> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",userId);
        queryWrapper.eq("stock_id",stockId);
        queryWrapper.eq("stock_type",stockType);
        StockFlower origin = flowerMapper.selectOne(queryWrapper);
        if (origin == null)
            return null;
        return origin;
    }

    /**
     * 点赞(cancel点赞)
     */
    @Override
    public Integer floweredOrNot(StockFlower flower) {
        Integer affected = 0;
        QueryWrapper<StockFlower> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",flower.getMemberId());
        queryWrapper.eq("stock_id",flower.getStockId());
        queryWrapper.eq("stock_type",flower.getStockType());
        StockFlower origin = flowerMapper.selectOne(queryWrapper);
/*      Notify notify = new Notify();
        notify.setTargetId(flower.getStockId());
        notify.setTargetType(flower.getStockType());
        notify.setOriginType(originType);
        notify.setOriginId(originId);
        notify.setSenderId(flower.getMemberId());
        notify.setContent("");*/
        if (origin == null) {
            affected += flowerMapper.insert(flower);
/*            notify.setSourceId(flower.getId());
            notify.setSourceType("Flower");
            notify.setAction("UnFlower");
            userNotifyService.createRemind(notify);*/

            return affected;
        }
        // 取消点赞获取消息
//        userNotifyService.createRemind(flower.getStockId(),
//                flower.getStockType(),
//                "UnFlower",
//                flower.getMemberId(),
//                "",
//                flower.getStockId(),
//                flower.getStockType());
        affected += flowerMapper.deleteById(origin.getId());
        return affected;
    }
}
