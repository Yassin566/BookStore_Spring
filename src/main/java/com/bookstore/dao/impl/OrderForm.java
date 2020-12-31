package com.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.dao.entity.User;

import lombok.Data;

@Data
public class OrderForm {
    private User user=new User();
    private List<OrderBook> books=new ArrayList<>();
}
@Data
class OrderBook{
    private Long id;
    private int quantity;

}
