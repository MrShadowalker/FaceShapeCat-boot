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
    title: '商品名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '商品种类',
    align: "center",
    dataIndex: 'type'
  },
  {
    title: '商品描述',
    align: "center",
    dataIndex: 'description'
  },
  {
    title: '计量单位',
    align: "center",
    dataIndex: 'unit'
  },
  {
    title: '规格型号',
    align: "center",
    dataIndex: 'spec'
  },
  {
    title: '商品图片',
    align: "center",
    dataIndex: 'img'
  },
  {
    title: '商品编码',
    align: "center",
    dataIndex: 'code'
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
    title: '零售价',
    align: "center",
    dataIndex: 'price'
  },
  {
    title: '成本价',
    align: "center",
    dataIndex: 'costPrice'
  },
  {
    title: '商品状态',
    align: "center",
    dataIndex: 'status'
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
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入删除标志位!'},
             ];
    },
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
  {
    label: '商品名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '商品种类',
    field: 'type',
    component: 'Input',
  },
  {
    label: '商品描述',
    field: 'description',
    component: 'Input',
  },
  {
    label: '计量单位',
    field: 'unit',
    component: 'Input',
  },
  {
    label: '规格型号',
    field: 'spec',
    component: 'Input',
  },
  {
    label: '商品图片',
    field: 'img',
    component: 'Input',
  },
  {
    label: '商品编码',
    field: 'code',
    component: 'Input',
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
    label: '零售价',
    field: 'price',
    component: 'InputNumber',
  },
  {
    label: '成本价',
    field: 'costPrice',
    component: 'InputNumber',
  },
  {
    label: '商品状态',
    field: 'status',
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
  name: {title: '商品名称',order: 2,view: 'text', type: 'string',},
  type: {title: '商品种类',order: 3,view: 'text', type: 'string',},
  description: {title: '商品描述',order: 4,view: 'text', type: 'string',},
  unit: {title: '计量单位',order: 5,view: 'text', type: 'string',},
  spec: {title: '规格型号',order: 6,view: 'text', type: 'string',},
  img: {title: '商品图片',order: 7,view: 'text', type: 'string',},
  code: {title: '商品编码',order: 8,view: 'text', type: 'string',},
  supplier: {title: '供货商',order: 9,view: 'text', type: 'string',},
  contact: {title: '联系人',order: 10,view: 'text', type: 'string',},
  phone: {title: '联系电话',order: 11,view: 'text', type: 'string',},
  address: {title: '联系地址',order: 12,view: 'text', type: 'string',},
  price: {title: '零售价',order: 13,view: 'number', type: 'number',},
  costPrice: {title: '成本价',order: 14,view: 'number', type: 'number',},
  status: {title: '商品状态',order: 15,view: 'text', type: 'string',},
};
