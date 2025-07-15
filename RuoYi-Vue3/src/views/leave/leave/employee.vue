<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['leave:leave:add']"
        >新增</el-button>
      </el-col>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="leaveApplicationList"
      row-key="leaveId"
    >
      <el-table-column prop="startTime" label="开始时间" width="200">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="200">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
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
          <el-button v-if="scope.row.status === '0'" link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['leave:leave:edit']">修改</el-button>
          <el-button v-if="scope.row.status === '0'" link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['leave:leave:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改请假申请对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="leaveRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker
                v-model="form.startTime"
                type="date"
                placeholder="选择开始时间"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker
                v-model="form.endTime"
                type="date"
                placeholder="选择结束时间"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="请假原因" prop="reason">
              <el-input v-model="form.reason" placeholder="请输入请假原因" type="textarea" rows="4" />
            </el-form-item>
          </el-col>
        </el-row>
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

<script setup name="LeaveEmployee">
import { listLeaveApplication, getLeaveApplication, addLeaveApplication, updateLeaveApplication, deleteLeaveApplication } from "@/api/leave/leave";
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';
import { getInfo } from "@/api/login"; // 引入获取用户信息的 API

const { proxy } = getCurrentInstance();
const { leave_status } = proxy.useDict("leave_status");

const nickName = ref("");
const leaveApplicationList = ref([]);
const open = ref(false);
const loading = ref(true);
const title = ref("");
const refreshTable = ref(true);

const data = reactive({
  form: {},
  rules: {
    startTime: [{ required: true, message: "开始时间不能为空", trigger: "change" }],
    endTime: [{ required: true, message: "结束时间不能为空", trigger: "change" }],
    reason: [{ required: true, message: "请假原因不能为空", trigger: "blur" }]
  },
});

const { form, rules } = toRefs(data);

/** 查询请假申请列表 */
async function getList() {
  loading.value = true;
  try {
    const userInfo = await getInfo();
    nickName.value = userInfo.user.nickName;
    listLeaveApplication({ nickName: nickName.value }).then(response => {
      leaveApplicationList.value = response.data;
      loading.value = false;
    });
  } catch (error) {
    console.error('Failed to get user info:', error);
    loading.value = false;
  }
}

/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}

/** 表单重置 */
function reset() {
  form.value = {
    leaveId: undefined,
    startTime: undefined,
    endTime: undefined,
    reason: undefined,
    status: '0',
    approvalOpinion: undefined
  };
  proxy.resetForm("leaveRef");
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加请假申请";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  getLeaveApplication(row.leaveId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改请假申请";
  });
}

/** 提交按钮 */
async function submitForm() {
  proxy.$refs["leaveRef"].validate(valid => {
    if (valid) {
      if (form.value.leaveId != undefined) {
        updateLeaveApplication(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        getInfo().then(userInfo => {
          const user = userInfo.user;
          nickName.value = user.nickName;
          form.value.nickName = nickName.value;
          form.value.applicant = nickName.value;
          form.value.nickName = user.nickName;
          form.value.userId = user.userId;
          form.value.deptId = user.deptId;
          form.value.applicant = user.nickName;
          addLeaveApplication(form.value).then(response => {
            proxy.$modal.msgSuccess("新增成功");
            open.value = false;
            getList();
          });
        }).catch(error => {
          console.error('Failed to get user info:', error);
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  proxy.$modal.confirm('是否确认删除该请假申请?').then(function() {
    return deleteLeaveApplication(row.leaveId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

getList();
</script>