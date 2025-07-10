<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="天线种类" prop="天线种类">
        <el-select v-model="queryParams.天线种类" clearable placeholder="请选择天线种类">
          <el-option v-for="type in antennaTypes.filter(item => item)" :key="type" :label="type" :value="type">
          </el-option>
        </el-select>
      </el-form-item>
      <!--        <el-form-item label="室内外" prop="室内外">
        <el-select v-model="queryParams.室内外" placeholder="请选择室内外">
          <el-option
            v-for="type in indoorOutdoorTypes.filter(item => item)"
            :key="type"
            :label="type"
            :value="type">
          </el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item label="全定向" prop="全定向">
        <el-select v-model="queryParams.全定向"  clearable placeholder="请选择全定向">
          <el-option v-for="type in omniDirectionalTypes.filter(item => item)" :key="type" :label="type" :value="type">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="极化方式" prop="极化方式">
        <el-select v-model="queryParams.极化方式" clearable placeholder="请选择极化方式" filterable allow-create default-first-option>
          <el-option v-for="type in polarizationTypes.filter(item => item)" :key="type" :label="type" :value="type">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="频率范围" prop="频率范围">
        <el-input v-model="queryParams.频率范围" placeholder="请输入频率范围" clearable @keyup.enter="handleQuery" >
          <template #suffix>MHz</template>
        </el-input>
      </el-form-item>
      <el-form-item label="驻波比" prop="驻波比">
        <el-input v-model="queryParams.驻波比" placeholder="请输入驻波比最大值" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="增益" prop="增益">
        <el-input v-model="queryParams.增益" placeholder="请输入增益" clearable @keyup.enter="handleQuery">
          <template #suffix>dBi</template>
        </el-input>
      </el-form-item>
      <el-form-item label="功率" prop="功率">
        <el-input v-model="queryParams.功率" placeholder="请输入功率" clearable @keyup.enter="handleQuery">
          <template #suffix>W</template>
        </el-input>
      </el-form-item>
      <el-form-item label="阻抗" prop="阻抗">
        <el-input v-model="queryParams.阻抗" placeholder="请输入阻抗" clearable @keyup.enter="handleQuery">
          <template #suffix>Ω</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['antenna:antenna:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['antenna:antenna:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['antenna:antenna:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['antenna:antenna:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="antennaList" @selection-change="handleSelectionChange"
      style="width: 100%; overflow-x: auto;">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="天线种类" align="center" prop="天线种类" width="120" show-overflow-tooltip />
      <el-table-column label="产品型号" align="center" prop="产品型号" width="200" show-overflow-tooltip />
      <el-table-column label="应用场景" align="center" prop="应用场景" width="200" show-overflow-tooltip />
      <el-table-column label="供货厂商" align="center" prop="供货厂商" width="120" show-overflow-tooltip />
      <!--       <el-table-column label="室内外" align="center" prop="室内外" width="80" show-overflow-tooltip/> -->
      <el-table-column label="全定向" align="center" prop="全定向" width="100" show-overflow-tooltip />
      <el-table-column label="频率范围" align="center" prop="频率范围" width="140" show-overflow-tooltip />
      <el-table-column label="驻波比" align="center" prop="驻波比" width="140" show-overflow-tooltip />
      <el-table-column label="增益" align="center" prop="增益" width="140" show-overflow-tooltip />
      <el-table-column label="极化方式" align="center" prop="极化方式" width="100" show-overflow-tooltip />
      <el-table-column label="功率" align="center" prop="功率" width="100" show-overflow-tooltip />
      <el-table-column label="阻抗" align="center" prop="阻抗" width="100" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['antenna:antenna:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['antenna:antenna:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改无线天线对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="antennaRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="天线种类" prop="天线种类">
          <el-select v-model="form.天线种类" placeholder="请选择天线种类" filterable allow-create default-first-option>
            <el-option v-for="type in antennaTypes.filter(item => item)" :key="type" :label="type" :value="type">
            </el-option>
          </el-select>
        </el-form-item>
        <!--       <el-form-item label="室内外" prop="室内外">
          <el-select v-model="form.室内外" placeholder="请选择室内外">
            <el-option
              v-for="type in indoorOutdoorTypes"
              :key="type"
              :label="type"
              :value="type">
            </el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="全定向" prop="全定向">
          <el-select v-model="form.全定向" placeholder="请选择全定向">
            <el-option v-for="type in omniDirectionalTypes.filter(item => item)" :key="type" :label="type"
              :value="type">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="极化方式" prop="极化方式">
          <el-select v-model="form.极化方式" placeholder="请选择极化方式" filterable allow-create default-first-option>
            <el-option v-for="type in polarizationTypes.filter(item => item)" :key="type" :label="type" :value="type">
            </el-option>
          </el-select>
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
        <el-form-item label="频率范围" prop="频率范围">
          <el-input v-model="form.频率范围" placeholder="请输入频率范围">
            <template #suffix>MHz</template>
          </el-input>
        </el-form-item>
        <el-form-item label="驻波比" prop="驻波比">
          <el-input v-model="form.驻波比" placeholder="请输入驻波比" />

        </el-form-item>
        <el-form-item label="增益" prop="增益">
          <el-input v-model="form.增益" placeholder="请输入增益">
            <template #suffix>dBi</template>
          </el-input>
        </el-form-item>
        <el-form-item label="功率" prop="功率">
          <el-input v-model="form.功率" placeholder="请输入功率">
            <template #suffix>W</template>
          </el-input>
        </el-form-item>
        <el-form-item label="阻抗" prop="阻抗">
          <el-input v-model="form.阻抗" placeholder="请输入阻抗">
            <template #suffix>Ω</template>
          </el-input>
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

<script setup name="Antenna">
import { listAntenna, getAntenna, delAntenna, addAntenna, updateAntenna, getAllAntennaTypes, getAllIndoorOutdoorTypes, getAllOmniDirectionalTypes, getAllPolarizationTypes } from "@/api/antenna/antenna"
import { ref, reactive, toRefs, getCurrentInstance } from 'vue'

const { proxy } = getCurrentInstance()

const antennaList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const antennaTypes = ref([]) // 新增：存储天线种类列表
const indoorOutdoorTypes = ref([]) // 新增：存储室内外类型列表
const omniDirectionalTypes = ref([]) // 新增：存储全定向类型列表
const polarizationTypes = ref([]) // 新增：存储极化方式类型列表



const data = reactive({
  form: {
    频率范围: '',
    增益: '',
    功率: '',
    阻抗: ''
  },
  queryParams: {
    pageNum: 1,
    pageSize: 13,
    天线种类: null,
    应用场景: null,
    室内外: null,
    全定向: null,
    频率范围: null,
    驻波比: null,
    增益: null,
    极化方式: null,
    功率: null,
    阻抗: null
  },
  rules: {
    天线种类: [
      { required: true, message: "天线种类不能为空", trigger: "change" }
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

/** 查询无线天线列表 */
function getList() {
  loading.value = true
  listAntenna(queryParams.value).then(response => {
    antennaList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 获取所有天线种类
async function getAntennaTypes() {
  const response = await getAllAntennaTypes()
  antennaTypes.value = response.data
}

// 获取所有室内外类型
async function getIndoorOutdoorTypes() {
  const response = await getAllIndoorOutdoorTypes()
  indoorOutdoorTypes.value = response.data
}

// 获取所有全定向类型
async function getOmniDirectionalTypes() {
  const response = await getAllOmniDirectionalTypes()
  omniDirectionalTypes.value = response.data
}

// 获取所有极化方式类型
async function getPolarizationTypes() {
  const response = await getAllPolarizationTypes()
  polarizationTypes.value = response.data
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
    天线种类: null,
    产品型号: null,
    应用场景: null,
    供货厂商: null,
    室内外: null,
    全定向: null,
    频率范围: null,
    驻波比: null,
    增益: null,
    极化方式: null,
    功率: null,
    阻抗: null
  }
  proxy.resetForm("antennaRef")
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
  title.value = "添加无线天线"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getAntenna(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改无线天线"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["antennaRef"].validate(valid => {
    if (valid) {
      // 检查并添加频率范围后缀
      if (form.value.频率范围 && !form.value.频率范围.endsWith('MHz')) {
        form.value.频率范围 = `${form.value.频率范围}MHz`;
      }
      // 检查并添加增益后缀
      if (form.value.增益 && !form.value.增益.endsWith('dBi')) {
        form.value.增益 = `${form.value.增益}dBi`;
      }
      // 检查并添加功率后缀
      if (form.value.功率 && !form.value.功率.endsWith('W')) {
        form.value.功率 = `${form.value.功率}W`;
      }
      // 检查并添加阻抗后缀
      if (form.value.阻抗 && !form.value.阻抗.endsWith('Ω')) {
        form.value.阻抗 = `${form.value.阻抗}Ω`;
      }
      if (form.value.id != null) {
        updateAntenna(form.value).then(response => {
          if (response.code === 200) {
            proxy.$modal.msgSuccess("修改成功")
            open.value = false
            getList()
          } else {
            proxy.$modal.msgError("修改失败：" + response.msg)
          }
        }).catch(error => {
          proxy.$modal.msgError("修改请求出错：" + error.message)
        })
      } else {
        addAntenna(form.value).then(response => {
          if (response.code === 200) {
            proxy.$modal.msgSuccess("新增成功")
            open.value = false
            getList()
          } else {
            proxy.$modal.msgError("新增失败：" + response.msg)
          }
        }).catch(error => {
          proxy.$modal.msgError("新增请求出错：" + error.message)
        })
      }
    } else {
      proxy.$modal.msgError("表单验证失败，请检查输入内容")
    }
  })
}


/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除无线天线编号为"' + _ids + '"的数据项？').then(function () {
    return delAntenna(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('antenna/antenna/export', {
    ...queryParams.value
  }, `antenna_${new Date().getTime()}.xlsx`)
}

getList()
getAntennaTypes() // 调用获取天线种类的方法
getIndoorOutdoorTypes() // 调用获取室内外类型的方法
getOmniDirectionalTypes() // 调用获取全定向类型的方法
getPolarizationTypes() // 调用获取极化方式类型的方法
</script>
