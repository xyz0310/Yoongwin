package com.ruoyi.order.domain;

import lombok.Data;
import java.util.Date;

@Data
public class WorkOrderApproval {
    private Long id;
    private Long workOrderId;
    private Long applicantId;
    private Long approverId;
    private String status;
    private Date applyTime;
    private Date approvalTime;

}