package com.example.cuong.sofusers.DataModels;

public class UserReputation {

    private String reputationType;
    private String reputationChange;
    private String createdAt;
    private String postId;

    public UserReputation(String reputationType, String reputationChange, String createdAt, String postId) {
        this.reputationType = reputationType;
        this.reputationChange = reputationChange;
        this.createdAt = createdAt;
        this.postId = postId;
    }

    public String getReputationType() {
        return reputationType;
    }

    public void setReputationType(String reputationType) {
        this.reputationType = reputationType;
    }

    public String getReputationChange() {
        return reputationChange;
    }

    public void setReputationChange(String reputationChange) {
        this.reputationChange = reputationChange;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
