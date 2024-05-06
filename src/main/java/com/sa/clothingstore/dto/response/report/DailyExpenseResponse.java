package com.sa.clothingstore.dto.response.report;

import java.math.BigDecimal;
import java.util.Date;

public class DailyExpenseResponse {
    private Date date;
    private long totalCustomers;
    private long totalOrders;
    private long totalProductsSold;
    private BigDecimal totalRevenue;
}
