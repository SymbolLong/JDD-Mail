package com.zhang.entity;

import com.zhang.util.StringUtil;

import java.math.BigDecimal;

/**
 * Created by 致远 on 2017/6/13 0013.
 */
public class Person {

    private String name;//姓名
    private String email;//邮箱
    private String part;//部门
    private String position;//职位
    private String baseSalary;//基本工资
    private String actualBaseSalary;//实际基本工资
    private String workDays;//出勤天数
    private String preMonthBonus;//上月提成
    private String teamBonus;//团队提成
    private String assessment;//考核
    private String socialInsurance;//社保
    private String houseFund;//公积金
    private String totalTaxSalary;//应纳税总额
    private String tax;//个税
    private String socialInsuranceFund;//社保补贴
    private String otherFund;//其它补贴
    private String late;//迟到|缺卡
    private String leave;//请假|扣款
    private String preMonthFund;//补上月工资
    private String notFullWork;//未全勤
    private String salary;//工资总额

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(String baseSalary) {
        this.baseSalary = format(baseSalary);
    }

    public String getActualBaseSalary() {
        return actualBaseSalary;
    }

    public void setActualBaseSalary(String actualBaseSalary) {
        this.actualBaseSalary = format(actualBaseSalary);
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getPreMonthBonus() {
        return preMonthBonus;
    }

    public void setPreMonthBonus(String preMonthBonus) {
        this.preMonthBonus = format(preMonthBonus);
    }

    public String getTeamBonus() {
        return teamBonus;
    }

    public void setTeamBonus(String teamBonus) {
        this.teamBonus = format(teamBonus);
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = format(assessment);
    }

    public String getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(String socialInsurance) {
        this.socialInsurance = format(socialInsurance);
    }

    public String getHouseFund() {
        return houseFund;
    }

    public void setHouseFund(String houseFund) {
        this.houseFund = format(houseFund);
    }

    public String getTotalTaxSalary() {
        return totalTaxSalary;
    }

    public void setTotalTaxSalary(String totalTaxSalary) {
        this.totalTaxSalary = format(totalTaxSalary);
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = format(tax);
    }

    public String getSocialInsuranceFund() {
        return socialInsuranceFund;
    }

    public void setSocialInsuranceFund(String socialInsuranceFund) {
        this.socialInsuranceFund = format(socialInsuranceFund);
    }

    public String getOtherFund() {
        return otherFund;
    }

    public void setOtherFund(String otherFund) {
        this.otherFund = format(otherFund);
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = format(late);
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = format(leave);
    }

    public String getPreMonthFund() {
        return preMonthFund;
    }

    public void setPreMonthFund(String preMonthFund) {
        this.preMonthFund = format(preMonthFund);
    }

    public String getNotFullWork() {
        return notFullWork;
    }

    public void setNotFullWork(String notFullWork) {
        this.notFullWork = format(notFullWork);
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = format(salary);
    }

    private String format(String value) {
        if (StringUtil.isEmpty(value)) {
            return "";
        }
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);  //保留两位，四舍五入,
        return StringUtil.toString(bigDecimal);
    }
}
