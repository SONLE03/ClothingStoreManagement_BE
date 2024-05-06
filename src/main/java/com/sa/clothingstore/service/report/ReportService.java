package com.sa.clothingstore.service.report;

import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.request.report.MonthlyRequest;
import com.sa.clothingstore.dto.response.report.*;

import java.util.List;

public interface ReportService {
    List<DailyRevenueResponse> getDailyRevenue(DailyRequest date);
    List<DailyExpenseResponse> getDailyExpense(DailyRequest date);

    List<MonthlyRevenueResponse> getMonthlyRevenue(MonthlyRequest year);
    List<MonthlyExpenseResponse> getMonthlyExpense(MonthlyRequest year);
    List<YearlyRevenueResponse> getYearlyRevenue();
    List<YearlyExpenseResponse> getYearlyExpense();

}
