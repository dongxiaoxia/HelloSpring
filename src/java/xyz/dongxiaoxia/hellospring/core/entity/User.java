package xyz.dongxiaoxia.hellospring.core.entity;

import xyz.dongxiaoxia.hellospring.util.annotation.Column;
import xyz.dongxiaoxia.hellospring.util.annotation.Entity;
import xyz.dongxiaoxia.hellospring.util.annotation.Id;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenwendong on 2015/10/29.
 */
@Entity("SYSTEM_USER")
public class User {
    @Column("id")
    @Id
    private String id;
    @Column("username")
    private String username;
    @Column("password")
    private String password;
    @Column("nickname")
    private String nickname;
    @Column("realname")
    private String realname;
    @Column("age")
    private int age;
    @Column("sex")
    private int sex;
    @Column("email")
    private String email;
    @Column("regTime")
    private Timestamp regTime;
    @Column("lastLoginTime")
    private Timestamp lastLoginTime;
    @Column("level")
    private int level;
    @Column("accountType")
    private String accountType;
    @Column("status")
    private String status;
    private Set<Role> roles = new HashSet<>(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
