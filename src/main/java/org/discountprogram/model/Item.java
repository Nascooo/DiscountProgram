package org.discountprogram.model;

import lombok.Data;
import org.discountprogram.constant.ItemCategory;

@Data
public class Item {
    private String name;
    private ItemCategory category;
    private double price;

}
