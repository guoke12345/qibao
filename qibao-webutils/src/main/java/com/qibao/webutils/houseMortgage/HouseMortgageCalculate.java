package com.qibao.webutils.houseMortgage;


//房贷计算器


import sun.util.resources.cldr.nl.CalendarData_nl_NL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 等额本息法
 */
public class HouseMortgageCalculate {
    /**
     * 本金
     */
    private BigDecimal principal;
    /**
     * 年利率
     */
    private BigDecimal interestRateYear;
    /**
     * 还款月数
     */
    private int        repaymentTimeMonth;
    /**
     * 月利率
     */
    private BigDecimal interestRateMonth;
    /**
     * 贷款时间
     */
    private Date date;

    /**
     * 必需实力化放可使用。
     *
     * @param principal
     * @param interestRateYear
     * @param repaymentTimeMonth
     */
    public HouseMortgageCalculate(Double principal, Double interestRateYear, int repaymentTimeMonth,Date date) {
        this.principal = new BigDecimal(principal);
        this.interestRateYear = new BigDecimal(interestRateYear);
        this.repaymentTimeMonth = repaymentTimeMonth;
        this.interestRateMonth = this.interestRateYear.divide(new BigDecimal(12), 8, BigDecimal.ROUND_HALF_UP);
        this.date = date;
    }

    /**
     * 月供额
     * 〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     */
    public BigDecimal moneyByMonth() {
        BigDecimal temp        = coreInterest();
        BigDecimal numerator   = principal.multiply(interestRateMonth).multiply(temp);
        BigDecimal denominator = temp.subtract(BigDecimal.ONE);
        return numerator.divide(denominator, 4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 月应还本金
     * 贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕总利息=还款月数×每月月供额-贷款本金
     *
     * @return
     */
    public BigDecimal principalByMonth(int monthOrderNumber) {
        BigDecimal temp        = coreInterest();
        BigDecimal temp_1      = coreInterest(monthOrderNumber);
        BigDecimal numerator   = principal.multiply(interestRateMonth).multiply(temp_1);
        BigDecimal denominator = temp.subtract(BigDecimal.ONE);
        return numerator.divide(denominator, 4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 月应还利息
     *
     * @param monthOrderNumber
     * @return
     */
    public BigDecimal interestByMonth(int monthOrderNumber) {
        return moneyByMonth().subtract(principalByMonth(monthOrderNumber));
    }

    /**
     * 总还款额
     *
     * @return
     */
    public BigDecimal getAllMoney() {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i <= repaymentTimeMonth; i++) {
            sum = sum.add(moneyByMonth());
        }
        return sum;
    }

    /**
     * 月还款信息
     */
    private HousingLoanDto repaymentMonth(int monthOrderNumber) {
        HousingLoanDto housingLoanDto = new HousingLoanDto();
        //还款总金额
        housingLoanDto.setRepayment(moneyByMonth().doubleValue());
        //还款本金
        housingLoanDto.setRepaymentPrincipal(principalByMonth(monthOrderNumber).doubleValue());
        //还款利息
        housingLoanDto.setRepaymentInterest(interestByMonth(monthOrderNumber).doubleValue());
        //第几个月数
        housingLoanDto.setMonths(monthOrderNumber);
        // 设置还款日期
        if (this.date != null){
            housingLoanDto.setData(getRepaymentDate(monthOrderNumber));
        }
        return housingLoanDto;
    }

    /**
     * 获取还款日期
     * @param monthOrderNumber 第几个还款月
     * @return 还款日期
     */
    private Date getRepaymentDate(int monthOrderNumber){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,monthOrderNumber - 1);
        return calendar.getTime();
    }

    /**
     * 还款列表  月
     *
     * @return
     */
    public List<HousingLoanDto> repaymentMonthList() {
        List<HousingLoanDto> housingLoanDtoList = new ArrayList<HousingLoanDto>();
        for (int i = 1; i <= repaymentTimeMonth; i++) {
            housingLoanDtoList.add(repaymentMonth(i));
        }
        return housingLoanDtoList;
    }

    /**
     * 获取本月还款信息
     * @return
     */
    public HousingLoanDto thisRepaymentMonth(){
        if (this.date == null){
            //首次还款时间不能为空
            return null;
        }
        Calendar now = Calendar.getInstance();
        //第一个还款月
        Calendar  firstRepayDate = Calendar.getInstance();
        now.setTime(new Date());
        firstRepayDate.setTime(date);
        // 现在时间与首次还款月相差月数
        Integer months= now.get(Calendar.MONTH) - firstRepayDate.get(Calendar.MONTH);
        Integer years= now.get(Calendar.YEAR) - firstRepayDate.get(Calendar.YEAR);
        if (years<=0 && months < 0){
            throw new RuntimeException("时间设置异常");
        }
        months = years*12+months;
        return repaymentMonth(months);
    }



    /**
     * （1+月利率）^还款月数
     *
     * @return
     */
    private BigDecimal coreInterest() {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 0; i < repaymentTimeMonth; i++) {
            result = result.multiply(BigDecimal.ONE.add(interestRateMonth));
        }
        return result;
    }

    /**
     * （1+月利率）^（还款月序号-1）
     *
     * @return
     */
    private BigDecimal coreInterest(int monthOrderNumber) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 0; i < monthOrderNumber - 1; i++) {
            result = result.multiply(BigDecimal.ONE.add(interestRateMonth));
        }
        return result;
    }

}
