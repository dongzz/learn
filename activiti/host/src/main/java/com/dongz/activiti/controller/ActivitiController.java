package com.dongz.activiti.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dongz.activiti.service.WorkFlowService;
import com.dongz.activiti.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/26 5:26 下午
 */

@RestController
@RequestMapping("/activiti")
@Slf4j
public class ActivitiController {

    @Resource
    private WorkFlowService workFlowService;


    /**
     * 启动请假流程
     * @param pdKey
     * @return
     */
    @PostMapping("/qj-apply")
    public Res startWorkflow(@RequestParam(required = false) String pdKey){
        Map param = new HashMap(4){{
            put("applyUserId","001");
            put("approveUserIds", Arrays.asList("001","002","003"));
        }};

        if(StringUtils.isBlank(pdKey)){
            pdKey="QjFlow";
        }
        // 启动流程
        String pdId = workFlowService.startWorkflow(pdKey, "QJ001", param);
        // 获取请假申请任务节点
        String Id = workFlowService.getCurrentTask(pdId);
        // 完成请假申请任务节点
        Map continueParam = new HashMap(2){{
            put("dealUserId",param.get("applyUserId"));
        }};
        workFlowService.continueWorkflow(Id,continueParam);
        return Res.ok("请假已提交");
    }

    /**
     * 审批请假流程
     * @param pId
     * @param result
     * @return
     */
    @PostMapping("/qj-approve")
    public Res continueWorkflow(@RequestParam String pId,@RequestParam String result){
        Map param = new HashMap(2){{
            put("dealUserId","001");
            put("result",result);
        }};

        // 获取请假审批任务节点
        String Id = workFlowService.getCurrentTask(pId);
        // 完成请假审批任务节点
        workFlowService.continueWorkflow(Id,param);
        return Res.ok("审批成功");
    }

    /**
     * 委托请假流程
     * @param pId
     * @param userId
     * @return
     */
    @PostMapping("/qj-delegate")
    public Res delegateWorkflow(@RequestParam String pId,@RequestParam String userId){
        Map param = new HashMap(2){{
            put("dealUserId",userId);
        }};
        // 获取请假审批任务节点
        String Id = workFlowService.getCurrentTask(pId);
        // 完成请假审批任务节点
        workFlowService.delegateWorkflow(Id,param);
        return Res.ok("委托成功");
    }

    /**
     *  查询用户待办流程实例
     * @param userId
     * @param pdKey
     */
    @GetMapping("/user-process")
    public Res findUserProcessIds(@RequestParam String userId, @RequestParam(required = false) String pdKey) {
        if(StringUtils.isBlank(pdKey)){
            pdKey="QjFlow";
        }
        // 获取流程图
        return Res.ok(workFlowService.findUserProcessIds(userId,pdKey,1,0));
    }

    /**
     * 读取流程资源
     * @param pId 流程实例id
     */
    @GetMapping("/read-resource")
    public void readResource(@RequestParam String pId, HttpServletResponse response) {
        // 获取流程图
        workFlowService.getProcessImage(pId, response);

    }

}