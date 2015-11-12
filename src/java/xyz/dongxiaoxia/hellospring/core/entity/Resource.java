package xyz.dongxiaoxia.hellospring.core.entity;

/**
 * Created by Administrator on 2015/11/8.
 */
public class Resource {
    private String id;
    private String name;
    private String type;
    private String res_string;
    private String priority;
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRes_string() {
        return res_string;
    }

    public void setRes_string(String res_string) {
        this.res_string = res_string;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
}
