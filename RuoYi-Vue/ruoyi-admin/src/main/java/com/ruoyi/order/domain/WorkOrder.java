package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单记录对象 work_order
 * 
 * @author wyh
 * @date 2025-06-26
 */
public class WorkOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 技术员 */
    @Excel(name = "技术员")
    private String 技术员;

    /** 业务员 */
    @Excel(name = "业务员")
    private String 业务员;

    /** 工单主题 */
    @Excel(name = "工单主题")
    private String 工单主题;

    /** 相关内容 */
    @Excel(name = "相关内容")
    private String 相关内容;

    /** 状态 */
    @Excel(name = "状态")
    private String 状态;

    /** 单号 */
    @Excel(name = "单号")
    private String 单号;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date 创建时间;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date 修改时间;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void set技术员(String 技术员) 
    {
        this.技术员 = 技术员;
    }

    public String get技术员() 
    {
        return 技术员;
    }

    public void set业务员(String 业务员) 
    {
        this.业务员 = 业务员;
    }

    public String get业务员() 
    {
        return 业务员;
    }

    public void set工单主题(String 工单主题) 
    {
        this.工单主题 = 工单主题;
    }

    public String get工单主题() 
    {
        return 工单主题;
    }

    public void set相关内容(String 相关内容) 
    {
        this.相关内容 = 相关内容;
    }

    public String get相关内容() 
    {
        return 相关内容;
    }

    public void set状态(String 状态) 
    {
        this.状态 = 状态;
    }

    public String get状态() 
    {
        return 状态;
    }

    public void set单号(String 单号) 
    {
        this.单号 = 单号;
    }

    public String get单号() 
    {
        return 单号;
    }

    public void set创建时间(Date 创建时间) 
    {
        this.创建时间 = 创建时间;
    }

    public Date get创建时间() 
    {
        return 创建时间;
    }

    public void set修改时间(Date 修改时间) 
    {
        this.修改时间 = 修改时间;
    }

    public Date get修改时间() 
    {
        return 修改时间;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("技术员", get技术员())
            .append("业务员", get业务员())
            .append("工单主题", get工单主题())
            .append("相关内容", get相关内容())
            .append("状态", get状态())
            .append("单号", get单号())
            .append("创建时间", get创建时间())
            .append("修改时间", get修改时间())
            .toString();
    }
}
