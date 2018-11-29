package in.aprilfish.mapper;

import java.text.MessageFormat;

public class UserOrder {
    private Integer id;

    private Integer uid;

    private String name;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return MessageFormat.format("'{'\"id\":{0},\"uid\":{1},\"name\":\"{2}\"'}'",this.getId(),this.getUid(),this.getName());
    }
}