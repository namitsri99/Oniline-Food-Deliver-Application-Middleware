package com.cg.ofda.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.RestaurantEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.RestaurantModel;
import com.cg.ofda.repository.IRestaurantRepository;
import com.cg.ofda.util.EMParserRestaurant;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

	@Autowired
	private IRestaurantRepository resRepo;

	@Autowired
	private EMParserRestaurant parser;

	public RestaurantServiceImpl() {
		this.parser = new EMParserRestaurant();
	}

	/*
	 * Parameterized for assigning
	 */
	public RestaurantServiceImpl(IRestaurantRepository resRepo) {
		super();
		this.resRepo = resRepo;
		this.parser = new EMParserRestaurant();
	}

	/*
	 * Implementation of addRestaurant method to add a Restaurant
	 */

	@Transactional
	@Override
	public RestaurantModel addRestaurant(RestaurantModel res) throws OFDAException {
		if (res != null) {
			if (resRepo.existsById(res.getRestaurantId())) {
				throw new OFDAException("Restaurant with this id already exists");
			}
			res = parser.parse(resRepo.save(parser.parse(res)));
		}

		return res;

	}

	/*
	 * Implementation of removeRestaurant method to remove existing Restaurant
	 */

	@Transactional
	@Override
	public boolean removeRestaurant(Long restaurantId) throws OFDAException {
		boolean isDeleted = false;
		RestaurantEntity restaurant = resRepo.findById(restaurantId).orElse(null);
		if (restaurant == null) {
			throw new OFDAException("no restaurant with id #" + restaurantId + " present");
		} else {
			resRepo.deleteById(restaurantId);
			isDeleted = true;
		}
		return isDeleted;
	}

	/*
	 * Implementation of updateRestaurant method to update existing Restaurant
	 */

	@Transactional
	@Override
	public RestaurantModel updateRestaurant(RestaurantModel res) throws OFDAException {
		if (res != null) {
			if (!resRepo.existsById(res.getRestaurantId())) {
				throw new OFDAException("No Such Restaurant Found");

			}
			res = parser.parse(resRepo.save(parser.parse(res)));
		}
		return res;
	}

	/*
	 * Implementation of viewRestaurant method to view a Restaurant
	 */

	@Override
	public RestaurantModel viewRestaurant(Long restaurantId) throws OFDAException {
		RestaurantEntity restaurant = resRepo.findById(restaurantId).orElse(null);
		if (restaurant == null) {
			throw new OFDAException("no restaurant with id #" + restaurantId + " present");
		}
		return parser.parse(resRepo.findById(restaurantId).orElse(null));
	}

	/*
	 * Implementation of viewAllRestaurants method to view all the Restaurants
	 */

	@Override
	public List<RestaurantModel> viewAllRestaurants() throws OFDAException {

		return parser.parseEntity(resRepo.findAll());
	}

}