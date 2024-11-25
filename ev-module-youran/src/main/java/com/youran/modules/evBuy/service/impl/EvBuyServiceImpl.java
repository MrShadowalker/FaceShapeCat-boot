package com.youran.modules.evBuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youran.modules.evBuy.entity.EvBuy;
import com.youran.modules.evBuy.entity.EvBuyVO;
import com.youran.modules.evBuy.mapper.EvBuyMapper;
import com.youran.modules.evBuy.service.IEvBuyService;
import com.youran.modules.evBuyGoods.entity.EvBuyGoods;
import com.youran.modules.evBuyGoods.service.IEvBuyGoodsService;
import com.youran.modules.evStocks.entity.EvStocks;
import com.youran.modules.evStocks.service.IEvStocksService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 采购表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Service
public class EvBuyServiceImpl extends ServiceImpl<EvBuyMapper, EvBuy> implements IEvBuyService {

    @Autowired
    private IEvBuyGoodsService evBuyGoodsService;
    @Autowired
    private IEvStocksService evStocksService;

    public boolean saveGoods(EvBuyVO evBuyVO){
        EvBuy evBuy = new EvBuy();
        BeanUtils.copyProperties(evBuyVO,evBuy);
        this.save(evBuy);
        if(!evBuyVO.getEvBuyGoodsList().isEmpty()){
            List<EvBuyGoods> evBuyGoodsList = evBuyVO.getEvBuyGoodsList();
            evBuyGoodsList.forEach(evBuyGoods -> {
                evBuyGoods.setBuyId(evBuy.getId());
                //增加库存
                LambdaQueryWrapper<EvStocks> evStocksQueryWrapper = new LambdaQueryWrapper<>();
                evStocksQueryWrapper.eq(EvStocks::getGoodsId,evBuyGoods.getGoodsId())
                        .eq(EvStocks::getDepartId,evBuy.getShopId());
                EvStocks evStocks = evStocksService.getOne(evStocksQueryWrapper);
                if(evStocks==null){
                    evStocks = new EvStocks();
                    evStocks.setGoodsId(evBuyGoods.getGoodsId());
                    evStocks.setNum(evBuyGoods.getNum());
                    evStocks.setDepartId(evBuy.getShopId());
                    evStocksService.save(evStocks);
                }else{
                    evStocks.setNum(evStocks.getNum().add(evBuyGoods.getNum()));
                    evStocksService.updateById(evStocks);
                }
            });
            evBuyGoodsService.saveBatch(evBuyGoodsList);
        }
        return true;
    }

    public boolean updateGoodsById(EvBuyVO evBuyVO){
        EvBuy evBuy = new EvBuy();
        BeanUtils.copyProperties(evBuyVO,evBuy);
        this.updateById(evBuy);
        LambdaQueryWrapper<EvBuyGoods> evBuyGoodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        evBuyGoodsLambdaQueryWrapper.eq(EvBuyGoods::getBuyId,evBuy.getId());
        List<EvBuyGoods> evBuyGoodsList = evBuyGoodsService.list(evBuyGoodsLambdaQueryWrapper);
        evBuyGoodsService.removeBatchByIds(evBuyGoodsList);
        if(!evBuyVO.getEvBuyGoodsList().isEmpty()){
            evBuyGoodsService.saveOrUpdateBatch(evBuyVO.getEvBuyGoodsList());
        }
        return true;
    }

    public boolean removeGoodsByBuyId(String id){
        EvBuy evBuy = this.getById(id);
        this.removeById(id);
        //删除
        LambdaQueryWrapper<EvBuyGoods> evBuyGoodsQueryWrapper = new LambdaQueryWrapper<>();
        evBuyGoodsQueryWrapper.eq(EvBuyGoods::getBuyId,id);
        List<EvBuyGoods> evBuyGoodsList=evBuyGoodsService.list(evBuyGoodsQueryWrapper);
        if(!evBuyGoodsList.isEmpty()){
            evBuyGoodsList.forEach(evBuyGoods -> {
                //减少库存
                LambdaQueryWrapper<EvStocks> evStocksQueryWrapper = new LambdaQueryWrapper<>();
                evStocksQueryWrapper.eq(EvStocks::getGoodsId,evBuyGoods.getGoodsId())
                        .eq(EvStocks::getDepartId,evBuy.getShopId());
                EvStocks evStocks = evStocksService.getOne(evStocksQueryWrapper);
                if(evStocks==null){
                    evStocks = new EvStocks();
                    evStocks.setGoodsId(evBuyGoods.getGoodsId());
                    evStocks.setNum(BigDecimal.ZERO.subtract(evBuyGoods.getNum()));
                    evStocks.setDepartId(evBuy.getShopId());
                    evStocksService.save(evStocks);
                }else{
                    evStocks.setNum(evStocks.getNum().subtract(evBuyGoods.getNum()));
                    evStocksService.updateById(evStocks);
                }
            });
            evBuyGoodsService.removeBatchByIds(evBuyGoodsList);
        }
//        Map<String,Object> columnMap = new HashMap<>();
//        columnMap.put("buy_id",id);
//        evBuyGoodsService.removeByMap(columnMap);
        return true;
    }

    public EvBuyVO getGoodsById(String id){
        EvBuy evBuy = this.getById(id);
        if(evBuy==null) {
            return null;
        }
        EvBuyVO evBuyVo = new EvBuyVO();
        BeanUtils.copyProperties(evBuy,evBuyVo);
        LambdaQueryWrapper<EvBuyGoods> evBuyGoodsQueryWrapper = new LambdaQueryWrapper<>();
        evBuyGoodsQueryWrapper.eq(EvBuyGoods::getBuyId,id);
        List<EvBuyGoods> evBuyGoodsList = evBuyGoodsService.list(evBuyGoodsQueryWrapper);
        evBuyVo.setEvBuyGoodsList(evBuyGoodsList);
        return evBuyVo;
    }
}
