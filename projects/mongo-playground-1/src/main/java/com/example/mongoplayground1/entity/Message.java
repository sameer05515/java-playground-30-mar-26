package com.example.mongoplayground1.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cgptmessages")
public class Message {

    @Id
    private String id;

    private String uniqueId;
    private String name;
    private List<Description> descriptions = new ArrayList<>();
    private String parentId;
    private List<String> tags = new ArrayList<>();
    private String author;
    private String linkedCGPTConvId;
    private String linkedCGPTFileId;
    private Integer order;
    private Instant createdDate;
    private Instant updatedDate;

    @Field("__v")
    private Integer version;

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLinkedCGPTConvId() {
        return linkedCGPTConvId;
    }

    public void setLinkedCGPTConvId(String linkedCGPTConvId) {
        this.linkedCGPTConvId = linkedCGPTConvId;
    }

    public String getLinkedCGPTFileId() {
        return linkedCGPTFileId;
    }

    public void setLinkedCGPTFileId(String linkedCGPTFileId) {
        this.linkedCGPTFileId = linkedCGPTFileId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public static class Description {
        private String content;
        private String textOutputType;
        private String textInputType;

        public Description() {
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTextOutputType() {
            return textOutputType;
        }

        public void setTextOutputType(String textOutputType) {
            this.textOutputType = textOutputType;
        }

        public String getTextInputType() {
            return textInputType;
        }

        public void setTextInputType(String textInputType) {
            this.textInputType = textInputType;
        }
    }
}
