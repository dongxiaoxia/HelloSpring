package xyz.dongxiaoxia.hellospring.core.entity;

import xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation.Column;
import xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation.Entity;
import xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation.Id;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/7.
 */
@Entity("SYSTEM_LOG")
public class Log {
    @Id
    @Column
    private String id;
    @Column
    private String module;
    @Column
    private String description;
    @Column
    private String method;
    @Column
    private Long logType;
    @Column
    private String requestIp;
    @Column
    private String exceptionCode;
    @Column
    private String exceptionDetail;
    @Column
    private String params;
    @Column
    private String createBy;
    @Column
    private Date createDate;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getLogType() {
        return logType;
    }

    public void setLogType(Long logType) {
        this.logType = logType;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
