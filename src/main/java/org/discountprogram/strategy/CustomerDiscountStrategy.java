package org.discountprogram.strategy;

import org.discountprogram.model.Bill;
import org.discountprogram.model.Item;
import org.discountprogram.constant.ItemCategory;
import org.discountprogram.constant.UserType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CustomerDiscountStrategy implements DiscountStrategy {


    private final BigDecimal discountNumber = new BigDecimal(5);

    @Override
    public double applyDiscount(Bill bill) {
        BigDecimal total = new BigDecimal(0);
        for (Item item : bill.getItems()) {
            total = total.add(BigDecimal.valueOf(item.getPrice()));
        }

        return total.setScale(2, RoundingMode.UP).doubleValue();
    }

    @Override
    public UserType getStrategyName() {
        return UserType.CUSTOMER;
    }
}
