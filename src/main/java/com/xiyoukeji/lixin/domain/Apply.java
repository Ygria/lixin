package com.xiyoukeji.lixin.domain;

import com.xiyoukeji.lixin.type.ApplyStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ygria on 2018/2/5.
 * 利信金融的申请
 */
@Entity
@Table(name="apply")
public class Apply {

    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    //公司名称
    private String company_name;
    //借款用途
    private String usage_of_loan;
    //借款开始时间
    private Long start_time;
    //借款结束时间
    private Long end_time;
   private String wechat;

    private Long apply_time;
    private Long money;
    private ApplyStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUsage_of_loan() {
        return usage_of_loan;
    }

    public void setUsage_of_loan(String usage_of_loan) {
        this.usage_of_loan = usage_of_loan;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Long getApply_time() {
        return apply_time;
    }

    public void setApply_time(Long apply_time) {
        this.apply_time = apply_time;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public ApplyStatus getStatus() {
        return status;
    }

    public void setStatus(ApplyStatus status) {
        this.status = status;
    }
}
