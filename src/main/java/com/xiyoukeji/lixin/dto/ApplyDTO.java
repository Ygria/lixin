package com.xiyoukeji.lixin.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ygria on 2018/2/5.
 */
public class ApplyDTO {

    private Long id;
    //公司名称
    @NotBlank
    private String company_name;
    //借款用途
    @NotBlank
    private String usage_of_loan;
    //借款开始时间
    @NotNull
    private Long start_time;
    //借款结束时间
    @NotNull
    private Long end_time;
    @NotBlank
    private String wechat;
    @NotNull
    private Long apply_time;
    @NotNull
    private Long money;

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
}
