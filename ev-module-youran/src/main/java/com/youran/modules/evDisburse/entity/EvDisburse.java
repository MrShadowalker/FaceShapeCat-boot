package com.youran.modules.evDisburse.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 损耗单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Data
@TableName("ev_disburse")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ev_disburse对象", description="损耗单表")
public class EvDisburse implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**删除标志位*/
	@Excel(name = "删除标志位", width = 15)
    @ApiModelProperty(value = "删除标志位")
    @TableLogic
    private Integer delFlag;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
    /**店铺*/
    @Excel(name = "店铺", width = 15)
    @ApiModelProperty(value = "店铺")
    @Dict(dictTable = "sys_depart",dicCode = "id",dicText = "depart_name")
    private java.lang.String departId;
	/**商品*/
	@Excel(name = "商品", width = 15)
    @ApiModelProperty(value = "商品")
    @Dict(dictTable = "ev_goods",dicCode = "id",dicText = "name")
    private java.lang.String goodsId;
	/**损耗时间*/
	@Excel(name = "损耗时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "损耗时间")
    private java.util.Date disburseTime;
	/**损耗类型*/
	@Excel(name = "损耗类型", width = 15)
    @ApiModelProperty(value = "损耗类型")
    private java.lang.String type;
	/**损耗数量*/
	@Excel(name = "损耗数量", width = 15)
    @ApiModelProperty(value = "损耗数量")
    private BigDecimal num;
	/**损耗原因*/
	@Excel(name = "损耗原因", width = 15)
    @ApiModelProperty(value = "损耗原因")
    private java.lang.String reason;
	/**损耗状态*/
	@Excel(name = "损耗状态", width = 15)
    @ApiModelProperty(value = "损耗状态")
    private java.lang.String status;
    /**填报人*/
    @Excel(name = "填报人", width = 15)
    @ApiModelProperty(value = "填报人")
    private java.lang.String user;
    /**支出标题*/
    @Excel(name = "支出标题", width = 15)
    @ApiModelProperty(value = "支出标题")
    private java.lang.String title;
    /**支出凭证*/
    @Excel(name = "支出凭证", width = 15)
    @ApiModelProperty(value = "支出凭证")
    private java.lang.String img;
    /**支出金额*/
    @Excel(name = "支出金额", width = 15)
    @ApiModelProperty(value = "支出金额")
    private BigDecimal money;
}
