package com.example.vuttr.controller;

import com.example.vuttr.dto.ToolDTO;
import com.example.vuttr.model.Tool;
import com.example.vuttr.service.ToolsService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController @RequestMapping("/tools")
public class ToolController {
    private final ToolsService service;

    @GetMapping("/{title}")
    public ResponseEntity<Tool> getToolByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Tool> createTool(@RequestBody ToolDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTool(dto));
    }

    @GetMapping
    public ResponseEntity<List<Tool>> getAllTools() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping(params = "tag")
    public ResponseEntity<List<Tool>> getToolsByTag(@RequestParam String tag) {
        return ResponseEntity.status(HttpStatus.OK).body(service.filterByTag(tag));
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<String> deleteToolByTitle(@PathVariable String title){
        service.deleteToolByTitle(title);

        return ResponseEntity.status(HttpStatus.OK).body("Tool deleted");
    }
}
