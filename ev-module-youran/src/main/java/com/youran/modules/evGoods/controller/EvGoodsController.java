package com.youran.modules.evGoods.controller;

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
import com.youran.modules.evGoods.entity.EvGoods;
import com.youran.modules.evGoods.service.IEvGoodsService;

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
 * @Description: 商品表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Api(tags="商品表")
@RestController
@RequestMapping("/evGoods")
@Slf4j
public class EvGoodsController extends JeecgController<EvGoods, IEvGoodsService> {
	@Autowired
	private IEvGoodsService evGoodsService;

	/**
	 * 分页列表查询
	 *
	 * @param evGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "商品表-分页列表查询")
	@ApiOperation(value="商品表-分页列表查询", notes="商品表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvGoods>> queryPageList(EvGoods evGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvGoods> queryWrapper = QueryGenerator.initQueryWrapper(evGoods, req.getParameterMap());
		Page<EvGoods> page = new Page<EvGoods>(pageNo, pageSize);
		IPage<EvGoods> pageList = evGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param evGoods
	 * @return
	 */
	@AutoLog(value = "商品表-添加")
	@ApiOperation(value="商品表-添加", notes="商品表-添加")
//	@RequiresPermissions("evGoods:ev_goods:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvGoods evGoods) {
		evGoodsService.save(evGoods);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param evGoods
	 * @return
	 */
	@AutoLog(value = "商品表-编辑")
	@ApiOperation(value="商品表-编辑", notes="商品表-编辑")
//	@RequiresPermissions("evGoods:ev_goods:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvGoods evGoods) {
		evGoodsService.updateById(evGoods);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品表-通过id删除")
	@ApiOperation(value="商品表-通过id删除", notes="商品表-通过id删除")
//	@RequiresPermissions("evGoods:ev_goods:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		evGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品表-批量删除")
	@ApiOperation(value="商品表-批量删除", notes="商品表-批量删除")
//	@RequiresPermissions("evGoods:ev_goods:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.evGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "商品表-通过id查询")
	@ApiOperation(value="商品表-通过id查询", notes="商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EvGoods> queryById(@RequestParam(name="id",required=true) String id) {
		EvGoods evGoods = evGoodsService.getById(id);
		if(evGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(evGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param evGoods
    */
//    @RequiresPermissions("evGoods:ev_goods:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvGoods evGoods) {
        return super.exportXls(request, evGoods, EvGoods.class, "商品表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("evGoods:ev_goods:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvGoods.class);
    }

}
