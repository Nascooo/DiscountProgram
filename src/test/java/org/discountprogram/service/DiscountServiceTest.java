package org.discountprogram.service;

import org.discountprogram.entity.User;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {

    @Test
    void calculateAfterDiscount_HappyCase() {
        User user = Instancio.create(User.class);
    }
}