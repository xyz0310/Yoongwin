package com.ruoyi.filter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 滤波器对象 filter
 * 
 * @author wyh
 * @date 2025-06-20
 */
public class Filter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 产品系列 */
    @Excel(name = "产品系列")
    private String 产品系列;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String 产品型号;

    /** 应用场景 */
    @Excel(name = "应用场景")
    private String 应用场景;

    /** 供货厂商 */
    @Excel(name = "供货厂商")
    private String 供货厂商;

    /** 电压 */
    @Excel(name = "电压")
    private String 电压;

    /** 相位 */
    @Excel(name = "相位")
    private String 相位;

    /** 电流 */
    @Excel(name = "电流")
    private String 电流;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void set产品系列(String 产品系列) 
    {
        this.产品系列 = 产品系列;
    }

    public String get产品系列() 
    {
        return 产品系列;
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

    public void set电压(String 电压) 
    {
        this.电压 = 电压;
    }

    public String get电压() 
    {
        return 电压;
    }

    public void set相位(String 相位) 
    {
        this.相位 = 相位;
    }

    public String get相位() 
    {
        return 相位;
    }

    public void set电流(String 电流) 
    {
        this.电流 = 电流;
    }

    public String get电流() 
    {
        return 电流;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("产品系列", get产品系列())
            .append("产品型号", get产品型号())
            .append("应用场景", get应用场景())
            .append("供货厂商", get供货厂商())
            .append("电压", get电压())
            .append("相位", get相位())
            .append("电流", get电流())
            .toString();
    }
}
