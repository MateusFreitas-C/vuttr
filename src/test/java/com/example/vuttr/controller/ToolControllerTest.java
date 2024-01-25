package com.example.vuttr.controller;

import com.example.vuttr.ToolCreator;
import com.example.vuttr.exception.ToolAlreadyExistsException;
import com.example.vuttr.exception.ToolNotFoundException;
import com.example.vuttr.model.Tool;
import com.example.vuttr.service.ToolsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ToolControllerTest {
    @InjectMocks
    ToolController mockToolController;

    @Mock
    ToolsService mockToolsService;

    @Test
    void getToolByTitleShouldReturnResponseWithTool(){
        Tool expected = ToolCreator.createTool();

        when(mockToolsService.findByTitle(anyString())).thenReturn(expected);

        ResponseEntity<Tool> response = mockToolController.getToolByTitle("Notion");

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected, response.getBody())
        );
    }

    @Test
    void getToolByTitleShouldThrowException(){
        when(mockToolsService.findByTitle(any())).thenThrow(ToolNotFoundException.class);

        assertThrows(ToolNotFoundException.class,
                () -> mockToolController.getToolByTitle("Title"));
    }

    @Test
    void createShouldReturnCreatedTool(){
        Tool expected = ToolCreator.createTool();

        when(mockToolsService.createTool(any())).thenReturn(expected);

        ResponseEntity<Tool> response = mockToolController.createTool(ToolCreator.createDTO());

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected, response.getBody())
        );
    }

    @Test
    void createExistentToolShouldThrowException(){
        when(mockToolsService.createTool(any())).thenThrow(ToolAlreadyExistsException.class);

        assertThrows(ToolAlreadyExistsException.class,
                () -> mockToolController.createTool(ToolCreator.createDTO()));
    }

    @Test
    void getAllToolsShouldReturnToolList(){
        List<Tool> expected = List.of(ToolCreator.createTool());

        when(mockToolsService.getAll()).thenReturn(expected);

        ResponseEntity<List<Tool>> response = mockToolController.getAllTools();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expected.size(), Objects.requireNonNull(response.getBody()).size()),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected, response.getBody())
        );
    }

    @Test
    void getByTagShouldReturnList(){
        List<Tool> expected = List.of(ToolCreator.createTool());

        when(mockToolsService.filterByTag(any())).thenReturn(expected);

        ResponseEntity<List<Tool>> response = mockToolController.getToolsByTag("Organizing");

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expected.size(), Objects.requireNonNull(response.getBody()).size()),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected, response.getBody())
        );
    }

    @Test
    void deleteByTitleShouldReturnMessage(){
        String expected = "Tool deleted";

        Tool tool = ToolCreator.createTool();

        when(mockToolsService.findByTitle(anyString())).thenReturn(tool);

        ResponseEntity<String> response = mockToolController.deleteToolByTitle("Notion");

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected, response.getBody())
        );
    }
}