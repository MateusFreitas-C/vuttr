package com.example.vuttr.dto;

import java.util.List;

public record ToolDTO(String title, String link, String description, List<String> tags) {
}
