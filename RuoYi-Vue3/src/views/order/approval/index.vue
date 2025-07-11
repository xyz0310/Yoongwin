<template>
    <div>
        <el-table :data="applyList" stripe>
            <el-table-column prop="workOrderId" label="工单ID"></el-table-column>
            <el-table-column prop="applicantId" label="申请人ID"></el-table-column>
            <el-table-column prop="applyTime" label="申请时间"></el-table-column>
            <el-table-column prop="approvalStatus" label="审批状态"></el-table-column>
            <el-table-column label="操作">
                <template #scope="scope">
                    <el-button type="success" @click="approveApply(scope.row.id, 'approved')">通过</el-button>
                    <el-button type="danger" @click="approveApply(scope.row.id, 'rejected')">拒绝</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { getWorkOrderDeleteApplyList, approveWorkOrderDeleteApply } from '@/api/order/approval';

export default {
    data() {
        return {
            applyList: []
        };
    },
    created() {
        this.getApplyList();
    },
    methods: {
        async getApplyList() {
            try {
                const response = await getWorkOrderDeleteApplyList();
                this.applyList = response.data;
            } catch (error) {
                this.$modal.msgError("获取删除申请列表失败");
            }
        },
        async approveApply(applyId, approvalStatus) {
            try {
                await approveWorkOrderDeleteApply(applyId, approvalStatus);
                this.$modal.msgSuccess("审批成功");
                this.getApplyList();
            } catch (error) {
                this.$modal.msgError("审批失败");
            }
        }
    }
}
</script>