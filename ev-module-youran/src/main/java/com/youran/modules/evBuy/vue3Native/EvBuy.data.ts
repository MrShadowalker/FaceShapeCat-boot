import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '删除标志位',
    align: "center",
    dataIndex: 'delFlag'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remark'
  },
  {
    title: '采购单号',
    align: "center",
    dataIndex: 'code'
  },
  {
    title: '采购时间',
    align: "center",
    dataIndex: 'buyTime'
  },
  {
    title: '供货商',
    align: "center",
    dataIndex: 'supplier'
  },
  {
    title: '联系人',
    align: "center",
    dataIndex: 'contact'
  },
  {
    title: '联系电话',
    align: "center",
    dataIndex: 'phone'
  },
  {
    title: '联系地址',
    align: "center",
    dataIndex: 'address'
  },
  {
    title: '采购店铺/仓库',
    align: "center",
    dataIndex: 'shopId'
  },
  {
    title: '负责人',
    align: "center",
    dataIndex: 'buyUserId'
  },
  {
    title: '实际金额',
    align: "center",
    dataIndex: 'payMoney'
  },
  {
    title: '付款方式',
    align: "center",
    dataIndex: 'payType'
  },
  {
    title: '到货状态',
    align: "center",
    dataIndex: 'goodsStatus'
  },
  {
    title: '支付状态',
    align: "center",
    dataIndex: 'payStatus'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '删除标志位',
    field: 'delFlag',
    component: 'Input',
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
  {
    label: '采购单号',
    field: 'code',
    component: 'Input',
  },
  {
    label: '采购时间',
    field: 'buyTime',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
  },
  {
    label: '供货商',
    field: 'supplier',
    component: 'Input',
  },
  {
    label: '联系人',
    field: 'contact',
    component: 'Input',
  },
  {
    label: '联系电话',
    field: 'phone',
    component: 'Input',
  },
  {
    label: '联系地址',
    field: 'address',
    component: 'Input',
  },
  {
    label: '采购店铺/仓库',
    field: 'shopId',
    component: 'Input',
  },
  {
    label: '负责人',
    field: 'buyUserId',
    component: 'Input',
  },
  {
    label: '实际金额',
    field: 'payMoney',
    component: 'InputNumber',
  },
  {
    label: '付款方式',
    field: 'payType',
    component: 'Input',
  },
  {
    label: '到货状态',
    field: 'goodsStatus',
    component: 'Input',
  },
  {
    label: '支付状态',
    field: 'payStatus',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];

// 高级查询数据
export const superQuerySchema = {
  delFlag: {title: '删除标志位',order: 0,view: 'text', type: 'string',},
  remark: {title: '备注',order: 1,view: 'text', type: 'string',},
  code: {title: '采购单号',order: 2,view: 'text', type: 'string',},
  buyTime: {title: '采购时间',order: 3,view: 'datetime', type: 'string',},
  supplier: {title: '供货商',order: 4,view: 'text', type: 'string',},
  contact: {title: '联系人',order: 5,view: 'text', type: 'string',},
  phone: {title: '联系电话',order: 6,view: 'text', type: 'string',},
  address: {title: '联系地址',order: 7,view: 'text', type: 'string',},
  shopId: {title: '采购店铺/仓库',order: 8,view: 'text', type: 'string',},
  buyUserId: {title: '负责人',order: 9,view: 'text', type: 'string',},
  payMoney: {title: '实际金额',order: 10,view: 'number', type: 'number',},
  payType: {title: '付款方式',order: 11,view: 'text', type: 'string',},
  goodsStatus: {title: '到货状态',order: 12,view: 'text', type: 'string',},
  payStatus: {title: '支付状态',order: 13,view: 'text', type: 'string',},
};
