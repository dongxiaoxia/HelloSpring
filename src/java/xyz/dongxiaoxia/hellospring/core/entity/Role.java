package xyz.dongxiaoxia.hellospring.core.entity;

import xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation.Column;
import xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation.Entity;
import xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/8.
 */
@Entity("SYSTEM_ROLE")
public class Role {
    @Column
    @Id
    private String id;
    @Column
    private int enable;//是否禁用角色　1　表示禁用　2　表示不禁用
    @Column
    private String name;
    @Column
    private String roleKey;
    @Column
    private String description;
    private Set<Resource> resources = new HashSet<>(0);

    public Role() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
