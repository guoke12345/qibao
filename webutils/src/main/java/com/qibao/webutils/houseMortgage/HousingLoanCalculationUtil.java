package com.qibao.webutils.houseMortgage;

import org.springframework.stereotype.Service;

import java.util.List;

/**
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
    public List<HousingLoanDto> getAll(HousingLoanDto dto) {
        HouseMortgageCalculate util = new HouseMortgageCalculate(dto.getRepaymentPrincipal(),dto.getRepaymentRate(),dto.getRepaymentTime());
        return util.repaymentMonthList();
    }
}