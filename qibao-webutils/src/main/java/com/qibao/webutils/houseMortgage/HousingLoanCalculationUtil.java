package com.qibao.webutils.houseMortgage;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 房贷计算器实现
 * Created by giozola on 2017/8/4.
 */
@Service
public class HousingLoanCalculationUtil implements IHousingLoanCalculationUtil{
    @Override
    public HousingLoanDto getRepaymentByMonth(HousingLoanDto housingLoanDto) {
        return null;
    }

    @Override
    public List<HousingLoanDto> getRepaymentByYear() {
        return null;
    }

    @Override
    public List<HousingLoanDto> getAll(Double principal, Double interestRateYear, int repaymentTimeMonth, Date date) {
        HouseMortgageCalculate util = new HouseMortgageCalculate(principal,  interestRateYear,repaymentTimeMonth,date);
        return util.repaymentMonthList();
    }

    @Override
    public HousingLoanDto getThisMonth(Double principal, Double interestRateYear, int repaymentTimeMonth, Date date) {
        HouseMortgageCalculate util = new HouseMortgageCalculate(principal,  interestRateYear,repaymentTimeMonth,date);
        return util.thisRepaymentMonth();
    }
}
