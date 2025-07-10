package com.ruoyi.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.WorkOrderMapper;
import com.ruoyi.order.domain.WorkOrder;
import com.ruoyi.order.service.IWorkOrderService;

/**
 * 工单记录Service业务层处理
 * 
 * @author wyh
 * @date 2025-06-26
 */
@Service
public class WorkOrderServiceImpl implements IWorkOrderService 
{
    @Autowired
    private WorkOrderMapper workOrderMapper;

    /**
     * 查询工单记录
     * 
     * @param id 工单记录主键
     * @return 工单记录
     */
    @Override
    public WorkOrder selectWorkOrderById(Long id)
    {
        return workOrderMapper.selectWorkOrderById(id);
    }

    /**
     * 查询工单记录列表
     * 
     * @param workOrder 工单记录
     * @return 工单记录
     */
    @Override
    public List<WorkOrder> selectWorkOrderList(WorkOrder workOrder)
    {
        return workOrderMapper.selectWorkOrderList(workOrder);
    }

    /**
     * 新增工单记录
     * 
     * @param workOrder 工单记录
     * @return 结果
     */
    @Override
    public int insertWorkOrder(WorkOrder workOrder)
    {
        // 生成单号
        String orderNumber = generateOrderNumber(workOrder.get技术员(),workOrder.get业务员());
        workOrder.set单号(orderNumber);

        workOrder.set创建时间(new Date());
        workOrder.set修改时间(new Date());
        return workOrderMapper.insertWorkOrder(workOrder);
    }

    /**
     * 修改工单记录
     * 
     * @param workOrder 工单记录
     * @return 结果
     */
    @Override
    public int updateWorkOrder(WorkOrder workOrder)
    {
        // 获取原记录的创建时间
        WorkOrder originalOrder = workOrderMapper.selectWorkOrderById(workOrder.getId());
        workOrder.set创建时间(originalOrder.get创建时间());
        // 设置修改时间为当前时间
        workOrder.set修改时间(new Date());
        return workOrderMapper.updateWorkOrder(workOrder);
    }

    /**
     * 批量删除工单记录
     * 
     * @param ids 需要删除的工单记录主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrderByIds(Long[] ids)
    {
        return workOrderMapper.deleteWorkOrderByIds(ids);
    }

    /**
     * 删除工单记录信息
     * 
     * @param id 工单记录主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrderById(Long id)
    {
        return workOrderMapper.deleteWorkOrderById(id);
    }

    /**
     * 获取技术员列表
     */
    @Override
    public List<String> getTechnicians() {
        return workOrderMapper.getTechnicians();
    }


    /**
     * 获取业务员列表
     */
    @Override
    public List<String> getSalesmen() {
        return workOrderMapper.getSalesmen();
    }

    /**
     * 生成单号
     * @param salesman 业务员名称
     * @return 单号
     */
    private String generateOrderNumber(String technician, String salesman) {
        // 获取当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String currentDate = dateFormat.format(new Date());

        // 获取业务员名称拼音首字母大写
        String initials = getPinyinInitials(salesman);

        // 统计该技术员和业务员组合已有的工单记录数量
        WorkOrder query = new WorkOrder();
        query.set技术员(technician);
        query.set业务员(salesman);
        List<WorkOrder> list = workOrderMapper.selectWorkOrderList(query);
        int count = list.size() + 1;

        // 格式化序号
        String serialNumber = String.format("%02d", count);

        // 组合单号
        return currentDate + "-" + initials + "-" + serialNumber;
    }

    /**
     * 获取业务员名称拼音首字母大写
     * @param name 业务员名称
     * @return 拼音首字母大写
     */
    private String getPinyinInitials(String name) {
        StringBuilder initials = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (char c : name.toCharArray()) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        initials.append(pinyinArray[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                initials.append(Character.toUpperCase(c));
            }
        }
        return initials.toString();
    }

    /**
     * 获取业务员名称英文首字母大写
     * @param name 业务员名称
     * @return 英文首字母大写
     */
    private String getInitials(String name) {
        StringBuilder initials = new StringBuilder();
        String[] parts = name.split("\\s+");
        for (String part : parts) {
            if (!part.isEmpty()) {
                initials.append(Character.toUpperCase(part.charAt(0)));
            }
        }
        return initials.toString();
    }
}
