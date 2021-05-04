package com.cg.ofda.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.ItemEntity;
import com.cg.ofda.model.ItemModel;
import com.cg.ofda.repository.IItemRepository;

@Service
public class EMParserItem {

//	@Autowired
//	private IItemRepository itemRepo;
	
	@Autowired
	private EMParserFoodCart cartParser;
	
	@Autowired
	private EMParserCategory categoryParser;
	
	@Autowired
	private EMParserRestaurant restaurantParser;
	
//	public EMParserItem() {
//		this.cartParser= new EMParserFoodCart();
//		this.categoryParser= new EMParserCategory();
//		this.restaurantParser= new EMParserRestaurant();	
//	}
//	
//	
//	
//	public EMParserItem(IItemRepository itemRepo) {
//		super();
////		this.itemRepo = itemRepo;
//		this.cartParser= new EMParserFoodCart();
//		this.categoryParser= new EMParserCategory();
//		this.restaurantParser= new EMParserRestaurant();
//	}



	public ItemEntity parse(ItemModel source) {
		return source == null ? null :
			new ItemEntity(source.getItemId(),
					source.getItemName(),
					categoryParser.parse(source.getCategory()),
					source.getQuantity(),
					source.getCost(),
					restaurantParser.parse(source.getRestaurants()),
					cartParser.parse(source.getFoodCart()));
	}
	
	public ItemModel parse(ItemEntity source) {
		return source == null ? null :
			new ItemModel(source.getItemId(),
					source.getItemName(),
					categoryParser.parse(source.getCategory()),
					source.getQuantity(),
					source.getCost(),
					restaurantParser.parseEntity(source.getRestaurants()),
					cartParser.parse(source.getFoodCart()));
	}
	
	public List<ItemEntity> parse(List<ItemModel> list){
		
		List<ItemEntity> rlist =new ArrayList<>();
		for(ItemModel model : list) {
			rlist.add(parse(model));
		}
		return rlist;
	}	

	public List<ItemModel> parseEntity(List<ItemEntity> list){
		
		List<ItemModel> rlist =new ArrayList<>();
		for(ItemEntity entity : list) {
			rlist.add(parse(entity));
		}
		return rlist;
	}

}
