<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="光纤芯数" prop="光纤芯数">
        <el-input
          v-model="queryParams.光纤芯数"
          placeholder="请输入光纤芯数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排列方式" prop="排列方式">
        <el-input
          v-model="queryParams.排列方式"
          placeholder="请输入排列方式"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="传输波长" prop="传输波长">
        <el-input
          v-model="queryParams.传输波长"
          placeholder="请输入传输波长"
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
          v-hasPermi="['opticalFiber:opticalFiber:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['opticalFiber:opticalFiber:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['opticalFiber:opticalFiber:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['opticalFiber:opticalFiber:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<el-table v-loading="loading" :data="opticalFiberList" @selection-change="handleSelectionChange" style="width: 100%;">
      <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="产品类型" align="center" prop="产品类型" width="150" show-overflow-tooltip/>
        <el-table-column label="产品型号" align="center" prop="产品型号" width="150" show-overflow-tooltip/>
        <el-table-column label="应用场景" align="center" prop="应用场景" width="180" show-overflow-tooltip />
        <el-table-column label="供货厂商" align="center" prop="供货厂商" width="150" show-overflow-tooltip/>
        <el-table-column label="光纤芯数" align="center" prop="光纤芯数" width="100" show-overflow-tooltip/>
        <el-table-column label="排列方式" align="center" prop="排列方式" width="100" show-overflow-tooltip/>
        <el-table-column label="光纤类型" align="center" prop="光纤类型" width="100" show-overflow-tooltip/>
        <el-table-column label="传输波长" align="center" prop="传输波长" width="100" show-overflow-tooltip/>
        <el-table-column label="长度" align="center" prop="长度" width="80" />
        <el-table-column label="接头类型" align="center" prop="接头类型" width="100" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['antenna:antenna:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['antenna:antenna:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改光纤光缆对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="opticalFiberRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品类型" prop="产品类型">
          <el-input v-model="form.产品类型" placeholder="请输入产品类型" />
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
        <el-form-item label="光纤芯数" prop="光纤芯数">
          <el-input v-model="form.光纤芯数" placeholder="请输入光纤芯数" />
        </el-form-item>
        <el-form-item label="排列方式" prop="排列方式">
          <el-input v-model="form.排列方式" placeholder="请输入排列方式" />
        </el-form-item>
        <el-form-item label="传输波长" prop="传输波长">
          <el-input v-model="form.传输波长" placeholder="请输入传输波长" />
        </el-form-item>
        <el-form-item label="长度" prop="长度">
          <el-input v-model="form.长度" placeholder="请输入长度" />
        </el-form-item>
        <el-form-item label="接头类型" prop="接头类型">
          <el-input v-model="form.接头类型" placeholder="请输入接头类型" />
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

<script setup name="OpticalFiber">
import { listOpticalFiber, getOpticalFiber, delOpticalFiber, addOpticalFiber, updateOpticalFiber } from "@/api/opticalFiber/opticalFiber"

const { proxy } = getCurrentInstance()

const opticalFiberList = ref([])
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
    光纤芯数: null,
    排列方式: null,
    光纤类型: null,
    传输波长: null,
  },
  rules: {
    产品类型: [
      { required: true, message: "产品类型不能为空", trigger: "blur" }
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

/** 查询光纤光缆列表 */
function getList() {
  loading.value = true
  listOpticalFiber(queryParams.value).then(response => {
    opticalFiberList.value = response.rows
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
    产品类型: null,
    产品型号: null,
    应用场景: null,
    供货厂商: null,
    光纤芯数: null,
    排列方式: null,
    光纤类型: null,
    传输波长: null,
    长度: null,
    接头类型: null
  }
  proxy.resetForm("opticalFiberRef")
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
  title.value = "添加光纤光缆"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getOpticalFiber(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改光纤光缆"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["opticalFiberRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOpticalFiber(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addOpticalFiber(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除光纤光缆编号为"' + _ids + '"的数据项？').then(function() {
    return delOpticalFiber(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('opticalFiber/opticalFiber/export', {
    ...queryParams.value
  }, `opticalFiber_${new Date().getTime()}.xlsx`)
}

getList()
</script>    