package com.wintop.ms.carauction.mapper.write;

import com.wintop.ms.carauction.entity.WtRegion;

/**
 * Created by liangtingsen on 2018/3/6.
 * 地区区域表
 */
public interface IWtRegionWriteDao {
    /***
     * 保存
     * @param wtRegion
     */
    void saveOne(WtRegion wtRegion);

    /***
     * 修改
     * @param wtRegion
     */
    void updateOne(WtRegion wtRegion);
}
