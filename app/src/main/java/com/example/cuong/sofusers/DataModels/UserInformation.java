package com.example.cuong.sofusers.DataModels;

public class UserInformation {
    private String userName;
    private String userAvatar;
    private String reputaion;
    private String location;
    private String userAge;
    private String userId;
    private boolean iChecked;

    public UserInformation(String userName, String userAvatar, String reputaion, String location, String userId) {
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.reputaion = reputaion;
        this.location = location;
        this.userId = userId;
        this.iChecked=false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getReputaion() {
        return reputaion;
    }

    public void setReputaion(String reputaion) {
        this.reputaion = reputaion;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isiChecked() {
        return iChecked;
    }

    public void setiChecked(boolean iChecked) {
        this.iChecked = iChecked;
    }
}
