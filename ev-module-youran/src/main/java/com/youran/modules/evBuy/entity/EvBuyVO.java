package com.youran.modules.evBuy.entity;

import com.youran.modules.evBuyGoods.entity.EvBuyGoods;
import lombok.Data;

import java.util.List;

@Data
public class EvBuyVO extends EvBuy {

    private List<EvBuyGoods> evBuyGoodsList;

}
