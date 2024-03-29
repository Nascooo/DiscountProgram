package org.discountprogram.model;

import lombok.Data;

import java.util.List;

@Data
public class Bill {

    private List<Item> items;

    private Long userId;
}
