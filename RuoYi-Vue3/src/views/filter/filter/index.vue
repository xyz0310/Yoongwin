<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="电压" prop="电压">
        <el-input
          v-model="queryParams.电压"
          placeholder="请输入电压"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="相位" prop="相位">
        <el-input
          v-model="queryParams.相位"
          placeholder="请输入相位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电流" prop="电流">
        <el-input
          v-model="queryParams.电流"
          placeholder="请输入电流"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用场景" prop="应用场景">
        <el-input
          v-model="queryParams.应用场景"
          placeholder="请输入应用场景"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['filter:filter:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['filter:filter:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['filter:filter:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['filter:filter:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 为表格添加固定高度和横向滚动条 -->
    <div class="table-container" style="overflow-x: auto; max-width: 100%">
      <el-table v-loading="loading" :data="filterList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="产品系列" align="center" prop="产品系列" width="120" show-overflow-tooltip/>
        <el-table-column label="产品型号" align="center" prop="产品型号" width="120" show-overflow-tooltip/>
        <el-table-column label="应用场景" align="center" prop="应用场景" width="180" show-overflow-tooltip/>
        <el-table-column label="供货厂商" align="center" prop="供货厂商" width="150" show-overflow-tooltip/>
        <el-table-column label="电压" align="center" prop="电压" width="100" show-overflow-tooltip/>
        <el-table-column label="相位" align="center" prop="相位" width="100" show-overflow-tooltip/>
        <el-table-column label="电流" align="center" prop="电流" width="100" show-overflow-tooltip/>
        <el-table-column label="操作" align="center" width="160">
          <template #default="scope">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['filter:filter:edit']">修改</el-button>
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['filter:filter:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改滤波器对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="filterRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品系列" prop="产品系列">
          <el-input v-model="form.产品系列" placeholder="请输入产品系列" />
        </el-form-item>
        <el-form-item label="产品型号" prop="产品型号">
          <el-input v-model="form.产品型号" placeholder="请输入产品型号" />
        </el-form-item>
        <el-form-item label="应用场景" prop="应用场景">
          <el-input v-model="form.应用场景" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="供货厂商" prop="供货厂商">
          <el-input v-model="form.供货厂商" placeholder="请输入供货厂商" />
        </el-form-item>
        <el-form-item label="电压" prop="电压">
          <el-input v-model="form.电压" placeholder="请输入电压" />
        </el-form-item>
        <el-form-item label="相位" prop="相位">
          <el-input v-model="form.相位" placeholder="请输入相位" />
        </el-form-item>
        <el-form-item label="电流" prop="电流">
          <el-input v-model="form.电流" placeholder="请输入电流" />
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

<script setup name="Filter">
import { listFilter, getFilter, delFilter, addFilter, updateFilter } from "@/api/filter/filter"

const { proxy } = getCurrentInstance()

const filterList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 13,
    应用场景: null,
    电压: null,
    相位: null,
    电流: null
  },
  rules: {
    产品系列: [
      { required: true, message: "产品系列不能为空", trigger: "blur" }
    ],
    产品型号: [
      { required: true, message: "产品型号不能为空", trigger: "blur" }
    ],
    供货厂商: [
      { required: true, message: "供货厂商不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询滤波器列表 */
function getList() {
  loading.value = true
  listFilter(queryParams.value).then(response => {
    filterList.value = response.rows
    total.value = response.total
    loading.value = false
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
    产品系列: null,
    产品型号: null,
    应用场景: null,
    供货厂商: null,
    电压: null,
    相位: null,
    电流: null
  }
  proxy.resetForm("filterRef")
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
  title.value = "添加滤波器"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getFilter(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改滤波器"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["filterRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateFilter(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addFilter(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除滤波器编号为"' + _ids + '"的数据项？').then(function() {
    return delFilter(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('filter/filter/export', {
    ...queryParams.value
  }, `filter_${new Date().getTime()}.xlsx`)
}

getList()
</script>    