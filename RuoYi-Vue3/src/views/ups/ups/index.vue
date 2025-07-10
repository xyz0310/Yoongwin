<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="容量" prop="容量">
        <el-input v-model="queryParams.容量" placeholder="请输入容量" clearable @keyup.enter="handleQuery" >
          <template #suffix>KVA</template>
        </el-input>
      </el-form-item>
      <el-form-item label="功率" prop="功率">
        <el-input v-model="queryParams.功率" placeholder="请输入功率" clearable @keyup.enter="handleQuery" >
          <template #suffix>KW</template>
        </el-input>
      </el-form-item>
      <el-form-item label="输入电压" prop="输入电压">
        <el-input v-model="queryParams.输入电压大小" placeholder="请输入输入电压大小" clearable @keyup.enter="handleQuery" >
          <template #suffix>VAC</template>
        </el-input>
      </el-form-item>
      <el-form-item label="输出电压" prop="输出电压">
        <el-input v-model="queryParams.输出电压大小" placeholder="请输入输出电压大小" clearable @keyup.enter="handleQuery" >
          <template #suffix>VAC</template>
        </el-input>
      </el-form-item>
      <el-form-item label="输入频率" prop="输入频率">
        <el-input v-model="queryParams.输入频率" placeholder="请输入输入频率" clearable @keyup.enter="handleQuery" >
          <template #suffix>Hz</template>
        </el-input>
      </el-form-item>
      <el-form-item label="输出频率" prop="输出频率">
        <el-input v-model="queryParams.输出频率" placeholder="请输入输出频率" clearable @keyup.enter="handleQuery" >
          <template #suffix>Hz</template>
        </el-input>
      </el-form-item>
      <el-form-item label="应用场景" prop="应用场景">
        <el-input v-model="queryParams.应用场景" placeholder="请输入应用场景" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="输入相位" prop="输入相位">
        <el-select v-model="queryParams.输入电压相位" clearable placeholder="请选择输入电压相位">
          <el-option v-for="item in inputVoltagePhaseList.filter(item => item)" :key="item" :label="item"
            :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="输出相位" prop="输出相位">
        <el-select v-model="queryParams.输出电压相位" clearable placeholder="请选择输出电压相位">
          <el-option v-for="item in outputVoltagePhaseList.filter(item => item)" :key="item" :label="item"
            :value="item"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['ups:ups:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['ups:ups:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ups:ups:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['ups:ups:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="upsList" @selection-change="handleSelectionChange"
      style="width: 100%; overflow-x: auto;">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="产品系列" align="center" prop="产品系列" width="150" show-overflow-tooltip/>
      <el-table-column label="产品型号" align="center" prop="产品型号" width="150" show-overflow-tooltip />
      <el-table-column label="应用场景" align="center" prop="应用场景" width="200" show-overflow-tooltip>
        <template #default="scope">
          <span class="ellipsis">{{ scope.row.应用场景 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="供货厂商" align="center" prop="供货厂商" width="80" show-overflow-tooltip />
      <el-table-column label="容量" align="center" prop="容量" width="85" show-overflow-tooltip />
      <el-table-column label="功率" align="center" prop="功率" width="85" show-overflow-tooltip />
      <el-table-column label="输入电压大小" align="center" prop="输入电压大小" width="120" show-overflow-tooltip />
      <el-table-column label="输入电压相位" align="center" prop="输入电压相位" width="65" show-overflow-tooltip />
      <el-table-column label="输入电压分类" align="center" prop="输入电压分类" width="65" show-overflow-tooltip />
      <el-table-column label="输出电压大小" align="center" prop="输出电压大小" width="120" show-overflow-tooltip />
      <el-table-column label="输出电压相位" align="center" prop="输出电压相位" width="65" show-overflow-tooltip />
      <el-table-column label="输出电压分类" align="center" prop="输出电压分类" width="65" show-overflow-tooltip />
      <el-table-column label="输入频率" align="center" prop="输入频率" width="105" show-overflow-tooltip />
      <el-table-column label="输出频率" align="center" prop="输出频率" width="105" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['ups:ups:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['ups:ups:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />


    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form ref="upsRef" :model="form" :rules="rules" label-width="120px">
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
            <el-form-item label="容量" prop="容量">
              <el-input v-model="form.容量" placeholder="请输入容量" >
                <template #suffix>KVA</template>
              </el-input>
            </el-form-item>
            <el-form-item label="功率" prop="功率">
              <el-input v-model="form.功率" placeholder="请输入功率" >
                <template #suffix>KW</template>
              </el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12">
          <el-form ref="upsRef" :model="form" :rules="rules" label-width="120px">
            <el-form-item label="输入电压大小" prop="输入电压大小">
              <el-input v-model="form.输入电压大小" placeholder="请输入输入电压大小" >
                <template #suffix>V</template>
              </el-input>
            </el-form-item>
            <el-form-item label="输入电压相位" prop="输入电压相位">
              <el-input v-model="form.输入电压相位" placeholder="请输入输入电压相位" />
            </el-form-item>
            <el-form-item label="输入电压分类" prop="输入电压分类">
              <el-input v-model="form.输入电压分类" placeholder="请输入输入电压分类" />
            </el-form-item>
            <el-form-item label="输出电压大小" prop="输出电压大小">
              <el-input v-model="form.输出电压大小" placeholder="请输入输出电压大小" >
                <template #suffix>V</template>
              </el-input>
            </el-form-item>
            <el-form-item label="输出电压相位" prop="输出电压相位">
              <el-input v-model="form.输出电压相位" placeholder="请输入输出电压相位" />
            </el-form-item>
            <el-form-item label="输出电压分类" prop="输出电压分类">
              <el-input v-model="form.输出电压分类" placeholder="请输入输出电压分类" />
            </el-form-item>
            <el-form-item label="输入频率" prop="输入频率">
              <el-input v-model="form.输入频率" placeholder="请输入输入频率" >
                <template #suffix>Hz</template>
              </el-input>
            </el-form-item>
            <el-form-item label="输出频率" prop="输出频率">
              <el-input v-model="form.输出频率" placeholder="请输入输出频率" >
                <template #suffix>Hz</template>
              </el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, getCurrentInstance } from 'vue';
import { listUps, getUps, delUps, addUps, updateUps, getInputVoltagePhaseList, getOutputVoltagePhaseList } from "@/api/ups/ups";

const { proxy } = getCurrentInstance();

const upsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const inputVoltagePhaseList = ref([]);
const outputVoltagePhaseList = ref([]);



const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 13,
    产品系列: null,
    产品型号: null,
    供货厂商: null,
    容量: null,
    输入电压大小: null,
    输出电压大小: null,
    输入频率: null,
    输出频率: null,
    输入电压相位: null,
    输出电压相位: null
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
    容量: [
      { required: true, message: "容量不能为空", trigger: "blur" }
    ],
    输入电压大小: [
      { required: true, message: "输入电压大小不能为空", trigger: "blur" }
    ],
    输出电压大小: [
      { required: true, message: "输出电压大小不能为空", trigger: "blur" }
    ],
    输入频率: [
      { required: true, message: "输入频率不能为空", trigger: "blur" }
    ],
    输出频率: [
      { required: true, message: "输出频率不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

function getDropdownData() {
  // 获取输入电压相位列表
  getInputVoltagePhaseList().then(response => {
    inputVoltagePhaseList.value = response.data;
  });

  // 获取输出电压相位列表
  getOutputVoltagePhaseList().then(response => {
    outputVoltagePhaseList.value = response.data;
  });
}


/** 查询ups电源列表 */
function getList() {
  loading.value = true
  listUps(queryParams.value).then(response => {
    upsList.value = response.rows
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
    容量: null,
    功率: null,
    输入电压大小: null,
    输入电压相位: null,
    输入电压分类: null,
    输出电压大小: null,
    输出电压相位: null,
    输出电压分类: null,
    输入频率: null,
    输出频率: null
  }
  proxy.resetForm("upsRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  /* proxy.resetForm("queryRef") */
  data.queryParams = {
    pageNum: 1,
    pageSize: 13,
    产品系列: null,
    产品型号: null,
    供货厂商: null,
    容量: null,
    输入电压大小: null,
    输出电压大小: null,
    输入频率: null,
    输出频率: null,
    输入电压相位: null,
    输出电压相位: null,
  };
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
  title.value = "添加ups电源"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getUps(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改ups电源"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["upsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateUps(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addUps(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除ups电源编号为"' + _ids + '"的数据项？').then(function () {
    return delUps(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => { })
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ups/ups/export', {
    ...queryParams.value
  }, `ups_${new Date().getTime()}.xlsx`)
}



getList();
getDropdownData();
</script>

<style scoped>
.ellipsis {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}


/* 新增：图片容器样式 */
.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
  background-color: #f5f7fa;
}

/* 新增：产品图片样式 */
.product-image {
  max-width: 100%;
  max-height: 500px;
  object-fit: contain;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>