package com.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.dao.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
