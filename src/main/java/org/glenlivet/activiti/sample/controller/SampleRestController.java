package org.glenlivet.activiti.sample.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value="/jump/{executionId}/{taskId}/{assignee}")
    public void complete(@PathVariable String executionId, @PathVariable String taskId, @PathVariable String assignee) {
        taskService.claim(taskId, assignee);
        taskService.complete(taskId);
    }

    @RequestMapping(value="/jump/{executionId}/{taskId}/{assignee}/{jumpTo}")
    public void jump(@PathVariable String executionId, @PathVariable String taskId, @PathVariable String assignee, @PathVariable String jumpTo) {
        taskService.claim(taskId, assignee);
        runtimeService.setVariableLocal(executionId, "_jump_to", jumpTo);
        taskService.complete(taskId);
    }
}
