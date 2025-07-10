package com.ruoyi.ups.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ups电源对象 ups
 * 
 * @author wyh
 * @date 2025-06-19
 */
public class Ups extends BaseEntity
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

    /** 容量 */
    @Excel(name = "容量")
    private String 容量;

    /** 功率 */
    @Excel(name = "功率")
    private String 功率;

    /** 输入电压大小 */
    @Excel(name = "输入电压大小")
    private String 输入电压大小;

    /** 入电压相位 */
    @Excel(name = "输入电压相位")
    private String 输入电压相位;

    /** 输入电压分类 */
    @Excel(name = "输入电压分类")
    private String 输入电压分类;

    /** 输出电压大小 */
    @Excel(name = "输出电压大小")
    private String 输出电压大小;

    /** 输出电压相位 */
    @Excel(name = "输出电压相位")
    private String 输出电压相位;

    /** 输出电压分类 */
    @Excel(name = "输出电压分类")
    private String 输出电压分类;

    /** 输入频率 */
    @Excel(name = "输入频率")
    private String 输入频率;

    /** 输出频率 */
    @Excel(name = "输出频率")
    private String 输出频率;

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

    public void set容量(String 容量) 
    {
        this.容量 = 容量;
    }

    public String get容量() 
    {
        return 容量;
    }

    public void set功率(String 功率) 
    {
        this.功率 = 功率;
    }

    public String get功率() 
    {
        return 功率;
    }

    public void set输入电压大小(String 输入电压大小) 
    {
        this.输入电压大小 = 输入电压大小;
    }

    public String get输入电压大小() 
    {
        return 输入电压大小;
    }

    public void set输入电压相位(String 输入电压相位)
    {
        this.输入电压相位 = 输入电压相位;
    }

    public String get输入电压相位()
    {
        return 输入电压相位;
    }

    public void set输入电压分类(String 输入电压分类) 
    {
        this.输入电压分类 = 输入电压分类;
    }

    public String get输入电压分类() 
    {
        return 输入电压分类;
    }

    public void set输出电压大小(String 输出电压大小) 
    {
        this.输出电压大小 = 输出电压大小;
    }

    public String get输出电压大小() 
    {
        return 输出电压大小;
    }

    public void set输出电压相位(String 输出电压相位) 
    {
        this.输出电压相位 = 输出电压相位;
    }

    public String get输出电压相位() 
    {
        return 输出电压相位;
    }

    public void set输出电压分类(String 输出电压分类) 
    {
        this.输出电压分类 = 输出电压分类;
    }

    public String get输出电压分类() 
    {
        return 输出电压分类;
    }

    public void set输入频率(String 输入频率)
    {
        this.输入频率 = 输入频率;
    }

    public String get输入频率()
    {
        return 输入频率;
    }

    public void set输出频率(String 输出频率)
    {
        this.输出频率 = 输出频率;
    }

    public String get输出频率()
    {
        return 输出频率;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("产品系列", get产品系列())
            .append("产品型号", get产品型号())
            .append("应用场景", get应用场景())
            .append("供货厂商", get供货厂商())
            .append("容量", get容量())
            .append("功率", get功率())
            .append("输入电压大小", get输入电压大小())
            .append("输入电压相位", get输入电压相位())
            .append("输入电压分类", get输入电压分类())
            .append("输出电压大小", get输出电压大小())
            .append("输出电压相位", get输出电压相位())
            .append("输出电压分类", get输出电压分类())
            .append("输入频率", get输入频率())
            .append("输出频率", get输出频率())
            .toString();
    }
}
