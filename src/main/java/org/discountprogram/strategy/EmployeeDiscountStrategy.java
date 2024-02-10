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
public class EmployeeDiscountStrategy implements DiscountStrategy {

    private final String DISCOUNT_PRECENTAGE = "30";

    @Override
    public double applyDiscount(Bill bill) {
        List<Item> discountedItems = bill.getItems().stream().filter(el -> ItemCategory.NON_GROCERIES.equals(el.getCategory())).toList();
        List<Item> unDiscountedItems = bill.getItems().stream().filter(el -> ItemCategory.GROCERIES.equals(el.getCategory())).toList();

        BigDecimal total = new BigDecimal(0);
        for (Item item : discountedItems) {
            total = total.add(BigDecimal.valueOf(item.getPrice()));
        }
        BigDecimal totalDiscount = calculatePercentage(total, new BigDecimal(DISCOUNT_PRECENTAGE));
        //Subtract Discount from Total
        total = total.subtract(totalDiscount);

        for (Item item : unDiscountedItems) {
            total = total.add(BigDecimal.valueOf(item.getPrice()));
        }

        return total.setScale(2, RoundingMode.UP).doubleValue();
    }

    @Override
    public UserType getStrategyName() {
        return UserType.EMPLOYEE;
    }
}
