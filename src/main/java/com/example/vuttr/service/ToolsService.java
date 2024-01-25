package com.example.vuttr.service;

import com.example.vuttr.dto.ToolDTO;
import com.example.vuttr.model.Tool;

import java.util.List;

public interface ToolsService {
    List<Tool> getAll();

    Tool findByTitle(String title);

    List<Tool> filterByTag(String tag);

    Tool createTool(ToolDTO dto);

    void deleteToolByTitle(String title);
}
