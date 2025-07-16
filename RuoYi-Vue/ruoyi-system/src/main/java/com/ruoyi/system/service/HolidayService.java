package com.ruoyi.system.service;

import java.time.LocalDate;
import java.util.Set;

public interface HolidayService {
    Set<LocalDate> getHolidaySet(); // 返回当前年份节假日

    Set<LocalDate> getHolidaySetBetween(LocalDate startDate, LocalDate endDate); // 新增，返回时间区间内节假日
}
