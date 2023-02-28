package io.example.app.backend.rest.service;

import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.adapter.TaskServiceAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskRestServiceTest {
    @Mock
    private TaskServiceAdapter adapter;

    @InjectMocks
    private TaskRestService service;

    @Test
    public void shouldReturnTask() {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(adapter.getTaskById(any())).willReturn(expected);
        //when
        final TaskResponse actual = service.getTaskById(any());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateTask() {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(adapter.createTask(any())).willReturn(expected);
        //when
        final TaskResponse actual = service.createTask(any());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldUpdateTask() {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(adapter.updateTask(any(), any())).willReturn(expected);
        //when
        final TaskResponse actual = service.updateTask(any(), any());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCallAdapterDeleteTask() {
        //given
        Integer id = 1;
        //when
        service.deleteTask(id);
        //then
        verify(adapter, times(1)).deleteTask(id);
    }

    @Test
    public void shouldChangeStatus() {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(adapter.changeStatus(any(), any())).willReturn(expected);
        //when
        final TaskResponse actual = service.changeStatus(any(), any());
        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAssignUser() {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(adapter.assignUser(any(), any())).willReturn(expected);
        //when
        final TaskResponse actual = service.assignUser(any(), any());
        //then
        assertEquals(expected, actual);
    }


    public static TaskResponse exampleTaskResponse() {
        return new TaskResponse(1, "Java", "Short one", null, null, null);
    }

}