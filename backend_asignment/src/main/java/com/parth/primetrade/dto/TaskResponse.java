package com.parth.primetrade.dto;

import java.time.LocalDateTime;

public class TaskResponse {
    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;

    public TaskResponse() {
    }

    public TaskResponse(String id, String title, String description, String status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static TaskResponseBuilder builder() {
        return new TaskResponseBuilder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class TaskResponseBuilder {
        private String id;
        private String title;
        private String description;
        private String status;
        private LocalDateTime createdAt;

        public TaskResponseBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TaskResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskResponseBuilder status(String status) {
            this.status = status;
            return this;
        }

        public TaskResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskResponse build() {
            return new TaskResponse(id, title, description, status, createdAt);
        }
    }
}
