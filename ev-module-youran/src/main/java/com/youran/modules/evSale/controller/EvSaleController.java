package com.youran.modules.evSale.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youran.modules.evSale.vo.EvSaleVo;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import com.youran.modules.evSale.entity.EvSale;
import com.youran.modules.evSale.service.IEvSaleService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 销售单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Api(tags="销售单表")
@RestController
@RequestMapping("/evSale")
@Slf4j
public class EvSaleController extends JeecgController<EvSale, IEvSaleService> {
	@Autowired
	private IEvSaleService evSaleService;

	/**
	 * 分页列表查询
	 *
	 * @param evSale
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "销售单表-分页列表查询")
	@ApiOperation(value="销售单表-分页列表查询", notes="销售单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvSale>> queryPageList(EvSale evSale,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvSale> queryWrapper = QueryGenerator.initQueryWrapper(evSale, req.getParameterMap());
		Page<EvSale> page = new Page<EvSale>(pageNo, pageSize);
		IPage<EvSale> pageList = evSaleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param evSale
	 * @return
	 */
	@AutoLog(value = "销售单表-添加")
	@ApiOperation(value="销售单表-添加", notes="销售单表-添加")
//	@RequiresPermissions("evSale:ev_sale:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvSale evSale) {
		evSaleService.save(evSale);
		return Result.OK("添加成功！");
	}
	 @AutoLog(value = "销售单表-添加")
	 @ApiOperation(value="销售单表-添加", notes="销售单表-添加")
//	@RequiresPermissions("evSale:ev_sale:add")
	 @PostMapping(value = "/addGoods")
	 public Result<String> addGoods(@RequestBody EvSaleVo evSaleVo) {
		 evSaleService.saveGoods(evSaleVo);
		 return Result.OK("添加成功！");
	 }

	/**
	 *  编辑
	 *
	 * @param evSale
	 * @return
	 */
	@AutoLog(value = "销售单表-编辑")
	@ApiOperation(value="销售单表-编辑", notes="销售单表-编辑")
//	@RequiresPermissions("evSale:ev_sale:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvSale evSale) {
		evSaleService.updateById(evSale);
		return Result.OK("编辑成功!");
	}

	 @AutoLog(value = "销售单表-编辑")
	 @ApiOperation(value="销售单表-编辑", notes="销售单表-编辑")
//	@RequiresPermissions("evSale:ev_sale:edit")
	 @RequestMapping(value = "/editGoods", method = {RequestMethod.PUT,RequestMethod.POST})
	 public Result<String> editGoods(@RequestBody EvSaleVo evSaleVo) {
		 evSaleService.updateGoodsById(evSaleVo);
		 return Result.OK("编辑成功!");
	 }

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "销售单表-通过id删除")
	@ApiOperation(value="销售单表-通过id删除", notes="销售单表-通过id删除")
//	@RequiresPermissions("evSale:ev_sale:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		evSaleService.removeGoodsById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
//	@AutoLog(value = "销售单表-批量删除")
//	@ApiOperation(value="销售单表-批量删除", notes="销售单表-批量删除")
////	@RequiresPermissions("evSale:ev_sale:deleteBatch")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.evSaleService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.OK("批量删除成功!");
//	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "销售单表-通过id查询")
	@ApiOperation(value="销售单表-通过id查询", notes="销售单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EvSale> queryById(@RequestParam(name="id",required=true) String id) {
		EvSale evSale = evSaleService.getById(id);
		if(evSale==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(evSale);
	}
	 @ApiOperation(value="销售单表-通过id查询", notes="销售单表-通过id查询")
	 @GetMapping(value = "/queryGoodsById")
	 public Result<EvSaleVo> queryGoodsById(@RequestParam(name="id",required=true) String id) {
		 EvSaleVo evSaleVo = evSaleService.getGoodsById(id);
		 if(evSaleVo==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.OK(evSaleVo);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param evSale
    */
//    @RequiresPermissions("evSale:ev_sale:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvSale evSale) {
        return super.exportXls(request, evSale, EvSale.class, "销售单表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("evSale:ev_sale:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvSale.class);
    }

}
