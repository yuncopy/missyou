package com.lin.missyou.sample.task;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl {
    /**
     * 创建任务
     */
    @TaskEvent(type = TaskEventEnum.CREATE_TASK)
    public TaskCenterModel createTask( TaskCenterModel taskCenterModel) {
        System.out.println("createTask");
        System.out.println("TaskController getByName:"+taskCenterModel);
        return taskCenterModel;
    }

    @TaskEvent(type = TaskEventEnum.REGISTER_TASK)
    public TaskCenterModel registerTask(TaskCenterModel taskCenterModel) {
        System.out.println("registerTask");
        System.out.println("TaskController getByName:"+taskCenterModel);
        return taskCenterModel;
    }

    /**
     * 执行任务
     */
    @TaskEvent(type = TaskEventEnum.EXECUTE_TASK)
    public TaskCenterModel executeTask(TaskCenterModel taskCenterModel) {
        System.out.println("executeTask");
        System.out.println("TaskController getByName:"+taskCenterModel);
       return taskCenterModel;
    }

}
