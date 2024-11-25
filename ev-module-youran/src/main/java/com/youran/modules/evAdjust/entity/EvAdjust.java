package com.youran.modules.evAdjust.entity;

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
 * @Description: 调货单表
 * @Author: jeecg-boot
 * @Date:   2024-09-01
 * @Version: V1.0
 */
@Data
@TableName("ev_adjust")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ev_adjust对象", description="调货单表")
public class EvAdjust implements Serializable {
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
	/**供货店铺*/
	@Excel(name = "供货店铺", width = 15)
    @ApiModelProperty(value = "供货店铺")
    @Dict(dictTable = "sys_depart",dicCode = "id",dicText = "depart_name")
    private java.lang.String fromId;
	/**收货店铺*/
	@Excel(name = "收货店铺", width = 15)
    @Dict(dictTable = "sys_depart",dicCode = "id",dicText = "depart_name")
    @ApiModelProperty(value = "收货店铺")
    private java.lang.String toId;
	/**商品*/
	@Excel(name = "商品", width = 15)
    @ApiModelProperty(value = "商品")
    @Dict(dictTable = "ev_goods",dicCode = "id",dicText = "name")
    private java.lang.String goodsId;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private BigDecimal num;
	/**调货日期*/
	@Excel(name = "调货日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "调货日期")
    private java.util.Date adjustTime;
	/**送货人*/
	@Excel(name = "送货人", width = 15)
    @ApiModelProperty(value = "送货人")
    @Dict(dictTable = "sys_user",dicCode = "id",dicText = "realname")
    private java.lang.String fromUserId;
    /**收货人*/
    @Excel(name = "收货人", width = 15)
    @ApiModelProperty(value = "收货人")
    @Dict(dictTable = "sys_user",dicCode = "id",dicText = "realname")
    private java.lang.String toUserId;
    /**调货凭证*/
    @Excel(name = "调货凭证", width = 15)
    @ApiModelProperty(value = "调货凭证")
    private java.lang.String img;
}
