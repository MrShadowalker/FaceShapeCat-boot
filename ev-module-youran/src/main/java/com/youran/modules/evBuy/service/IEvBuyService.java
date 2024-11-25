package com.youran.modules.evBuy.service;

import com.youran.modules.evBuy.entity.EvBuy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youran.modules.evBuy.entity.EvBuyVO;

/**
 * @Description: 采购表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
public interface IEvBuyService extends IService<EvBuy> {

    public boolean saveGoods(EvBuyVO evBuyVO);

    public boolean updateGoodsById(EvBuyVO evBuyVO);

    public boolean removeGoodsByBuyId(String id);

    public EvBuyVO getGoodsById(String id);

}
