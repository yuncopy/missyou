package com.lin.missyou.sample.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping("/test")
    public void getByName() throws Exception{
        System.out.println("TaskController getByName");
        TaskCenterModel taskCenterModel = new TaskCenterModel();
        taskCenterModel.setUuid(System.currentTimeMillis()+"123");
        taskServiceImpl.createTask(taskCenterModel);
    }

}
