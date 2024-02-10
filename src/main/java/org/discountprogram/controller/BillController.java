package org.discountprogram.controller;

import lombok.RequiredArgsConstructor;
import org.discountprogram.model.Bill;
import org.discountprogram.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillController {

    private final DiscountService discountService;


    @PostMapping("/discount")
    public ResponseEntity<Double> calculateNetAmount(@RequestBody Bill bill) {
        double netAmount = discountService.calculateAfterDiscount(bill);
        return ResponseEntity.ok(netAmount);
    }
}
