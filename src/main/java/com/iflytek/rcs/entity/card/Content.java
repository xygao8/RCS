package com.iflytek.rcs.entity.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iflytek.rcs.entity.suggestion.Suggestion;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {
    private Media media;
    /**
     * Rich card 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 建议对象
     */
    private List<Suggestion> suggestions;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}