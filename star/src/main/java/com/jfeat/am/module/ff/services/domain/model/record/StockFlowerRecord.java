package com.jfeat.am.module.ff.services.domain.model.record;

import com.jfeat.am.module.ff.services.persistence.model.StockFlower;

/**
 * Created by Code Generator on 2018-07-16
 */
public class StockFlowerRecord extends StockFlower {
    Long originId;
    String originType;

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }
}
