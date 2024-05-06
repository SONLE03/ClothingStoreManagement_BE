package com.sa.clothingstore.service.report;

import com.sa.clothingstore.dto.request.report.DailyRequest;
import com.sa.clothingstore.dto.request.report.MonthlyRequest;
import com.sa.clothingstore.dto.response.report.*;
import com.sa.clothingstore.repository.importInvoice.ImportItemRepository;
import com.sa.clothingstore.repository.order.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImp implements ReportService{
    private final OrderItemRepository orderRepository;
    private final ImportItemRepository importItemRepository;

    @Override
    public List<DailyRevenueResponse> getDailyRevenue(DailyRequest date) {
        return orderRepository.getDailyRevenue(date.getStartDate(), date.getEndDate());
    }

    @Override
    public List<DailyExpenseResponse> getDailyExpense(DailyRequest date) {
        return importItemRepository.getDailyExpense(date.getStartDate(), date.getEndDate());
    }

    @Override
    public List<MonthlyRevenueResponse> getMonthlyRevenue(MonthlyRequest year) {
        return orderRepository.getMonthlyRevenue(year.getYear());
    }

    @Override
    public List<YearlyRevenueResponse> getYearlyRevenue() {
        return orderRepository.getYearlyRevenue();
    }

    @Override
    public List<MonthlyExpenseResponse> getMonthlyExpense(MonthlyRequest year) {
        return importItemRepository.getMonthlyExpense(year.getYear());
    }

    @Override
    public List<YearlyExpenseResponse> getYearlyExpense() {
        return importItemRepository.getYearlyExpense();
    }
}
