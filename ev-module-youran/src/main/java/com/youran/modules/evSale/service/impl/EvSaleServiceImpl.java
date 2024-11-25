package com.youran.modules.evSale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youran.modules.evBuyGoods.entity.EvBuyGoods;
import com.youran.modules.evSaleGoods.entity.EvSaleGoods;
import com.youran.modules.evSale.entity.EvSale;
import com.youran.modules.evSale.vo.EvSaleVo;
import com.youran.modules.evSaleGoods.entity.EvSaleGoods;
import com.youran.modules.evSale.entity.EvSale;
import com.youran.modules.evSale.mapper.EvSaleMapper;
import com.youran.modules.evSale.service.IEvSaleService;
import com.youran.modules.evSale.vo.EvSaleVo;
import com.youran.modules.evSaleGoods.service.IEvSaleGoodsService;
import com.youran.modules.evStocks.entity.EvStocks;
import com.youran.modules.evStocks.service.IEvStocksService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 销售单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Service
public class EvSaleServiceImpl extends ServiceImpl<EvSaleMapper, EvSale> implements IEvSaleService {

    @Autowired
    private IEvSaleGoodsService evSaleGoodsService;
    @Autowired
    private IEvStocksService evStocksService;
    public boolean saveGoods(EvSaleVo evSaleVo){
        EvSale evSale = new EvSale();
        BeanUtils.copyProperties(evSaleVo,evSale);
        this.save(evSale);
        if(!evSaleVo.getEvSaleGoodsList().isEmpty()){
            List<EvSaleGoods> evSaleGoodsList = evSaleVo.getEvSaleGoodsList();
            evSaleGoodsList.forEach(evSaleGoods -> {
                evSaleGoods.setSaleId(evSale.getId());
                //减少库存
                LambdaQueryWrapper<EvStocks> evStocksQueryWrapper = new LambdaQueryWrapper<>();
                evStocksQueryWrapper.eq(EvStocks::getGoodsId,evSaleGoods.getGoodsId())
                        .eq(EvStocks::getDepartId,evSale.getDepartId());
                EvStocks evStocks = evStocksService.getOne(evStocksQueryWrapper);
                if(evStocks==null){
                    evStocks = new EvStocks();
                    evStocks.setGoodsId(evSaleGoods.getGoodsId());
                    evStocks.setNum(BigDecimal.ZERO.subtract(evSaleGoods.getNum()));
                    evStocks.setDepartId(evSale.getDepartId());
                    evStocksService.save(evStocks);
                }else{
                    evStocks.setNum(evStocks.getNum().add(evSaleGoods.getNum()));
                    evStocksService.updateById(evStocks);
                }

            });
            evSaleGoodsService.saveBatch(evSaleVo.getEvSaleGoodsList());
        }
        return true;
    }
    public boolean updateGoodsById(EvSaleVo evSaleVo){
        EvSale evSale = new EvSale();
        BeanUtils.copyProperties(evSaleVo,evSale);
        this.updateById(evSale);
        if(!evSaleVo.getEvSaleGoodsList().isEmpty()){
            evSaleGoodsService.saveOrUpdateBatch(evSaleVo.getEvSaleGoodsList());
        }
        return true;
    }

    public boolean removeGoodsById(String id){
        EvSale evSale = this.getById(id);
        this.removeById(id);
        //删除
        LambdaQueryWrapper<EvSaleGoods> evSaleGoodsQueryWrapper = new LambdaQueryWrapper<>();
        evSaleGoodsQueryWrapper.eq(EvSaleGoods::getSaleId,id);
        List<EvSaleGoods> evSaleGoodsList=evSaleGoodsService.list(evSaleGoodsQueryWrapper);
        if(!evSaleGoodsList.isEmpty()){
            evSaleGoodsList.forEach(evSaleGoods -> {
                //减少库存
                LambdaQueryWrapper<EvStocks> evStocksQueryWrapper = new LambdaQueryWrapper<>();
                evStocksQueryWrapper.eq(EvStocks::getGoodsId,evSaleGoods.getGoodsId())
                        .eq(EvStocks::getDepartId,evSale.getDepartId());
                EvStocks evStocks = evStocksService.getOne(evStocksQueryWrapper);
                if(evStocks==null){
                    evStocks = new EvStocks();
                    evStocks.setGoodsId(evSaleGoods.getGoodsId());
                    evStocks.setNum(evSaleGoods.getNum());
                    evStocks.setDepartId(evSale.getDepartId());
                    evStocksService.save(evStocks);
                }else{
                    evStocks.setNum(evStocks.getNum().add(evSaleGoods.getNum()));
                    evStocksService.updateById(evStocks);
                }
            });
            evSaleGoodsService.removeBatchByIds(evSaleGoodsList);
        }
//        Map<String,Object> columnMap = new HashMap<>();
//        columnMap.put("sale_id",id);
//        evSaleGoodsService.removeByMap(columnMap);
        return true;
    }

    public EvSaleVo getGoodsById(String id){
        EvSale evSale = this.getById(id);
        if(evSale==null) {
            return null;
        }
        EvSaleVo evSaleVo = new EvSaleVo();
        BeanUtils.copyProperties(evSale,evSaleVo);
        LambdaQueryWrapper<EvSaleGoods> evSaleGoodsQueryWrapper = new LambdaQueryWrapper<>();
        evSaleGoodsQueryWrapper.eq(EvSaleGoods::getSaleId,id);
        List<EvSaleGoods> evSaleGoodsList = evSaleGoodsService.list(evSaleGoodsQueryWrapper);
        evSaleVo.setEvSaleGoodsList(evSaleGoodsList);
        return evSaleVo;
    }
}
