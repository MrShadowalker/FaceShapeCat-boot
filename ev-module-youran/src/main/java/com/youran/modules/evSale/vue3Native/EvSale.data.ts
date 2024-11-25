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
    title: '销售单号',
    align: "center",
    dataIndex: 'code'
  },
  {
    title: '销售时间',
    align: "center",
    dataIndex: 'saleTime'
  },
  {
    title: '客户类型',
    align: "center",
    dataIndex: 'customType'
  },
  {
    title: '客户姓名',
    align: "center",
    dataIndex: 'name'
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
    title: '店铺',
    align: "center",
    dataIndex: 'departId'
  },
  {
    title: '销售员',
    align: "center",
    dataIndex: 'saleUserId'
  },
  {
    title: '支付方式',
    align: "center",
    dataIndex: 'payType'
  },
  {
    title: '实际支付金额',
    align: "center",
    dataIndex: 'payMoney'
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
    label: '销售单号',
    field: 'code',
    component: 'Input',
  },
  {
    label: '销售时间',
    field: 'saleTime',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
  },
  {
    label: '客户类型',
    field: 'customType',
    component: 'Input',
  },
  {
    label: '客户姓名',
    field: 'name',
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
    label: '店铺',
    field: 'departId',
    component: 'Input',
  },
  {
    label: '销售员',
    field: 'saleUserId',
    component: 'Input',
  },
  {
    label: '支付方式',
    field: 'payType',
    component: 'Input',
  },
  {
    label: '实际支付金额',
    field: 'payMoney',
    component: 'InputNumber',
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
  code: {title: '销售单号',order: 2,view: 'text', type: 'string',},
  saleTime: {title: '销售时间',order: 3,view: 'datetime', type: 'string',},
  customType: {title: '客户类型',order: 4,view: 'text', type: 'string',},
  name: {title: '客户姓名',order: 5,view: 'text', type: 'string',},
  phone: {title: '联系电话',order: 6,view: 'text', type: 'string',},
  address: {title: '联系地址',order: 7,view: 'text', type: 'string',},
  departId: {title: '店铺',order: 8,view: 'text', type: 'string',},
  saleUserId: {title: '销售员',order: 9,view: 'text', type: 'string',},
  payType: {title: '支付方式',order: 10,view: 'text', type: 'string',},
  payMoney: {title: '实际支付金额',order: 11,view: 'number', type: 'number',},
};
