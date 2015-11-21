package xyz.dongxiaoxia.hellospring.core.entity;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/18.
 * <p/>
 * 登陆信息实体类
 */
public class UserLoginList {
    private String loginId;
    private String userId;
    private String userName;
    private Date loginTime;
    private String loginIp;

    public UserLoginList() {
    }

    public UserLoginList(String loginId, String userId, Date loginTime, String loginIp, String userName) {
        this.loginId = loginId;
        this.userId = userId;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
