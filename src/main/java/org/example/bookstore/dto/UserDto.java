package org.example.bookstore.dto;

public class UserDto {
    private Integer id;
    private String username;

    private Integer age;
    private String contact;
    private String introduction;
    private String avatar;
    private Boolean type;

    public UserDto () {
    }

    public UserDto (Integer id, String username, Integer age, String contact, String introduction, String avatar, Boolean type) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.contact = contact;
        this.introduction = introduction;
        this.avatar = avatar;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", introduction='" + introduction + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                '}';
    }
}
