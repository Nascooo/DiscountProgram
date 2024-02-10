package org.discountprogram.strategy;

import org.discountprogram.model.Bill;
import org.discountprogram.constant.UserType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface DiscountStrategy {

    double applyDiscount(Bill bill);

    UserType getStrategyName();

    default BigDecimal calculatePercentage(BigDecimal amount, BigDecimal percentage) {
        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal fraction = percentage.divide(oneHundred);
        return amount.multiply(fraction).setScale(2, RoundingMode.UP);
    }

    /**
     *     here i can Make a Generic Function and pass the Percentage and calculate the Whole Bill but want to
     *     make clear that every one could have it's own different implementation
     */
}
