package com.cg.ofda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.OrderDetailsEntity;
import com.cg.ofda.model.OrderDetailsModel;
import com.cg.ofda.repository.IOrderRepository;

@Service
public class EMParserOrderDetails {
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private EMParserFoodCart cartParser;
	
	public EMParserOrderDetails() {
		this.cartParser= new EMParserFoodCart();
	}
	
	
	
	
	
	public EMParserOrderDetails(IOrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
		this.cartParser= new EMParserFoodCart();
	}



	public OrderDetailsModel parse(OrderDetailsEntity source) {
		return source == null ? null:
			new OrderDetailsModel(source.getOrderId(),
					source.getOrderDate(),
					cartParser.parse(source.getCart()),
					source.getOrderStatus());
	}
	
	public OrderDetailsEntity parse(OrderDetailsModel  source) {
		return source == null ? null:
			new OrderDetailsEntity(source.getOrderId(),
					source.getOrderDate(),
					cartParser.parse(source.getCart()),
					source.getOrderStatus());
	}
	

}
