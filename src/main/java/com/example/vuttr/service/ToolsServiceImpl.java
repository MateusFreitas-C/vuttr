package com.example.vuttr.service;

import com.example.vuttr.dto.ToolDTO;
import com.example.vuttr.exception.ToolAlreadyExistsException;
import com.example.vuttr.exception.ToolNotFoundException;
import com.example.vuttr.model.Tool;
import com.example.vuttr.repository.ToolsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ToolsServiceImpl implements ToolsService {
    private final ToolsRepository toolsRepository;
    @Override
    public List<Tool> getAll() {
        return toolsRepository.findAll();
    }

    @Override
    public Tool findByTitle(String title) {
        return toolsRepository.findByTitle(title).orElseThrow(ToolNotFoundException::new);
    }

    @Override
    public List<Tool> filterByTag(String tag) {
        return toolsRepository.findAllByTags(tag);
    }

    @Override
    public Tool createTool(ToolDTO dto) {
        if(toolsRepository.existsByTitle(dto.title())) throw new ToolAlreadyExistsException();

        Tool tool = new Tool(dto.title(), dto.link(), dto.description(), dto.tags());
        toolsRepository.save(tool);
        return tool;
    }

    @Override
    public void deleteToolByTitle(String title) {
        toolsRepository.delete(findByTitle(title));
    }
}
