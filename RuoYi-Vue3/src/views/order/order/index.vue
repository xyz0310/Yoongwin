<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="技术员" prop="技术员">
        <el-select v-model="queryParams.技术员" clearable placeholder="请选择技术员">
          <el-option v-for="technician in technicians" :key="technician" :label="technician"
            :value="technician"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="业务员" prop="业务员">
        <el-select v-model="queryParams.业务员" clearable placeholder="请选择业务员">
          <el-option v-for="salesman in salesmen" :key="salesman" :label="salesman" :value="salesman"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="创建时间">
        <el-date-picker clearable v-model="queryParams.创建时间" type="date" value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['order:order:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['order:order:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['order:order:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['order:order:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="技术员" align="center" prop="技术员" />
      <el-table-column label="业务员" align="center" prop="业务员" />
      <el-table-column label="工单主题" align="center" prop="工单主题" show-overflow-tooltip />
      <el-table-column label="相关内容" align="center" prop="相关内容" show-overflow-tooltip />
      <el-table-column label="状态" align="center" prop="状态" show-overflow-tooltip />
      <el-table-column label="单号" align="center" prop="单号" />
      <el-table-column label="创建时间" align="center" prop="创建时间" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.创建时间, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['order:order:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['order:order:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改工单记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="技术员" prop="technician">
          <el-select v-model="form.technician" placeholder="请选择技术员" filterable allow-create
            @change="validateField('technician')" @blur="validateField('technician')">
            <el-option v-for="technician in technicians" :key="technician.id || technician"
              :label="technician.name || technician" :value="technician.id || technician"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务员" prop="salesman">
          <el-select v-model="form.salesman" placeholder="请选择业务员" filterable allow-create
            @change="validateField('salesman')" @blur="validateField('salesman')">
            <el-option v-for="salesman in salesmen" :key="salesman.id || salesman" :label="salesman.name || salesman"
              :value="salesman.id || salesman"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工单主题" prop="工单主题">
          <el-input v-model="form.工单主题" placeholder="请输入工单主题" />
        </el-form-item>
        <el-form-item label="相关内容" prop="相关内容">
          <el-input v-model="form.相关内容" placeholder="请输入相关内容" />
        </el-form-item>
        <el-form-item label="状态" prop="状态">
          <el-input v-model="form.状态" placeholder="请输入状态" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Order">
import { listOrder, getOrder, delOrder, addOrder, updateOrder, getTechnicians, getSalesmen } from "@/api/order/order"

const { proxy } = getCurrentInstance()

const orderList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const technicians = ref([])
const salesmen = ref([])


const data = reactive({
  form: {
    id: null,
    technician: null,     // 改为驼峰命名法，与验证规则保持一致
    salesman: null,       // 改为驼峰命名法，与验证规则保持一致
  },
  queryParams: {
    pageNum: 1,
    pageSize: 13,
    technician: null,     // 搜索表单字段也改为驼峰命名法
    salesman: null,       // 搜索表单字段也改为驼峰命名法
  },
  rules: {
    technician: [         // 验证规则字段名改为驼峰命名法
      { required: true, message: "技术员不能为空", trigger: ["change", "blur"] }
    ],
    salesman: [           // 验证规则字段名改为驼峰命名法
      { required: true, message: "业务员不能为空", trigger: ["change", "blur"] }
    ],
  }
})

// 手动触发表单字段验证
function validateField(field) {
  proxy.$refs["orderRef"].validateField(field)
}

const { queryParams, form, rules } = toRefs(data)

/** 查询工单记录列表 */
function getList() {
  loading.value = true
  listOrder(queryParams.value).then(response => {
    orderList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 获取技术员和业务员列表
function getTechniciansAndSalesmen() {
  getTechnicians().then(response => {
    technicians.value = response.data
  })
  getSalesmen().then(response => {
    salesmen.value = response.data
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    技术员: null,
    业务员: null,
    工单主题: null,
    相关内容: null,
    状态: null,
    单号: null,
  }
  proxy.resetForm("orderRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加工单记录"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value

  // 先获取技术员和业务员数据，确保下拉框选项已加载
  Promise.all([getTechnicians(), getSalesmen()]).then(results => {
    technicians.value = results[0].data
    salesmen.value = results[1].data

    // 再获取工单详情
    getOrder(_id).then(response => {
      // 保存原始数据
      const orderData = response.data

      // 映射技术员和业务员数据
      mapTechnicianAndSalesman(orderData)

      // 填充表单数据
      form.value = {
        ...form.value,
        ...orderData,
        // 确保使用正确的字段名
        technician: orderData.技术员,
        salesman: orderData.业务员
      }

      open.value = true
      title.value = "修改工单记录"
    })
  })
}

// 映射技术员和业务员数据
function mapTechnicianAndSalesman(orderData) {
  // 如果技术员数据是对象格式，提取ID或名称
  if (orderData.技术员 && typeof orderData.技术员 === 'object') {
    orderData.技术员 = orderData.技术员.id || orderData.技术员.name
  }

  // 如果业务员数据是对象格式，提取ID或名称
  if (orderData.业务员 && typeof orderData.业务员 === 'object') {
    orderData.业务员 = orderData.业务员.id || orderData.业务员.name
  }

  // 如果下拉框选项已加载，确保值与选项匹配
  if (technicians.value.length > 0) {
    // 查找匹配的技术员
    const matchedTechnician = technicians.value.find(
      t => t.id === orderData.技术员 || t.name === orderData.技术员
    )
    if (matchedTechnician) {
      orderData.技术员 = matchedTechnician.id || matchedTechnician.name
    }
  }

  // 同理处理业务员
  if (salesmen.value.length > 0) {
    const matchedSalesman = salesmen.value.find(
      s => s.id === orderData.业务员 || s.name === orderData.业务员
    )
    if (matchedSalesman) {
      orderData.业务员 = matchedSalesman.id || matchedSalesman.name
    }
  }
}

// 提交表单前验证所有字段
function submitForm() {
  proxy.$refs["orderRef"].validate(valid => {
    if (valid) {
      // 准备提交的数据，将驼峰命名法字段映射为后端需要的下划线命名法
      const submitData = {
        ...form.value,
        技术员: form.value.technician,
        业务员: form.value.salesman
      }

      if (form.value.id != null) {
        updateOrder(submitData).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addOrder(submitData).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    } else {
      console.log('表单验证失败')
      return false
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除工单记录编号为"' + _ids + '"的数据项？').then(function () {
    return delOrder(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('order/order/export', {
    ...queryParams.value
  }, `order_${new Date().getTime()}.xlsx`)
}



getList()
getTechniciansAndSalesmen()
</script>
