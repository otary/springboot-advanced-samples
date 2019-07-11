package cn.chenzw.springboot.rabbitmq.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class SysUserDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;

    private Date birth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "SysUserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }
}
