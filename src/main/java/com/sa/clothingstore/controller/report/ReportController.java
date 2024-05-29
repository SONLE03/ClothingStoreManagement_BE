package com.sa.clothingstore.controller.report;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.request.report.MonthlyRequest;
import com.sa.clothingstore.dto.request.report.YearlyRequest;
import com.sa.clothingstore.dto.response.report.*;
import com.sa.clothingstore.service.report.ReportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(APIConstant.REPORTS)
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @GetMapping(APIConstant.USER_DAILY_REVENUE)
    @ResponseStatus(HttpStatus.OK)
    public List<DailyRevenueResponse> getDailyRevenueByUser(@PathVariable UUID userId, @RequestBody Date startDate){
        return reportService.getDailyRevenueByUser(userId, startDate);
    }
    @GetMapping(APIConstant.USER_DAILY_EXPENSE)
    @ResponseStatus(HttpStatus.OK)
    public List<DailyExpenseResponse> getDailyExpenseByUser(@PathVariable UUID userId, @RequestBody Date startDate ){
        return reportService.getDailyExpenseByUser(userId, startDate);
    }

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
    public List<YearlyRevenueResponse> getYearlyRevenue(@RequestBody @Valid YearlyRequest yearlyRequest){
        return reportService.getYearlyRevenue(yearlyRequest);
    }
    @GetMapping(APIConstant.YEARLY_EXPENSE)
    @ResponseStatus(HttpStatus.OK)
    public List<YearlyExpenseResponse> getYearlyExpense(@RequestBody @Valid YearlyRequest yearlyRequest){
        return reportService.getYearlyExpense(yearlyRequest);
    }
}
