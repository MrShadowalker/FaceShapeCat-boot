package com.youran.modules.evAdjust.controller;

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
import com.youran.modules.evAdjust.entity.EvAdjust;
import com.youran.modules.evAdjust.service.IEvAdjustService;

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
 * @Description: 调货单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Api(tags="调货单表")
@RestController
@RequestMapping("/evAdjust")
@Slf4j
public class EvAdjustController extends JeecgController<EvAdjust, IEvAdjustService> {
	@Autowired
	private IEvAdjustService evAdjustService;

	/**
	 * 分页列表查询
	 *
	 * @param evAdjust
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "调货单表-分页列表查询")
	@ApiOperation(value="调货单表-分页列表查询", notes="调货单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvAdjust>> queryPageList(EvAdjust evAdjust,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvAdjust> queryWrapper = QueryGenerator.initQueryWrapper(evAdjust, req.getParameterMap());
		Page<EvAdjust> page = new Page<EvAdjust>(pageNo, pageSize);
		IPage<EvAdjust> pageList = evAdjustService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param evAdjust
	 * @return
	 */
	@AutoLog(value = "调货单表-添加")
	@ApiOperation(value="调货单表-添加", notes="调货单表-添加")
//	@RequiresPermissions("evAdjust:ev_adjust:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvAdjust evAdjust) {
		evAdjustService.save(evAdjust);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param evAdjust
	 * @return
	 */
	@AutoLog(value = "调货单表-编辑")
	@ApiOperation(value="调货单表-编辑", notes="调货单表-编辑")
//	@RequiresPermissions("evAdjust:ev_adjust:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvAdjust evAdjust) {
		evAdjustService.updateById(evAdjust);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "调货单表-通过id删除")
	@ApiOperation(value="调货单表-通过id删除", notes="调货单表-通过id删除")
//	@RequiresPermissions("evAdjust:ev_adjust:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		evAdjustService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "调货单表-批量删除")
	@ApiOperation(value="调货单表-批量删除", notes="调货单表-批量删除")
//	@RequiresPermissions("evAdjust:ev_adjust:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.evAdjustService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "调货单表-通过id查询")
	@ApiOperation(value="调货单表-通过id查询", notes="调货单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EvAdjust> queryById(@RequestParam(name="id",required=true) String id) {
		EvAdjust evAdjust = evAdjustService.getById(id);
		if(evAdjust==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(evAdjust);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param evAdjust
    */
//    @RequiresPermissions("evAdjust:ev_adjust:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvAdjust evAdjust) {
        return super.exportXls(request, evAdjust, EvAdjust.class, "调货单表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("evAdjust:ev_adjust:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvAdjust.class);
    }

}
