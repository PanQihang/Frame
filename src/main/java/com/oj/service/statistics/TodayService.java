package com.oj.service.statistics;
import com.oj.frameUtil.JqueryDataTableDto;

import java.util.List;
import java.util.Map;


public interface TodayService {
    public Map<String,String> getStatus(String college_id);

    public List<Map> getStatusMaplist(String college_id);

    public List<Map> getNoStatusMaplist(String college_id);
}
