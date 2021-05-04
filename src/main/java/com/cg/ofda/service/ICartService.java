package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.FoodCartModel;

public interface ICartService {

//	/* Definition of addItem method for adding items to FoodCart */
//	public FoodCartModel addItemToCart(FoodCartModel cart, ItemModel item) throws OFDAException;
//
//	/*
//	 * Definition of increaseQuantity method for increasing quantity of items in
//	 * FoodCart
//	 */
//	public FoodCartModel increaseQuantity(FoodCartModel cart, ItemModel item, int quantity) throws OFDAException;
//
//	/*
//	 * Definition of reduceQuantity method for reducing quantity of items in
//	 * FoodCart
//	 */
//	public FoodCartModel reduceQuantity(FoodCartModel cart, ItemModel item, int quantity) throws OFDAException;

	
	
	/* Definition of addCart for adding cart*/
	public FoodCartModel addCart(FoodCartModel cart) throws OFDAException;
	
	
	/* Definition of updateCart for updating cart */
	public FoodCartModel updateCart(FoodCartModel cart) throws OFDAException;
	

	
	/* Definition of removeItem method for removing items from FoodCart */
	public boolean removeCart(Long cartId) throws OFDAException;
	
	
	/*Definition of view cart method for viewing a cart using cartId */
	public FoodCartModel viewCart(Long cartId) throws OFDAException;
	
	/*Definition of cart method for viewing all carts */
	public List<FoodCartModel> viewAllCarts() throws OFDAException;

	/* Definition of clearCart method for clearing FoodCart */
	public FoodCartModel clearCart(FoodCartModel cart) throws OFDAException;
	
	
	

}