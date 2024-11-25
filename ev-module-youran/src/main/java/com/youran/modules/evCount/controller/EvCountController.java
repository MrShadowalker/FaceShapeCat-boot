package com.youran.modules.evCount.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 统计表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */

@Api(tags="统计表")
@RestController
@RequestMapping("/evCount")
@Slf4j
public class EvCountController {

    /**
     * 按店铺统计月销售金额及总金额
     */
    @ApiOperation(value = "统计表-按月查询销售额",notes = "统计表-按月查询销售额")
    @GetMapping(value = "/saleMoneyList")
    public Result querySale(){
        return Result.OK();
    }

    /**
     * 按店铺统计库存的金额（按零售价算）
     */

    /**
     * 按店铺统计月损耗金额变化
     */

}
