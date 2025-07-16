<template>
  <div class="app-container">
    <!-- 查询区域 -->
    <el-card shadow="never" class="filter-card">
      <el-form
        :model="queryParams"
        ref="queryRef"
        :inline="true"
        label-width="80px"
        class="filter-form"
      >
        <el-form-item label="部门" prop="deptId">
          <el-select
            v-model="queryParams.deptId"
            placeholder="请选择部门"
            clearable
            class="form-item-width"
          >
            <el-option
              v-for="dept in deptOptions"
              :key="dept.deptId"
              :label="dept.deptName"
              :value="dept.deptId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="开始时间">
          <el-date-picker
            v-model="queryParams.startTime"
            type="date"
            placeholder="开始时间"
            class="form-item-width"
          />
        </el-form-item>

        <el-form-item label="结束时间">
          <el-date-picker
            v-model="queryParams.endTime"
            type="date"
            placeholder="结束时间"
            class="form-item-width"
          />
        </el-form-item>

        <el-form-item label="状态">
          <el-select
            v-model="queryParams.status"
            placeholder="请选择状态"
            clearable
            class="form-item-width"
          >
            <el-option
              v-for="dict in leave_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">查询</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
                <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['order:order:export']">导出</el-button>
        </el-col>
      </el-form>
    </el-card>

    <!-- 表格区域 -->
    <el-card shadow="never" class="table-card">
      <el-table
        :data="recordList"
        v-loading="loading"
        border
        stripe
        highlight-current-row
        class="leave-record-table"
      >
        <el-table-column prop="applicant" label="申请人" width="120" />
        <el-table-column prop="deptName" label="部门" width="150" />
        <el-table-column prop="startTime" label="开始时间" width="180">
          <template #default="scope">{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</template>
        </el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="180">
          <template #default="scope">{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</template>
        </el-table-column>
        <el-table-column prop="leaveDays" label="请假天数" width="100" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <dict-tag :options="leave_status" :value="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column prop="approvalOpinion" label="审批意见" />
      </el-table>

      <!-- 分页 -->
      <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>
  </div>
</template>




<script setup name="LeaveRecord">
import { ref, reactive, toRefs, getCurrentInstance } from 'vue';
import { listLeaveApplication, exportLeaveApplication } from '@/api/leave/leave';
import { listDept } from "@/api/system/dept";

const { proxy } = getCurrentInstance();
const { leave_status } = proxy.useDict("leave_status");
const total = ref(0); // 总条数

const queryParams = reactive({
  pageNum: 1,
  pageSize: 13,
  status: '',
  startTime: '',
  endTime: '',
  deptName: '',
  deptId: undefined,
});
const recordList = ref([]);
const loading = ref(false);
const deptOptions = ref([]);


/** 查询数据 */
function getList() {
  loading.value = true;
  listLeaveApplication(queryParams)
    .then(res => {
      if (res.code === 200) {
        recordList.value = Array.isArray(res.data) ? res.data : [];
        total.value = recordList.value.length; // 如果后端没传总数只能用长度
      } else {
        recordList.value = [];
        total.value = 0;
        proxy.$message.error(res.msg || '查询失败');
      }
    })
    .catch(err => {
      console.error('查询请假记录接口异常:', err);
      proxy.$message.error('查询异常，请稍后重试');
    })
    .finally(() => {
      loading.value = false;
    });
}


function filterDeptTree(data) {
  return data
    .filter(dept => dept.deptName !== '永为科技' && dept.deptName !== '南京公司')
    .map(dept => ({
      ...dept,
      children: dept.children ? filterDeptTree(dept.children) : [],
    }));
}

function loadDeptOptions() {
  listDept()
    .then(response => {
      if (response && response.data) {
        deptOptions.value = filterDeptTree(response.data);
      } else {
        deptOptions.value = [];
        console.warn('部门数据格式异常:', response);
      }
    })
    .catch(error => {
      console.error('加载部门列表失败:', error);
      deptOptions.value = [];
    });
}

/** 查询按钮 */
function handleQuery() {
    getList();
}

/** 重置按钮 */
function resetQuery() {
    proxy.resetForm("queryRef");
    handleQuery();
}

/** 导出按钮 */
function handleExport() {
    exportLeaveApplication(queryParams);
}

onMounted(() => {
  getList();          // 加载请假记录
  loadDeptOptions();  // 加载部门下拉框
});
</script>

<style scoped>
.query-form {
  margin-bottom: 20px;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.input-width {
  width: 200px;
}

.leave-record-table .el-table__cell {
  padding: 10px 8px;
  font-size: 14px;
}

.leave-record-table .el-table__header-wrapper th {
  background-color: #f5f7fa;
  font-weight: bold;
  color: #303133;
}

.leave-record-table .el-table__row:hover {
  background-color: #f0f9ff;
}
</style>
