package com.algaworks.osworks.domain.model;

import javax.validation.constraints.NotBlank;

public class CommentaryInput {

    @NotBlank
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
