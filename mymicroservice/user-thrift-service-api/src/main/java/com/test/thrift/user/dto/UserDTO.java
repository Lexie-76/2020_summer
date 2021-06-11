package com.test.thrift.user.dto;

public class UserDTO {
    private int id;
    private String username;
    private String realName;
    private String mobile;
    private String email;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String realName, String mobile, String email) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.mobile = mobile;
        this.email = email;
    }

    public UserDTO(String username, String realName, String mobile, String email) {
        this.username = username;
        this.realName = realName;
        this.mobile = mobile;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
