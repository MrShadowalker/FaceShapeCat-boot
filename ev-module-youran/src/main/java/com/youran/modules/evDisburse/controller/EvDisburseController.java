package com.youran.modules.evDisburse.controller;

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
import com.youran.modules.evDisburse.entity.EvDisburse;
import com.youran.modules.evDisburse.service.IEvDisburseService;

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
 * @Description: 损耗单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Api(tags="损耗单表")
@RestController
@RequestMapping("/evDisburse")
@Slf4j
public class EvDisburseController extends JeecgController<EvDisburse, IEvDisburseService> {
	@Autowired
	private IEvDisburseService evDisburseService;

	/**
	 * 分页列表查询
	 *
	 * @param evDisburse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "损耗单表-分页列表查询")
	@ApiOperation(value="损耗单表-分页列表查询", notes="损耗单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<EvDisburse>> queryPageList(EvDisburse evDisburse,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EvDisburse> queryWrapper = QueryGenerator.initQueryWrapper(evDisburse, req.getParameterMap());
		Page<EvDisburse> page = new Page<EvDisburse>(pageNo, pageSize);
		IPage<EvDisburse> pageList = evDisburseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param evDisburse
	 * @return
	 */
	@AutoLog(value = "损耗单表-添加")
	@ApiOperation(value="损耗单表-添加", notes="损耗单表-添加")
//	@RequiresPermissions("evDisburse:ev_disburse:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody EvDisburse evDisburse) {
		evDisburseService.save(evDisburse);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param evDisburse
	 * @return
	 */
	@AutoLog(value = "损耗单表-编辑")
	@ApiOperation(value="损耗单表-编辑", notes="损耗单表-编辑")
//	@RequiresPermissions("evDisburse:ev_disburse:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody EvDisburse evDisburse) {
		evDisburseService.updateById(evDisburse);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "损耗单表-通过id删除")
	@ApiOperation(value="损耗单表-通过id删除", notes="损耗单表-通过id删除")
//	@RequiresPermissions("evDisburse:ev_disburse:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		evDisburseService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "损耗单表-批量删除")
	@ApiOperation(value="损耗单表-批量删除", notes="损耗单表-批量删除")
//	@RequiresPermissions("evDisburse:ev_disburse:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.evDisburseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "损耗单表-通过id查询")
	@ApiOperation(value="损耗单表-通过id查询", notes="损耗单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<EvDisburse> queryById(@RequestParam(name="id",required=true) String id) {
		EvDisburse evDisburse = evDisburseService.getById(id);
		if(evDisburse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(evDisburse);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param evDisburse
    */
//    @RequiresPermissions("evDisburse:ev_disburse:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EvDisburse evDisburse) {
        return super.exportXls(request, evDisburse, EvDisburse.class, "损耗单表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
//    @RequiresPermissions("evDisburse:ev_disburse:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EvDisburse.class);
    }

}
