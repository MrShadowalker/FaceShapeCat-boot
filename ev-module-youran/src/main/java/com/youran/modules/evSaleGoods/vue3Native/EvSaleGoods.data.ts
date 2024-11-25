import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '删除标志位',
    align: "center",
    dataIndex: 'delFalg'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remark'
  },
  {
    title: '商品',
    align: "center",
    dataIndex: 'goodsId'
  },
  {
    title: '商品数量',
    align: "center",
    dataIndex: 'num'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '删除标志位',
    field: 'delFalg',
    component: 'Input',
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
  {
    label: '商品',
    field: 'goodsId',
    component: 'Input',
  },
  {
    label: '商品数量',
    field: 'num',
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
  delFalg: {title: '删除标志位',order: 0,view: 'text', type: 'string',},
  remark: {title: '备注',order: 1,view: 'text', type: 'string',},
  goodsId: {title: '商品',order: 2,view: 'text', type: 'string',},
  num: {title: '商品数量',order: 3,view: 'text', type: 'string',},
};
