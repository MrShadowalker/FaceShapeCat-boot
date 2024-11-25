package com.youran.modules.evCustom.controller;

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
import com.youran.modules.evCustom.entity.EvCustom;
import com.youran.modules.evCustom.service.IEvCustomService;

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
 * @Description: 客户表
 * @Author: jeecg-boot
 * @Date:   2024-10-13
 * @Version: V1.0
 */
@Api(tags="客户表")
@RestController
@RequestMapping("/evCustom")
@Slf4j
public class EvCustomController extends JeecgController<EvCustom, IEvCustomService> {
	@Autowired
	private IEvCustomService evCustomService;

	/**
	 * 分页列表查询
	 *
	 * @param evCustom
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "客户表-分页列表查询")
	@ApiOperation(value="客户表-分页列表查询", notes="客户表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvCustom>> queryPageList(EvCustom evCustom,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvCustom> queryWrapper = QueryGenerator.initQueryWrapper(evCustom, req.getParameterMap());
		Page<EvCustom> page = new Page<EvCustom>(pageNo, pageSize);
		IPage<EvCustom> pageList = evCustomService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param evCustom
	 * @return
	 */
	@AutoLog(value = "客户表-添加")
	@ApiOperation(value="客户表-添加", notes="客户表-添加")
//	@RequiresPermissions("com.youran.modules:ev_custom:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvCustom evCustom) {
		evCustomService.save(evCustom);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param evCustom
	 * @return
	 */
	@AutoLog(value = "客户表-编辑")
	@ApiOperation(value="客户表-编辑", notes="客户表-编辑")
//	@RequiresPermissions("com.youran.modules:ev_custom:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvCustom evCustom) {
		evCustomService.updateById(evCustom);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户表-通过id删除")
	@ApiOperation(value="客户表-通过id删除", notes="客户表-通过id删除")
//	@RequiresPermissions("com.youran.modules:ev_custom:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		evCustomService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户表-批量删除")
	@ApiOperation(value="客户表-批量删除", notes="客户表-批量删除")
//	@RequiresPermissions("com.youran.modules:ev_custom:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.evCustomService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "客户表-通过id查询")
	@ApiOperation(value="客户表-通过id查询", notes="客户表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EvCustom> queryById(@RequestParam(name="id",required=true) String id) {
		EvCustom evCustom = evCustomService.getById(id);
		if(evCustom==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(evCustom);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param evCustom
    */
//    @RequiresPermissions("com.youran.modules:ev_custom:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvCustom evCustom) {
        return super.exportXls(request, evCustom, EvCustom.class, "客户表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("com.youran.modules:ev_custom:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvCustom.class);
    }

}
