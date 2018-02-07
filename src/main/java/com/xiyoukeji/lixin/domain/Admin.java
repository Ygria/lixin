package com.xiyoukeji.lixin.domain;

import com.xiyoukeji.lixin.type.AdminRoleEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ygria on 2017/11/20.
 */

@Entity
@Table(name="admin")
@DynamicUpdate
@DynamicInsert
public class Admin {

    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Long create_time = System.currentTimeMillis();
    private String username;
    private String password;
    private AdminRoleEnum admin_role;

    private Boolean disabled = false;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public AdminRoleEnum getAdmin_role() {
        return admin_role;
    }

    public void setAdmin_role(AdminRoleEnum admin_role) {
        this.admin_role = admin_role;
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
}
