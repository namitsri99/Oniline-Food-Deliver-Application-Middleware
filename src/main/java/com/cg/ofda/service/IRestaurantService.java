package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.RestaurantModel;

public interface IRestaurantService {

	/* Definition of addRestaurant method for adding restaurant */
	public RestaurantModel addRestaurant(RestaurantModel res) throws OFDAException;

	/* Definition of removeRestaurant method for removing restaurant */
	public boolean removeRestaurant(Long restaurantId) throws OFDAException;

	/* Definition of updateRestaurant method for updating restaurant */
	public RestaurantModel updateRestaurant(RestaurantModel res) throws OFDAException;

	/* Definition of viewRestaurant method for viewing restaurant */
	public RestaurantModel viewRestaurant(Long restaurantId) throws OFDAException;

	/* Definition of viewAllRestaurants method for viewing all restaurants */
	public List<RestaurantModel> viewAllRestaurants() throws OFDAException;

	/*
	 * Definition of viewAllRestaurants method
	 */
//	public List<RestaurantModel> viewRestaurantByItemName(String name) throws OFDAException;
}
