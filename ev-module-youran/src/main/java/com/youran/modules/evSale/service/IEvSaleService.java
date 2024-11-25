package com.youran.modules.evSale.service;

import com.youran.modules.evSale.vo.EvSaleVo;
import com.youran.modules.evSale.entity.EvSale;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youran.modules.evSale.vo.EvSaleVo;

/**
 * @Description: 销售单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
public interface IEvSaleService extends IService<EvSale> {

    public boolean saveGoods(EvSaleVo evSaleVo);

    public boolean updateGoodsById(EvSaleVo evSaleVo);

    public boolean removeGoodsById(String id);

    public EvSaleVo getGoodsById(String id);
}
