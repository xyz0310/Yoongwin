package com.ruoyi.opticalFiber.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 光纤光缆对象 optical_communications
 * 
 * @author wyh
 * @date 2025-06-20
 */
public class opticalCommunications extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String 产品类型;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String 产品型号;

    /** 应用场景 */
    @Excel(name = "应用场景")
    private String 应用场景;

    /** 供货厂商 */
    @Excel(name = "供货厂商")
    private String 供货厂商;

    /** 光纤芯数 */
    @Excel(name = "光纤芯数")
    private String 光纤芯数;

    /** 排列方式 */
    @Excel(name = "排列方式")
    private String 排列方式;

    /** 排列方式 */
    @Excel(name = "排列方式")
    private String 光纤类型;

    /** 传输波长 */
    @Excel(name = "传输波长")
    private String 传输波长;

    /** 长度 */
    @Excel(name = "长度")
    private String 长度;

    /** 接头类型 */
    @Excel(name = "接头类型")
    private String 接头类型;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void set产品类型(String 产品类型) 
    {
        this.产品类型 = 产品类型;
    }

    public String get产品类型() 
    {
        return 产品类型;
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

    public void set光纤芯数(String 光纤芯数) 
    {
        this.光纤芯数 = 光纤芯数;
    }

    public String get光纤芯数() 
    {
        return 光纤芯数;
    }

    public void set排列方式(String 排列方式) 
    {
        this.排列方式 = 排列方式;
    }

    public String get排列方式() 
    {
        return 排列方式;
    }

    public void set光纤类型(String 光纤类型) 
    {
        this.光纤类型 = 光纤类型;
    }

    public String get光纤类型() 
    {
        return 光纤类型;
    }

    public void set传输波长(String 传输波长) 
    {
        this.传输波长 = 传输波长;
    }

    public String get传输波长() 
    {
        return 传输波长;
    }

    public void set长度(String 长度) 
    {
        this.长度 = 长度;
    }

    public String get长度() 
    {
        return 长度;
    }

    public void set接头类型(String 接头类型) 
    {
        this.接头类型 = 接头类型;
    }

    public String get接头类型() 
    {
        return 接头类型;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("产品类型", get产品类型())
            .append("产品型号", get产品型号())
            .append("应用场景", get应用场景())
            .append("供货厂商", get供货厂商())
            .append("光纤芯数", get光纤芯数())
            .append("排列方式", get排列方式())
            .append("光纤类型", get光纤类型())
            .append("传输波长", get传输波长())
            .append("长度", get长度())
            .append("接头类型", get接头类型())
            .toString();
    }
}
