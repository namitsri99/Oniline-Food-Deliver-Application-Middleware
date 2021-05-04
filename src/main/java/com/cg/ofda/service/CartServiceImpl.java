package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.FoodCartEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.FoodCartModel;
import com.cg.ofda.repository.ICartRepository;
import com.cg.ofda.util.EMParserFoodCart;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private ICartRepository cartRepo;

	@Autowired
	private EMParserFoodCart parser;

	public CartServiceImpl() {
		this.parser = new EMParserFoodCart();
	}

	public CartServiceImpl(ICartRepository cartRepo) {
		super();
		this.cartRepo = cartRepo;
		this.parser = new EMParserFoodCart();
	}

//	@Transactional
//	@Override
//	public FoodCartModel addItemToCart(FoodCartModel cart, ItemModel item) throws OFDAException {
//		if (item != null) {
//			if (cartRepo.existsById(item.getItemId())) {
//				throw new OFDAException("Item with this id already exists");
//			}
//			
//			List<ItemModel> item1= new ArrayList<>();
//			item1.add(item);
//			cart.setItemList(item1);
//			
//			cart = parser.parse(cartRepo.save(parser.parse(cart)));
//		}
//
//		return cart;
//	}
//
//	@Transactional
//	@Override
//	public FoodCartModel increaseQuantity(FoodCartModel cart, ItemModel item, int quantity) throws OFDAException {
//
//		if (item != null) {
//			if (!cartRepo.existsById(item.getItemId())) {
//				throw new OFDAException("Item with this id does not exists");
//			}
//			item.setQuantity(item.getQuantity() + quantity);
//			item.setFoodCart(cart);
//			cart = parser.parse(cartRepo.save(parser.parse(cart)));
//		}
//
//		return cart;
//	}
//
//	@Transactional
//	@Override
//	public FoodCartModel reduceQuantity(FoodCartModel cart, ItemModel item, int quantity) throws OFDAException {
//		if (item != null) {
//			if (!cartRepo.existsById(item.getItemId())) {
//				throw new OFDAException("Item with this id does not exists");
//			}
//			item.setQuantity(item.getQuantity() - quantity);
//			item.setFoodCart(cart);
//			cart = parser.parse(cartRepo.save(parser.parse(cart)));
//		}
//
//		return cart;
//	}

	/* Implementaion of removeCart to remove the existing cart */

	@Transactional
	@Override
	public boolean removeCart(Long cartId) throws OFDAException {

		FoodCartEntity oldCart = cartRepo.findById(cartId).orElse(null);
		boolean isDeleted = false;
		if (oldCart == null) {
			throw new OFDAException("no cart with id #" + cartId + " present");
		} else {
			cartRepo.deleteById(cartId);
			isDeleted = true;
		}
		return isDeleted;
	}

	/* Implementaion of addCart to add a new cart */

	@Transactional
	@Override
	public FoodCartModel addCart(FoodCartModel cart) throws OFDAException {

		if (cart != null) {
			if (cartRepo.existsById(cart.getCartId())) {
				throw new OFDAException("Cart with this id already exists");
			}
			cart = parser.parse(cartRepo.save(parser.parse(cart)));
		}
		return cart;

	}

	/* Implementation of updateCart to update the existing cart */

	@Transactional
	@Override
	public FoodCartModel updateCart(FoodCartModel cart) throws OFDAException {
		FoodCartEntity cart1 = cartRepo.findById(cart.getCartId()).orElse(null);
		if (cart1 == null) {
			throw new OFDAException("no cart with id #" + cart.getCartId() + " present");
		}
		cart = parser.parse(cartRepo.save(parser.parse(cart)));
		return cart;
	}

	/* Implementaion of clearCart to clear all the carts */

	@Transactional
	@Override
	public FoodCartModel clearCart(FoodCartModel cart) throws OFDAException {
		cartRepo.deleteAll();
		return cart;
	}

	/* Implementation of viewCart to view the existing cart */

	@Transactional
	@Override
	public FoodCartModel viewCart(Long cartId) throws OFDAException {

		FoodCartEntity foodCart = cartRepo.findById(cartId).orElse(null);
		if (foodCart == null) {
			throw new OFDAException("cart with id #" + cartId + " not exists");
		}
		return parser.parse(cartRepo.findById(cartId).orElse(null));
	}

	/* Implementation of viewCart to view all the carts */

	@Transactional
	@Override
	public List<FoodCartModel> viewAllCarts() throws OFDAException {

		return cartRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

}