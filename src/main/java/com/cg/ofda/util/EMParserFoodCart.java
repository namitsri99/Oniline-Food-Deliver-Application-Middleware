package com.cg.ofda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.FoodCartEntity;
import com.cg.ofda.model.FoodCartModel;
import com.cg.ofda.repository.ICartRepository;

@Service
public class EMParserFoodCart {
	
	@Autowired
	private ICartRepository cartRepo;
	
	@Autowired
	private EMParserCustomer customerParser;
	
	public EMParserFoodCart() {
		this.customerParser= new EMParserCustomer();
	}
	
	
	
	
	public EMParserFoodCart(ICartRepository cartRepo) {
		super();
		this.cartRepo = cartRepo;
		this.customerParser= new EMParserCustomer();
	}




	public FoodCartModel parse(FoodCartEntity source) {
		return source==null ? null:
			new FoodCartModel (source.getCartId(),
					customerParser.parse(source.getCustomer()));
	}
	
	public FoodCartEntity parse(FoodCartModel source) {
		return source==null ? null:
			new FoodCartEntity (source.getCartId(),
					customerParser.parse(source.getCustomer()));
	}

}
