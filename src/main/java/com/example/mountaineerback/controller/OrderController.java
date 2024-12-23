package com.example.mountaineerback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /orders
 * ----------------------------------
 * GET   /         查詢該使用者所有商品(多筆)
 * POST  /checkout 該使用者進行結帳
 * */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {


}
