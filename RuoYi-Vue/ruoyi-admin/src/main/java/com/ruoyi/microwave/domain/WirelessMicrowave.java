package com.ruoyi.microwave.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 无线微波器械对象 wireless_microwave
 * 
 * @author wyh
 * @date 2025-06-20
 */
public class WirelessMicrowave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String 产品名称;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String 产品型号;

    /** 应用场景 */
    @Excel(name = "应用场景")
    private String 应用场景;

    /** 供货厂商 */
    @Excel(name = "供货厂商")
    private String 供货厂商;

    /** 频率范围 */
    @Excel(name = "频率范围")
    private String 频率范围;

    /** 功率容量 */
    @Excel(name = "功率容量")
    private String 功率容量;

    /** 插入损耗 */
    @Excel(name = "插入损耗")
    private String 插入损耗;

    /** 接头类型 */
    @Excel(name = "接头类型")
    private String 接头类型;

    /** PIM */
    @Excel(name = "PIM")
    private String PIM;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void set产品名称(String 产品名称) 
    {
        this.产品名称 = 产品名称;
    }

    public String get产品名称() 
    {
        return 产品名称;
    }

    public void set产品型号(String 产品型号) 
    {
        this.产品型号 = 产品型号;
    }

    public String get产品型号() 
    {
        return 产品型号;
    }

    public void set应用场景(String 应用场景) 
    {
        this.应用场景 = 应用场景;
    }

    public String get应用场景() 
    {
        return 应用场景;
    }

    public void set供货厂商(String 供货厂商) 
    {
        this.供货厂商 = 供货厂商;
    }

    public String get供货厂商() 
    {
        return 供货厂商;
    }

    public void set频率范围(String 频率范围) 
    {
        this.频率范围 = 频率范围;
    }

    public String get频率范围() 
    {
        return 频率范围;
    }

    public void set功率容量(String 功率容量) 
    {
        this.功率容量 = 功率容量;
    }

    public String get功率容量() 
    {
        return 功率容量;
    }

    public void set插入损耗(String 插入损耗) 
    {
        this.插入损耗 = 插入损耗;
    }

    public String get插入损耗() 
    {
        return 插入损耗;
    }

    public void set接头类型(String 接头类型) 
    {
        this.接头类型 = 接头类型;
    }

    public String get接头类型() 
    {
        return 接头类型;
    }

    public void setPIM(String PIM) 
    {
        this.PIM = PIM;
    }

    public String getPIM() 
    {
        return PIM;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("产品名称", get产品名称())
            .append("产品型号", get产品型号())
            .append("应用场景", get应用场景())
            .append("供货厂商", get供货厂商())
            .append("频率范围", get频率范围())
            .append("功率容量", get功率容量())
            .append("插入损耗", get插入损耗())
            .append("接头类型", get接头类型())
            .append("PIM", getPIM())
            .toString();
    }
}
