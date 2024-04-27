package com.task.manager.service;

import com.task.manager.model.Task;
import com.task.manager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Mockito.when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task actual = taskService.getTaskById(1L);
        assertEquals(task.getId(), actual.getId());
    }

    @Test
    void testGetTaskById_NotFound() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task actual = taskService.getTaskById(1L);
        assertNull(actual);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setId(1L);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        Task savedTask = taskService.createTask(task);
        assertEquals(task.getId(), savedTask.getId());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        task.setId(1L);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTask(task);
        assertEquals(task.getId(), updatedTask.getId());
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;
        Mockito.doNothing().when(taskRepository).deleteById(taskId);

        taskService.deleteTask(taskId);
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(taskId);
    }


}
