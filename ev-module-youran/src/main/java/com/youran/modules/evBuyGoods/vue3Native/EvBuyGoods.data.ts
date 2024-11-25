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
    title: '采购单ID',
    align: "center",
    dataIndex: 'buyId'
  },
  {
    title: '商品ID',
    align: "center",
    dataIndex: 'goodsId'
  },
  {
    title: '数量',
    align: "center",
    dataIndex: 'num'
  },
  {
    title: '单价',
    align: "center",
    dataIndex: 'price'
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
    label: '采购单ID',
    field: 'buyId',
    component: 'Input',
  },
  {
    label: '商品ID',
    field: 'goodsId',
    component: 'Input',
  },
  {
    label: '数量',
    field: 'num',
    component: 'InputNumber',
  },
  {
    label: '单价',
    field: 'price',
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
  buyId: {title: '采购单ID',order: 2,view: 'text', type: 'string',},
  goodsId: {title: '商品ID',order: 3,view: 'text', type: 'string',},
  num: {title: '数量',order: 4,view: 'number', type: 'number',},
  price: {title: '单价',order: 5,view: 'number', type: 'number',},
};
