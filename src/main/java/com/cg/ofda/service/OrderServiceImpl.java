package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.OrderDetailsEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.OrderDetailsModel;
import com.cg.ofda.repository.IOrderRepository;
import com.cg.ofda.util.EMParserOrderDetails;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private EMParserOrderDetails parser;

	public OrderServiceImpl() {
		this.parser = new EMParserOrderDetails();
	}

	/*
	 * Parameterized for assigning
	 */

	public OrderServiceImpl(IOrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
		this.parser = new EMParserOrderDetails();
	}

	/*
	 * Implementation of addOrder method to add an order
	 */

	@Transactional
	@Override
	public OrderDetailsModel addOrder(OrderDetailsModel order) throws OFDAException {
		if (order != null) {
			if (orderRepository.existsById(order.getOrderId())) {
				throw new OFDAException("Order with this id already exists");
			}

			order = parser.parse(orderRepository.save(parser.parse(order)));
		}

		return order;
	}

	/*
	 * Implementation of updateOrder method to update existing order
	 */

	@Transactional
	@Override
	public OrderDetailsModel updateOrder(OrderDetailsModel order) throws OFDAException {
		OrderDetailsEntity oldOrder = orderRepository.findById(order.getOrderId()).orElse(null);
		if (oldOrder == null) {
			throw new OFDAException("no order with id #" + order.getOrderId() + " present");
		} else {
			order = parser.parse(orderRepository.save(parser.parse(order)));
		}
		return order;
	}

	/*
	 * Implementation of removeOrder method to remove existing order
	 */

	@Transactional
	@Override
	public boolean removeOrder(Long orderId) throws OFDAException {
		boolean isDeleted = false;
		OrderDetailsEntity oldOrder = orderRepository.findById(orderId).orElse(null);
		if (oldOrder == null) {
			throw new OFDAException("no order with id #" + orderId + " present");
		} else {
			orderRepository.deleteById(orderId);
			isDeleted = true;
		}
		return isDeleted;
	}

	/*
	 * Implementation of viewOrder method to view an order
	 */

	@Override
	public OrderDetailsModel viewOrder(Long orderId) throws OFDAException {
		OrderDetailsEntity oldOrder = orderRepository.findById(orderId).orElse(null);
		if (oldOrder == null) {
			throw new OFDAException("no order with id #" + orderId + " present");
		}
		return parser.parse(orderRepository.findById(orderId).orElse(null));
	}

	/*
	 * Implementation of viewAllOrders method to view all orders
	 */

	@Override
	public List<OrderDetailsModel> viewAllOrders() throws OFDAException {

		return orderRepository.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

}