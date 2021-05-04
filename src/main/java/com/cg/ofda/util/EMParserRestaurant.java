package com.cg.ofda.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.RestaurantEntity;
import com.cg.ofda.model.RestaurantModel;
import com.cg.ofda.repository.IRestaurantRepository;

@Service
public class EMParserRestaurant {
	
	@Autowired
	private IRestaurantRepository billRepo;
	
	@Autowired
	private EMParserItem itemParser;
	
//	
//	public EMParserRestaurant() {
//		this.itemParser= new EMParserItem();
//	}
	
	
//	public EMParserRestaurant(IRestaurantRepository billRepo) {
//		super();
//		this.billRepo = billRepo;
//		this.itemParser= new EMParserItem();
//	}

	public RestaurantModel parse(RestaurantEntity source) {
		return source==null ? null:
			new RestaurantModel (source.getRestaurantId(),
					source.getRestaurantName(),
					source.getAddress(),
					itemParser.parseEntity(source.getItemList()),
					source.getManagerName(),
					source.getContactNumber());
					
	}
	
	public RestaurantEntity parse(RestaurantModel source) {
		return source==null ? null:
			new RestaurantEntity (source.getRestaurantId(),
					source.getRestaurantName(),
					source.getAddress(),
					itemParser.parse(source.getItemList()),
					source.getManagerName(),
					source.getContactNumber());
	}

	public List<RestaurantEntity> parse(List<RestaurantModel> list){
		
		List<RestaurantEntity> rlist =new ArrayList<>();
		for(RestaurantModel model : list) {
			rlist.add(parse(model));
		}
		return rlist;
	}
	
	public List<RestaurantModel> parseEntity(List<RestaurantEntity> list){
		
		List<RestaurantModel> rlist =new ArrayList<>();
		for(RestaurantEntity entity : list) {
			rlist.add(parse(entity));
		}
		return rlist;
	}
}
