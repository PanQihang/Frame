package com.oj.service.statistics;

import com.oj.entity.statistics.Form;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface SubmitService {
    public List<Map> getTargetStudentList(Map<String, String> param);
}
