package org.discountprogram.service;


import org.discountprogram.entity.User;
import org.discountprogram.model.Bill;
import org.discountprogram.constant.UserType;
import org.discountprogram.strategy.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class StrategyConsumerService {

    Map<UserType, DiscountStrategy> map = new HashMap<>();

    public StrategyConsumerService(Set<DiscountStrategy> strategies) {
        for (DiscountStrategy strategy : strategies) {
            map.put(strategy.getStrategyName(), strategy);
        }
    }

    public double applyStrategyAndCalculate(User user, Bill bill) {
        return map.get(user.getUserType()).applyDiscount(bill);
    }

}
