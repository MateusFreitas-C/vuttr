package com.example.vuttr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter @EqualsAndHashCode
@Document("tools")
public class Tool {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String title;

    private String link;

    private String description;

    private List<String> tags;

    public Tool(String title, String link, String description, List<String> tags) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.tags = tags;
    }
}
