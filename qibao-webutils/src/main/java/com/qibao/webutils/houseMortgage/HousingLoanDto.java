package com.qibao.webutils.houseMortgage;

import lombok.Data;

import java.util.Date;

/**
 * Created by giozola on 2017/8/3.
 */
@Data
public class HousingLoanDto {
    /**
     * 日期，时间
     */
    private Date data;
    /**
     * 还款月数
     */
    private Integer months;
    /**
     * 还款金额
     */
    private Double repayment;
    /**
     * 还款 本金
     */
    private Double repaymentPrincipal;
    /**
     * 还款 利息
     */
    private Double repaymentInterest;

    /**
     * 还款年利率
     */
    private Double repaymentRate;

    /**
     * 贷款期限  月
     */
    private Integer repaymentTime;
}
