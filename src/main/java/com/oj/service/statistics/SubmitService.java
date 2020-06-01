package com.oj.service.statistics;
import com.oj.frameUtil.JqueryDataTableDto;

import java.util.List;
import java.util.Map;


public interface SubmitService {
    //public JqueryDataTableDto getSubmitStatusMaplist(String start, String count, String student_name, String account, String college_id, String class_id, String is_headache);
    public List<Map> getSubmitStatusMaplist(Map<String, String> param);
    public List<Map> getFeverMaplist(Map<String, String> param);

    public Map<String, String> submitForm(Map<String, String> param);
}
