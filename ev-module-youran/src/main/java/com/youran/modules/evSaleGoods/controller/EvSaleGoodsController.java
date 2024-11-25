package com.youran.modules.evSaleGoods.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.youran.modules.evSaleGoods.entity.EvSaleGoods;
import com.youran.modules.evSaleGoods.service.IEvSaleGoodsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 销售单商品表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Api(tags="销售单商品表")
@RestController
@RequestMapping("/evSaleGoods")
@Slf4j
public class EvSaleGoodsController extends JeecgController<EvSaleGoods, IEvSaleGoodsService> {
	@Autowired
	private IEvSaleGoodsService evSaleGoodsService;

	/**
	 * 分页列表查询
	 *
	 * @param evSaleGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "销售单商品表-分页列表查询")
	@ApiOperation(value="销售单商品表-分页列表查询", notes="销售单商品表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvSaleGoods>> queryPageList(EvSaleGoods evSaleGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvSaleGoods> queryWrapper = QueryGenerator.initQueryWrapper(evSaleGoods, req.getParameterMap());
		Page<EvSaleGoods> page = new Page<EvSaleGoods>(pageNo, pageSize);
		IPage<EvSaleGoods> pageList = evSaleGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param evSaleGoods
	 * @return
	 */
	@AutoLog(value = "销售单商品表-添加")
	@ApiOperation(value="销售单商品表-添加", notes="销售单商品表-添加")
//	@RequiresPermissions("evSaleGoods:ev_sale_goods:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvSaleGoods evSaleGoods) {
		evSaleGoodsService.save(evSaleGoods);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param evSaleGoods
	 * @return
	 */
	@AutoLog(value = "销售单商品表-编辑")
	@ApiOperation(value="销售单商品表-编辑", notes="销售单商品表-编辑")
//	@RequiresPermissions("evSaleGoods:ev_sale_goods:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvSaleGoods evSaleGoods) {
		evSaleGoodsService.updateById(evSaleGoods);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "销售单商品表-通过id删除")
	@ApiOperation(value="销售单商品表-通过id删除", notes="销售单商品表-通过id删除")
//	@RequiresPermissions("evSaleGoods:ev_sale_goods:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		evSaleGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "销售单商品表-批量删除")
	@ApiOperation(value="销售单商品表-批量删除", notes="销售单商品表-批量删除")
//	@RequiresPermissions("evSaleGoods:ev_sale_goods:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.evSaleGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "销售单商品表-通过id查询")
	@ApiOperation(value="销售单商品表-通过id查询", notes="销售单商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EvSaleGoods> queryById(@RequestParam(name="id",required=true) String id) {
		EvSaleGoods evSaleGoods = evSaleGoodsService.getById(id);
		if(evSaleGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(evSaleGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param evSaleGoods
    */
//    @RequiresPermissions("evSaleGoods:ev_sale_goods:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvSaleGoods evSaleGoods) {
        return super.exportXls(request, evSaleGoods, EvSaleGoods.class, "销售单商品表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("evSaleGoods:ev_sale_goods:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvSaleGoods.class);
    }

}
