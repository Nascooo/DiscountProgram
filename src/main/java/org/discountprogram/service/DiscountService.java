package org.discountprogram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.discountprogram.entity.User;
import org.discountprogram.model.Bill;
import org.discountprogram.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiscountService {

    private final UserRepository userRepository;
    private final StrategyConsumerService strategyConsumerService;

    public double calculateAfterDiscount(Bill bill) {
        // getUser and get the type of it
        Long userId = bill.getUserId();
        Optional<User> optionalUser = userRepository.getUserByUserId(userId);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("Not Valid UserId"));
        return strategyConsumerService.applyStrategyAndCalculate(user, bill);
    }
}
