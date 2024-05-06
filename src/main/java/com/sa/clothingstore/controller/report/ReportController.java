package com.sa.clothingstore.controller.report;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.request.report.MonthlyRequest;
import com.sa.clothingstore.dto.response.report.*;
import com.sa.clothingstore.service.report.ReportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.REPORTS)
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping(APIConstant.DAILY_REVENUE)
    @ResponseStatus(HttpStatus.OK)
    public List<DailyRevenueResponse> getDailyRevenue(@RequestBody @Valid DailyRequest request){
        return reportService.getDailyRevenue(request);
    }
    @GetMapping(APIConstant.DAILY_EXPENSE)
    @ResponseStatus(HttpStatus.OK)
    public List<DailyExpenseResponse> getDailyExpense(@RequestBody @Valid DailyRequest request){
        return reportService.getDailyExpense(request);
    }

    @GetMapping(APIConstant.MONTHLY_REVENUE)
    @ResponseStatus(HttpStatus.OK)
    public List<MonthlyRevenueResponse> getMonthlyRevenue(@RequestBody @Valid MonthlyRequest request){
        return reportService.getMonthlyRevenue(request);
    }
    @GetMapping(APIConstant.MONTHLY_EXPENSE)
    @ResponseStatus(HttpStatus.OK)
    public List<MonthlyExpenseResponse> getMonthlyExpense(@RequestBody @Valid MonthlyRequest request){
        return reportService.getMonthlyExpense(request);
    }

    @GetMapping(APIConstant.YEARLY_REVENUE)
    @ResponseStatus(HttpStatus.OK)
    public List<YearlyRevenueResponse> getYearlyRevenue(){
        return reportService.getYearlyRevenue();
    }
    @GetMapping(APIConstant.YEARLY_EXPENSE)
    @ResponseStatus(HttpStatus.OK)
    public List<YearlyExpenseResponse> getYearlyExpense(){
        return reportService.getYearlyExpense();
    }
}
