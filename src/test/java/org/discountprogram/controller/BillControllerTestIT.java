package org.discountprogram.controller;

import org.discountprogram.model.Bill;
import org.discountprogram.repo.UserRepository;
import org.discountprogram.service.DiscountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BillController.class)
class BillControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountService discountService;
    @MockBean
    private UserRepository userRepository;


    @Test
    void calculateNetAmount_ReturnsNetAmount() throws Exception {
        // Given
        given(discountService.calculateAfterDiscount(any(Bill.class))).willReturn(280.0);

        // When & Then
        mockMvc.perform(post("/discount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                            "userId": 1,
                                            "items": [
                                                {
                                                    "itemId": 1,
                                                    "name": "RAYL",
                                                    "category": "GROCERIES",
                                                    "price": 20
                                                },
                                                {
                                                    "itemId": 2,
                                                    "name": "XESDESPL",
                                                    "category": "GROCERIES",
                                                    "price": 30
                                                },
                                                {
                                                    "itemId": 3,
                                                    "name": "KXA",
                                                    "category": "NON_GROCERIES",
                                                    "price": 300
                                                },
                                                {
                                                    "itemId": 4,
                                                    "name": "UKLMVXLV",
                                                    "category": "NON_GROCERIES",
                                                    "price": 300
                                                }
                                            ]
                                        }
                                        """
                                ))
                .andExpect(status().isOk())
                .andExpect(content().string("280.0"));
    }
}