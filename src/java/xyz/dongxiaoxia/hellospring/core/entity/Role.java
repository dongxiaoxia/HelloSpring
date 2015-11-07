package xyz.dongxiaoxia.hellospring.core.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public class Role {
    private String id;
    private String name;
    private List<User> users;
    private List<Group> groups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
