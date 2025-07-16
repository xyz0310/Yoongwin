<template>

  <div class="app-container">
<!--     <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="申请人" prop="applicant">
        <el-input
          v-model="queryParams.applicant"
          placeholder="请输入申请人"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请假状态" clearable style="width: 200px">
          <el-option
            v-for="dict in leave_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-tabs v-model="queryParams.status" @tab-click="handleQuery" class="mb8">
      <el-tab-pane label="全部" name="" />
      <el-tab-pane label="待审批" name="0" />
      <el-tab-pane label="已同意" name="1" />
      <el-tab-pane label="已拒绝" name="2" />
    </el-tabs>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Check"
          @click="handleApproveAll"
          v-hasPermi="['leave:leave:approve']"
        >批量同意</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Close"
          @click="handleRejectAll"
          v-hasPermi="['leave:leave:reject']"
        >批量拒绝</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="leaveApplicationList"
      row-key="leaveId"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" :selectable="isRowSelectable" ></el-table-column>
      <el-table-column prop="applicant" label="申请人" width="200"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="200">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="200">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="reason" label="请假原因" width="300"></el-table-column>
      <el-table-column prop="status" label="状态" width="100">
        <template #default="scope">
          <dict-tag :options="leave_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column prop="approvalOpinion" label="审批意见" width="200"></el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button v-if="scope.row.status === '0'" link type="primary" icon="Check" @click="handleApprove(scope.row)" v-hasPermi="['leave:leave:approve']">同意</el-button>
          <el-button v-if="scope.row.status === '0'" link type="primary" icon="Close" @click="handleReject(scope.row)" v-hasPermi="['leave:leave:reject']">拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 审批意见对话框 -->
    <el-dialog :title="approvalTitle" v-model="approvalOpen" width="400px" append-to-body>
      <el-form ref="approvalRef" :model="approvalForm" :rules="approvalRules" label-width="80px">
        <el-form-item label="审批意见" prop="approvalOpinion">
          <el-input v-model="approvalForm.approvalOpinion" placeholder="请输入审批意见" type="textarea" rows="4" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitApproval">确 定</el-button>
          <el-button @click="cancelApproval">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="LeaveLeader">
import { listLeaveApplication, updateLeaveApplication } from "@/api/leave/leave";
import { getInfo } from "@/api/login"; // 引入获取用户信息的 API
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';

const { proxy } = getCurrentInstance();
const { leave_status } = proxy.useDict("leave_status");
const total = ref(0);

const nickName = ref("");
const leaveApplicationList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const refreshTable = ref(true);
const approvalOpen = ref(false);
const approvalTitle = ref("");
const approvalForm = ref({});
const selectedRows = ref([]);

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 13,
    applicant: undefined,
    status: '0',
    nickName: undefined 
  },
  rules: {},
  approvalRules: {
    approvalOpinion: []
  },
});

const { queryParams, rules, approvalRules } = toRefs(data);

/** 查询请假申请列表 */
async function getList() {
  loading.value = true;
  try {
    const userInfo = await getInfo();
    nickName.value = userInfo.user.nickName;
    queryParams.value.nickName = nickName.value;

    // ✅ 正确传参（queryParams 是 ref，要取 value）
    const response = await listLeaveApplication(queryParams.value);

    // ✅ 设置表格数据
    leaveApplicationList.value = response.rows || response.data;

    // ✅ 设置总条数（决定分页是否显示）
    total.value = response.total || response.data?.length || 0;

  } catch (error) {
    console.error('获取请假列表失败:', error);
  } finally {
    loading.value = false;
  }
}


/** 搜索按钮操作 */
function handleQuery() {
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  selectedRows.value = selection;
}

/** 单个同意操作 */
function handleApprove(row) {
  approvalTitle.value = "同意审批";
  approvalForm.value = { ...row, status: '1' };
  approvalOpen.value = true;
}

/** 单个拒绝操作 */
function handleReject(row) {
  approvalTitle.value = "拒绝审批";
  approvalForm.value = { ...row, status: '2' };
  approvalOpen.value = true;
}

/** 批量同意操作 */
function handleApproveAll() {
  if (selectedRows.value.length === 0) {
    proxy.$modal.msgWarning("请选择要审批的请假申请");
    return;
  }
  approvalTitle.value = "批量同意审批";
  approvalForm.value = { status: '1' };
  approvalOpen.value = true;
}

/** 批量拒绝操作 */
function handleRejectAll() {
  if (selectedRows.value.length === 0) {
    proxy.$modal.msgWarning("请选择要审批的请假申请");
    return;
  }
  approvalTitle.value = "批量拒绝审批";
  approvalForm.value = { status: '2' };
  approvalOpen.value = true;
}

/** 提交审批意见 */
function submitApproval() {
  proxy.$refs["approvalRef"].validate(async (valid) => {
    if (valid) {
      try {
        const userInfo = await getInfo();
        const user = userInfo.user;

        if (selectedRows.value.length > 0) {
          const promises = selectedRows.value.map(row => {
            const data = {
              ...row,
              ...approvalForm.value,
              applicant: row.applicant || user.nickName,
              nickName: user.nickName,
              userId: user.userId,
              deptId: user.deptId
            };
            console.log("批量审批提交数据:", data);
            return updateLeaveApplication(data);
          });
          await Promise.all(promises);
        } else {
          const data = {
            ...approvalForm.value,
            applicant: approvalForm.value.applicant || user.nickName,
            nickName: user.nickName,
            userId: user.userId,
            deptId: user.deptId
          };
          console.log("单个审批提交数据:", data);
          await updateLeaveApplication(data);
        }

        proxy.$modal.msgSuccess("审批成功");
        approvalOpen.value = false;
        queryParams.value.status = '';
        getList();

      } catch (err) {
        console.error("审批失败", err);
      }
    }
  });
}


/** 仅允许选择待审批（status === '0'）的申请 */
function isRowSelectable(row) {
  return row.status === '0';
}


/** 取消审批对话框 */
function cancelApproval() {
  approvalOpen.value = false;
}

getList();
</script>