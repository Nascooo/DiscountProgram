package org.discountprogram.service;

import org.discountprogram.entity.User;
import org.discountprogram.model.Bill;
import org.discountprogram.repo.UserRepository;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.generators.Generators;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DataJpaTest
class DiscountServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StrategyConsumerService strategyConsumerService;

    @InjectMocks
    private DiscountService discountService;

    Model<User> userModel = Instancio.of(User.class).generate(field(User::getUserId), Generators::longSeq).toModel();


    @Test
    void whenCalculateAfterDiscount_thenReturnDiscountedValue() {
        User user = Instancio.create(userModel);
        Bill bill = Instancio.create(Bill.class);
        Long userId = user.getUserId();
        bill.setUserId(userId);
        when(userRepository.getUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
        when(strategyConsumerService.applyStrategyAndCalculate(user, bill)).thenReturn(200.0);
        double netAmount = discountService.calculateAfterDiscount(bill);
        assertEquals(200, netAmount);
    }

    @Test
    void whenCalculateAfterDiscount_throwUserNotFound() {
        User user = Instancio.create(userModel);
        Bill bill = Instancio.create(Bill.class);
        Long userId = user.getUserId();
        bill.setUserId(0L);
        assertThrows(IllegalArgumentException.class, () -> {
            double netAmount = discountService.calculateAfterDiscount(bill);
        });
    }
}