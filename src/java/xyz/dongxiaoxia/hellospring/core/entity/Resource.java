package xyz.dongxiaoxia.hellospring.core.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/8.
 */
public class Resource {
    private String id;
    private String name;
    private String parentId;//父类Id
    private String parentName;
    private String resKey;//这个权限KEY是唯一的，新增时要注意
    private String resUrl;//URL地址。例如：/api/user/login ，不需要项目名和http://XXXX:8080
    private Integer level;
    private String type;//权限类型，0表示目录，1标识菜单，2表示按钮。。在spring security3安全权限中涉及精确到按钮控制
    private String description;
    private Set<Role> roles = new HashSet<>(0);
    private Set<Resource> childs = new HashSet<>(0);


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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Resource> getChilds() {
        return childs;
    }

    public void setChilds(Set<Resource> childs) {
        this.childs = childs;
    }
}
