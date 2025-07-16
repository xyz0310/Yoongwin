package com.ruoyi.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.service.HolidayService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class HolidayServiceImpl implements HolidayService {

    // ✅ 正确的 API 地址：支持按年获取节假日信息
    private static final String HOLIDAY_API_YEAR = "http://timor.tech/api/holiday/year/";

    // 每年缓存一次：年 -> 节假日 Set
    private static final Map<Integer, Set<LocalDate>> yearHolidayCache = new HashMap<>();

    @Override
    public Set<LocalDate> getHolidaySet() {
        // 默认返回当前年节假日
        int year = LocalDate.now().getYear();
        return getHolidaySetBetween(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));
    }

    /**
     * 自动根据起止日期跨年加载所有所需节假日
     */
    @Override
    public Set<LocalDate> getHolidaySetBetween(LocalDate startDate, LocalDate endDate) {
        Set<LocalDate> result = new HashSet<>();

        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            if (!yearHolidayCache.containsKey(year)) {
                loadHolidayFromApi(year);
            }
            result.addAll(yearHolidayCache.getOrDefault(year, Collections.emptySet()));
        }

        return result;
    }

    /**
     * 请求并缓存某一年节假日
     */
    private void loadHolidayFromApi(int year) {
            try {
                String url = "https://timor.tech/api/holiday/year/" + year + "/";
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.getForObject(url, String.class);

                JSONObject json = JSON.parseObject(response);
                JSONObject holidays = json.getJSONObject("holiday");

                Set<LocalDate> holidaySet = new HashSet<>();

                for (String key : holidays.keySet()) {
                    JSONObject detail = holidays.getJSONObject(key);
                    Boolean isHoliday = detail.getBoolean("holiday");
                    String dateStr = detail.getString("date"); // ✅ 使用完整日期字段
                    if (Boolean.TRUE.equals(isHoliday) && dateStr != null) {
                        holidaySet.add(LocalDate.parse(dateStr));
                    }
                }

                yearHolidayCache.put(year, holidaySet);
            } catch (Exception e) {
                System.err.println("❌ 获取节假日失败：" + e.getMessage());
                e.printStackTrace();
            }
        }

    /**
     * 可选：清空所有缓存（用于管理后台刷新）
     */
    public void clearHolidayCache() {
        yearHolidayCache.clear();
        System.out.println("✅ 已清空所有节假日缓存");
    }
}
