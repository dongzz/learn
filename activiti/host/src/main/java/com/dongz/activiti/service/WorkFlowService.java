package com.dongz.activiti.service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/26 5:21 下午
 */
public interface WorkFlowService {
    String startWorkflow(String pdKey, String businessKey, Map<String, Object> variables);

    void continueWorkflow(String taskId, Map variables);

    void delegateWorkflow(String taskId, Map variables);

    void endWorkflow(String pProcessInstanceId, String deleteReason);

    String getCurrentTask(String pProcessInstanceId);

    List<String> findUserProcessIds(String userId, String pdKey, Integer pageNo, Integer pageSize);

    void getProcessImage(String pProcessInstanceId, HttpServletResponse response);
}
