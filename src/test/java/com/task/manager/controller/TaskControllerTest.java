package com.task.manager.controller;

import com.task.manager.model.Task;
import com.task.manager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskControllerTest {

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Mockito.when(taskService.getAllTasks()).thenReturn
                (Arrays.asList(new Task(), new Task()));


         List<Task> tasks = taskController.getAllTasks();
         assertEquals(2, tasks.size());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(10L);
        Mockito.when(taskService.getTaskById(Mockito.anyLong())).thenReturn(task);
        Task actual = taskController.getTaskById(2L);
        assertEquals(10L, actual.getId());
    }


    @Test
    void testUpdateAndInsertTask() {
        Task task = new Task();
        task.setTitle("ABC");
        Mockito.when(taskService.createTask(task)).thenReturn(task);
        Task inserted = taskController.createTask(task);
        Mockito.verify(taskService, Mockito.times(1)).createTask(Mockito.any());
        task.setTitle("DEF");
        Mockito.when(taskService.updateTask(Mockito.any())).thenReturn(task);
        Task actual = taskController.updateTask(10L, task);
        assertEquals("DEF", actual.getTitle());
    }

    @Test
    void testDeleteTask() {
        Mockito.doNothing().when(taskService).deleteTask(10L);
        taskController.deleteTask(10L);
        Mockito.verify(taskService, Mockito.times(1)).deleteTask(Mockito.anyLong());
    }


}
