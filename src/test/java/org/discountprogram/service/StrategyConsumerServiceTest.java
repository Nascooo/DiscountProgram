package org.discountprogram.service;

import org.discountprogram.constant.ItemCategory;
import org.discountprogram.constant.UserType;
import org.discountprogram.entity.User;
import org.discountprogram.model.Bill;
import org.discountprogram.model.Item;
import org.discountprogram.strategy.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StrategyConsumerServiceTest {

    private static StrategyConsumerService strategyConsumerService;

    @BeforeEach
    void setUp() {
        // Create a set of mock strategies
        Set<DiscountStrategy> strategies = new HashSet<>();
        EmployeeDiscountStrategy employeeDiscountStrategy = new EmployeeDiscountStrategy();
        AffiliateDiscountStrategy affiliateDiscountStrategy = new AffiliateDiscountStrategy();
        CustomerDiscountStrategy customerDiscountStrategy = new CustomerDiscountStrategy();
        LoyalCustomerDiscountStrategy loyalCustomerDiscountStrategy = new LoyalCustomerDiscountStrategy();
        strategies.add(customerDiscountStrategy);
        strategies.add(employeeDiscountStrategy);
        strategies.add(affiliateDiscountStrategy);
        strategies.add(loyalCustomerDiscountStrategy);

        // Initialize the service with mock strategies
        strategyConsumerService = new StrategyConsumerService(strategies);

        assertEquals(employeeDiscountStrategy.getStrategyName(), UserType.EMPLOYEE);
        assertEquals(affiliateDiscountStrategy.getStrategyName(), UserType.AFFILIATE);
        assertEquals(loyalCustomerDiscountStrategy.getStrategyName(), UserType.LOYAL_CUSTOMER);
        assertEquals(customerDiscountStrategy.getStrategyName(), UserType.CUSTOMER);
    }

    @Test
    void testApplyStrategyAndCalculate_AllStrategies() {
        //Unit Test for Making sure all User Types matching Discount Strategies
        List<User> users = Instancio.ofList(User.class).size(10).create();
        for (User user : users) {
            DiscountStrategy discountStrategy = strategyConsumerService.getMap().get(user.getUserType());
            assertEquals(user.getUserType(), discountStrategy.getStrategyName());
        }
    }

    @Test
    void testApplyStrategyAndCalculate_customerDiscount() {
        //Where No Discount Applied
        User user = Instancio.create(User.class);
        user.setUserType(UserType.CUSTOMER);
        Bill bill = new Bill();
        bill.setUserId(user.getUserId());
        Item item1 = new Item();
        item1.setPrice(100);
        item1.setCategory(ItemCategory.NON_GROCERIES);
        Item item2 = new Item();
        item2.setPrice(100);
        item2.setCategory(ItemCategory.GROCERIES);
        bill.setItems(List.of(item1, item2));
        double value = strategyConsumerService.applyStrategyAndCalculate(user, bill);
        assertEquals(value, 200);
    }

    @Test
    void testApplyStrategyAndCalculate_EmployeeDiscount() {
        User user = Instancio.create(User.class);
        user.setUserType(UserType.EMPLOYEE);
        Bill bill = new Bill();
        bill.setUserId(user.getUserId());
        Item item1 = new Item();
        item1.setPrice(100);
        item1.setCategory(ItemCategory.NON_GROCERIES);
        Item item2 = new Item();
        item2.setPrice(100);
        item2.setCategory(ItemCategory.GROCERIES);
        bill.setItems(List.of(item1, item2));
        double value = strategyConsumerService.applyStrategyAndCalculate(user, bill);
        assertEquals(value, 170);
    }

    @Test
    void testApplyStrategyAndCalculate_LoyalCustomerDiscount() {
        User user = Instancio.create(User.class);
        user.setUserType(UserType.LOYAL_CUSTOMER);
        Bill bill = new Bill();
        bill.setUserId(user.getUserId());
        Item item1 = new Item();
        item1.setPrice(100);
        item1.setCategory(ItemCategory.NON_GROCERIES);
        Item item2 = new Item();
        item2.setPrice(100);
        item2.setCategory(ItemCategory.GROCERIES);
        bill.setItems(List.of(item1, item2));
        double value = strategyConsumerService.applyStrategyAndCalculate(user, bill);
        assertEquals(value, 195);
    }

    @Test
    void testApplyStrategyAndCalculate_AffileateDiscount() {
        User user = Instancio.create(User.class);
        user.setUserType(UserType.AFFILIATE);
        Bill bill = new Bill();
        bill.setUserId(user.getUserId());
        Item item1 = new Item();
        item1.setPrice(100);
        item1.setCategory(ItemCategory.NON_GROCERIES);
        Item item2 = new Item();
        item2.setPrice(100);
        item2.setCategory(ItemCategory.GROCERIES);
        bill.setItems(List.of(item1, item2));
        double value = strategyConsumerService.applyStrategyAndCalculate(user, bill);
        assertEquals(value, 190);
    }

    @Test
    void testApplyStrategyAndCalculate_LoyalCustomer_applyExtraFor100() {
        User user = Instancio.create(User.class);
        user.setUserType(UserType.LOYAL_CUSTOMER);
        Bill bill = new Bill();
        bill.setUserId(user.getUserId());
        Item item1 = new Item();
        item1.setPrice(495);
        item1.setCategory(ItemCategory.NON_GROCERIES);
        Item item2 = new Item();
        item2.setPrice(495);
        item2.setCategory(ItemCategory.NON_GROCERIES);
        bill.setItems(List.of(item1, item2));
        double value = strategyConsumerService.applyStrategyAndCalculate(user, bill);
        assertEquals(945, value);
    }


}