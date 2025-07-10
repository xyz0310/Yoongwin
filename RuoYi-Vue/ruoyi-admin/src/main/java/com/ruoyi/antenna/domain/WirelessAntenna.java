package com.ruoyi.antenna.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 无线天线对象 wireless_antenna
 * 
 * @author wyh
 * @date 2025-07-01
 */
public class WirelessAntenna extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 天线种类 */
    @Excel(name = "天线种类")
    private String 天线种类;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String 产品型号;

    /** 应用场景 */
    @Excel(name = "应用场景")
    private String 应用场景;

    /** 供货厂商 */
    @Excel(name = "供货厂商")
    private String 供货厂商;

    /** 室内外 */
    @Excel(name = "室内外")
    private String 室内外;

    /** 全定向 */
    @Excel(name = "全定向")
    private String 全定向;

    /** 频率范围 */
    @Excel(name = "频率范围")
    private String 频率范围;

    /** 驻波比 */
    @Excel(name = "驻波比")
    private String 驻波比;

    /** 增益 */
    @Excel(name = "增益")
    private String 增益;

    /** 极化方式 */
    @Excel(name = "极化方式")
    private String 极化方式;

    /** 功率 */
    @Excel(name = "功率")
    private String 功率;

    /** 阻抗 */
    @Excel(name = "阻抗")
    private String 阻抗;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void set天线种类(String 天线种类) 
    {
        this.天线种类 = 天线种类;
    }

    public String get天线种类() 
    {
        return 天线种类;
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

    public void set室内外(String 室内外) 
    {
        this.室内外 = 室内外;
    }

    public String get室内外() 
    {
        return 室内外;
    }

    public void set全定向(String 全定向) 
    {
        this.全定向 = 全定向;
    }

    public String get全定向() 
    {
        return 全定向;
    }

    public void set频率范围(String 频率范围) 
    {
        this.频率范围 = 频率范围;
    }

    public String get频率范围() 
    {
        return 频率范围;
    }

    public void set驻波比(String 驻波比) 
    {
        this.驻波比 = 驻波比;
    }

    public String get驻波比() 
    {
        return 驻波比;
    }

    public void set增益(String 增益) 
    {
        this.增益 = 增益;
    }

    public String get增益() 
    {
        return 增益;
    }

    public void set极化方式(String 极化方式) 
    {
        this.极化方式 = 极化方式;
    }

    public String get极化方式() 
    {
        return 极化方式;
    }

    public void set功率(String 功率) 
    {
        this.功率 = 功率;
    }

    public String get功率() 
    {
        return 功率;
    }

    public void set阻抗(String 阻抗) 
    {
        this.阻抗 = 阻抗;
    }

    public String get阻抗() 
    {
        return 阻抗;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("天线种类", get天线种类())
            .append("产品型号", get产品型号())
            .append("应用场景", get应用场景())
            .append("供货厂商", get供货厂商())
            .append("室内外", get室内外())
            .append("全定向", get全定向())
            .append("频率范围", get频率范围())
            .append("驻波比", get驻波比())
            .append("增益", get增益())
            .append("极化方式", get极化方式())
            .append("功率", get功率())
            .append("阻抗", get阻抗())
            .toString();
    }
}
