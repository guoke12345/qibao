package com.qibao.webutils.houseMortgage;

import java.util.Date;
import java.util.List;

/**
 * 房贷计算器工具接口
 * Created by giozola on 2017/8/3.
 */
public interface IHousingLoanCalculationUtil {
    /**
     * 某月的还款信息
     * @return
     */
    HousingLoanDto getRepaymentByMonth(HousingLoanDto housingLoanDto);

    /**
     * 某年的还款信息
     */
    List<HousingLoanDto> getRepaymentByYear();

    /**
     * 所有的还款月信息
     * @param principal 本金
     * @param interestRateYear 年利率
     * @param repaymentTimeMonth 还款月数
     * @param date null 首次还款时间
     * @return
     */
    List<HousingLoanDto> getAll(Double principal, Double interestRateYear, int repaymentTimeMonth, Date date);

    /**
     * 获取本月还款信息
     * @param principal 本金
     * @param interestRateYear 年利率
     * @param repaymentTimeMonth 还款月数
     * @param date 首次还款时间 (不能为空)
     * @return
     */
    HousingLoanDto getThisMonth(Double principal, Double interestRateYear, int repaymentTimeMonth, Date date);
}
