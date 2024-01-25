package com.example.vuttr.service;

import com.example.vuttr.ToolCreator;
import com.example.vuttr.dto.ToolDTO;
import com.example.vuttr.exception.ToolAlreadyExistsException;
import com.example.vuttr.exception.ToolNotFoundException;
import com.example.vuttr.model.Tool;
import com.example.vuttr.repository.ToolsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ToolsServiceTest {
    @InjectMocks
    ToolsServiceImpl mockToolService;

    @Mock
    ToolsRepository mockToolRepository;

    @Test
    void findByTitleShouldReturnTool(){
        Tool expected = ToolCreator.createTool();

        when(mockToolRepository.findByTitle(any())).thenReturn(Optional.of(expected));

        Tool actual = mockToolService.findByTitle("Notion");

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findToolByTitleShouldThrowException(){
        when(mockToolRepository.findByTitle(any())).thenThrow(ToolNotFoundException.class);

        assertThrows(ToolNotFoundException.class,
                () -> mockToolService.findByTitle("Title"));
    }

    @Test
    void createShouldReturnCreatedTool(){
        Tool expected = ToolCreator.createTool();
        ToolDTO dto = ToolCreator.createDTO();

        when(mockToolRepository.existsByTitle(dto.title())).thenReturn(false);

        Tool actual = mockToolService.createTool(dto);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> verify(mockToolRepository).save(any())
        );
    }

    @Test
    void createExistentToolShouldThrowException(){
        when(mockToolRepository.existsByTitle(any())).thenReturn(true);

        ToolDTO dto = ToolCreator.createDTO();

        assertThrows(ToolAlreadyExistsException.class,
                () -> mockToolService.createTool(dto));
    }

    @Test
    void getAllShouldReturnList(){
        List<Tool> expected = List.of(ToolCreator.createTool());

        when(mockToolRepository.findAll()).thenReturn(expected);

        List<Tool> actual = mockToolService.getAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void getByTagShouldReturnList(){
        List<Tool> expected = List.of(ToolCreator.createTool());

        when(mockToolRepository.findAllByTags(any())).thenReturn(expected);

        List<Tool> actual = mockToolService.filterByTag("Organizing");

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteByTitleShouldDelete(){
        Tool tool = ToolCreator.createTool();

        when(mockToolRepository.findByTitle(any())).thenReturn(Optional.of(tool));

        mockToolService.deleteToolByTitle("Notion");

        verify(mockToolRepository).findByTitle("Notion");
        verify(mockToolRepository).delete(tool);
    }
}