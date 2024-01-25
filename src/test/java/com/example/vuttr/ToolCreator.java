package com.example.vuttr;

import com.example.vuttr.dto.ToolDTO;
import com.example.vuttr.model.Tool;

import java.util.ArrayList;

public class ToolCreator {
    public static Tool createTool(){
        return new Tool("Notion", "https://www.notion.so/", "All In One Workspace", new ArrayList<>());
    }

    public static ToolDTO createDTO(){
        return new ToolDTO("Notion", "https://www.notion.so/", "All In One Workspace", new ArrayList<>());
    }
}
