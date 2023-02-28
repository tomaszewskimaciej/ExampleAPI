package io.example.app.backend.rest.controller;

import io.example.app.backend.common.type.TaskStatusType;
import io.example.app.backend.rest.controller.version.RestApiVersion;
import io.example.app.backend.rest.model.task.TaskResponse;
import io.example.app.backend.rest.model.user.UserResponse;
import io.example.app.backend.rest.service.TaskRestService;
import io.example.app.backend.rest.utility.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@ContextConfiguration(classes = TaskController.class)
class TaskControllerTest extends BaseUnitTest {

    @MockBean
    private TaskRestService service;


    @Test
    public void shouldReturnTask() throws Exception {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(service.getTaskById(any())).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = get(RestApiVersion.version + "/task/1")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        TaskResponse actual = jsonToObject(callApi(mockHttp, HttpStatus.OK), TaskResponse.class);
        //then
        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getStatus(), actual.getStatus())
        );
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //given
        TaskResponse expected = exampleTaskResponse();
        given(service.createTask(any())).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = post(RestApiVersion.version + "/task")
                .content(jsonToString(expected))
                .contentType(MediaType.APPLICATION_JSON);
        //when
        TaskResponse actual = jsonToObject(callApi(mockHttp, HttpStatus.OK), TaskResponse.class);
        //then
        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getDescription(), actual.getDescription()),
                () -> assertEquals(expected.getFinishDate(), actual.getFinishDate())
        );
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        Integer id = 1;
        //when
        String response = callApi(delete(RestApiVersion.version + "/task/" + id), HttpStatus.NO_CONTENT);
        //then
        verify(service, times(1)).deleteTask(id);
        assertEquals("", response);
    }

    @Test
    public void shouldChangeTaskStatus() throws Exception {
        // given
        int taskId = 1;
        TaskStatusType status = TaskStatusType.CLOSED;
        TaskResponse expected = exampleTaskResponse();
        given(service.changeStatus(taskId, status)).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = put(RestApiVersion.version + "/task/change-status/" + taskId + "/" + status)
                .contentType(MediaType.APPLICATION_JSON);
        // when
        TaskResponse actual = jsonToObject(callApi(mockHttp, HttpStatus.OK), TaskResponse.class);

        // then
        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getStatus(), actual.getStatus())
        );
    }

    @Test
    public void shouldAssignUserToTask() throws Exception {
        //given
        Integer taskId = 1;
        Integer userId = 2;
        TaskResponse expected = exampleTaskResponse();

        given(service.assignUser(taskId, userId)).willReturn(expected);
        MockHttpServletRequestBuilder mockHttp = put(RestApiVersion.version + "/task/assign-user/" + taskId + "/" + userId)
                .contentType(MediaType.APPLICATION_JSON);
        //when
        TaskResponse actual = jsonToObject(callApi(mockHttp, HttpStatus.OK), TaskResponse.class);
        //then
        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getUsers(), actual.getUsers())
        );
    }

    @Test
    public void shouldReturnMatchingTasksWhenSearchTermIsProvided() throws Exception {
        // given
        List<TaskResponse> expectedList = Arrays.asList(exampleTaskResponse());
        given(service.searchTasks("Java")).willReturn(expectedList);
        MockHttpServletRequestBuilder mockHttp = get(RestApiVersion.version + "/task")
                .param("search", "Java")
                .contentType(MediaType.APPLICATION_JSON);
        // when
        List<TaskResponse> actualList = jsonToList(callApi(mockHttp, HttpStatus.OK), TaskResponse.class);
        // then
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            TaskResponse expected = expectedList.get(i);
            TaskResponse actual = actualList.get(i);
            assertAll(
                    () -> assertEquals(expected.getId(), actual.getId()),
                    () -> assertEquals(expected.getTitle(), actual.getTitle()),
                    () -> assertEquals(expected.getDescription(), actual.getDescription()),
                    () -> assertEquals(expected.getStatus(), actual.getStatus())
            );
        }
    }

    @Test
    public void shouldReturnAllTasksWhenSearchTermIsNull() throws Exception {
        // given
        List<TaskResponse> expectedList = Arrays.asList(exampleTaskResponse(),exampleTaskResponse());
        given(service.searchTasks(null)).willReturn(expectedList);
        MockHttpServletRequestBuilder requestBuilder = get(RestApiVersion.version + "/task")
                .contentType(MediaType.APPLICATION_JSON);
        // when
        List<TaskResponse> actualList = jsonToList(callApi(requestBuilder, HttpStatus.OK), TaskResponse.class);
        // then
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            TaskResponse expected = expectedList.get(i);
            TaskResponse actual = actualList.get(i);
            assertAll(
                    () -> assertEquals(expected.getId(), actual.getId()),
                    () -> assertEquals(expected.getTitle(), actual.getTitle()),
                    () -> assertEquals(expected.getDescription(), actual.getDescription()),
                    () -> assertEquals(expected.getStatus(), actual.getStatus())
            );
        }
    }

    public static TaskResponse exampleTaskResponse() {
        return new TaskResponse(1, "Java", "Short one", null, null, null);
    }
}