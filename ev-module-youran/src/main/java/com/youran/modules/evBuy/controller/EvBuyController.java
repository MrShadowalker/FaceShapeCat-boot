package com.youran.modules.evBuy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youran.modules.evBuy.entity.EvBuyVO;
import com.youran.modules.evBuyGoods.entity.EvBuyGoods;
import com.youran.modules.evBuyGoods.service.IEvBuyGoodsService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import com.youran.modules.evBuy.entity.EvBuy;
import com.youran.modules.evBuy.service.IEvBuyService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 采购表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Api(tags="采购表")
@RestController
@RequestMapping("/evBuy")
@Slf4j
public class EvBuyController extends JeecgController<EvBuy, IEvBuyService> {
	@Autowired
	private IEvBuyService evBuyService;

	@Autowired
	private IEvBuyGoodsService evBuyGoodsService;

	/**
	 * 分页列表查询
	 *
	 * @param evBuy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "采购表-分页列表查询")
	@ApiOperation(value="采购表-分页列表查询", notes="采购表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvBuyVO>> queryPageList(EvBuy evBuy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvBuy> queryWrapper = QueryGenerator.initQueryWrapper(evBuy, req.getParameterMap());
		Page<EvBuy> page = new Page<EvBuy>(pageNo, pageSize);
		IPage<EvBuy> pageList = evBuyService.page(page, queryWrapper);
		IPage<EvBuyVO> pageVoList = pageList.convert(item->{
			EvBuyVO evBuyVO = new EvBuyVO();
			BeanUtils.copyProperties(item,evBuyVO);
			LambdaQueryWrapper<EvBuyGoods> evBuyGoodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
			evBuyGoodsLambdaQueryWrapper.eq(EvBuyGoods::getBuyId,item.getId());
			List<EvBuyGoods> evBuyGoodsList = evBuyGoodsService.list(evBuyGoodsLambdaQueryWrapper);
			evBuyVO.setEvBuyGoodsList(evBuyGoodsList);
			return evBuyVO;
		});
		return Result.OK(pageVoList);
	}

	/**
	 *   添加
	 *
	 * @param evBuy
	 * @return
	 */
	@AutoLog(value = "采购表-添加")
	@ApiOperation(value="采购表-添加", notes="采购表-添加")
//	@RequiresPermissions("evBuy:ev_buy:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvBuy evBuy) {
		evBuyService.save(evBuy);
		return Result.OK("添加成功！");
	}
	 @AutoLog(value = "采购表-添加商品")
	 @ApiOperation(value="采购表-添加商品", notes="采购表-添加商品")
//	@RequiresPermissions("evBuy:ev_buy:add")
	 @PostMapping(value = "/addGoods")
	 public Result<String> addGoods(@RequestBody EvBuyVO evBuyVO) {
		 evBuyService.saveGoods(evBuyVO);
		 return Result.OK("添加成功！");
	 }

	/**
	 *  编辑
	 *
	 * @param evBuy
	 * @return
	 */
	@AutoLog(value = "采购表-编辑")
	@ApiOperation(value="采购表-编辑", notes="采购表-编辑")
//	@RequiresPermissions("evBuy:ev_buy:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvBuy evBuy) {
		evBuyService.updateById(evBuy);
		return Result.OK("编辑成功!");
	}
	 @AutoLog(value = "采购表-编辑")
	 @ApiOperation(value="采购表-编辑", notes="采购表-编辑")
//	@RequiresPermissions("evBuy:ev_buy:edit")
	 @RequestMapping(value = "/editGoods", method = {RequestMethod.PUT,RequestMethod.POST})
	 public Result<String> editGoods(@RequestBody EvBuyVO evBuyVo) {
		 evBuyService.updateGoodsById(evBuyVo);
		 return Result.OK("编辑成功!");
	 }

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "采购表-通过id删除")
	@ApiOperation(value="采购表-通过id删除", notes="采购表-通过id删除")
//	@RequiresPermissions("evBuy:ev_buy:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {

		evBuyService.removeGoodsByBuyId(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
//	@AutoLog(value = "采购表-批量删除")
//	@ApiOperation(value="采购表-批量删除", notes="采购表-批量删除")
////	@RequiresPermissions("evBuy:ev_buy:deleteBatch")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.evBuyService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.OK("批量删除成功!");
//	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "采购表-通过id查询")
	@ApiOperation(value="采购表-通过id查询", notes="采购表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EvBuy evBuy = evBuyService.getById(id);
		if(evBuy==null) {
			return Result.error("未找到对应数据");
		}
		EvBuyVO evBuyVO = new EvBuyVO();
		BeanUtils.copyProperties(evBuy,evBuyVO);
		LambdaQueryWrapper<EvBuyGoods> evBuyGoodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
		evBuyGoodsLambdaQueryWrapper.eq(EvBuyGoods::getBuyId,evBuy.getId());
		List<EvBuyGoods> evBuyGoodsList = evBuyGoodsService.list(evBuyGoodsLambdaQueryWrapper);
		evBuyVO.setEvBuyGoodsList(evBuyGoodsList);
		return Result.OK(evBuyVO);
	}
	 @ApiOperation(value="采购表-通过id查询", notes="采购表-通过id查询")
	 @GetMapping(value = "/queryGoodsById")
	 public Result<EvBuyVO> queryGoodsById(@RequestParam(name="id",required=true) String id) {
		 EvBuyVO evBuyVo = evBuyService.getGoodsById(id);
		 if(evBuyVo==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.OK(evBuyVo);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param evBuy
    */
//    @RequiresPermissions("evBuy:ev_buy:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvBuy evBuy) {
        return super.exportXls(request, evBuy, EvBuy.class, "采购表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("evBuy:ev_buy:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvBuy.class);
    }

}
