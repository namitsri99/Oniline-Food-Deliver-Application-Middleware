package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.ItemEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.ItemModel;
import com.cg.ofda.repository.IItemRepository;
import com.cg.ofda.util.EMParserItem;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private IItemRepository itemRepo;

	@Autowired
	private EMParserItem parser;

	public ItemServiceImpl() {
		this.parser = new EMParserItem();
	}

	public ItemServiceImpl(IItemRepository itemRepo) {
		super();
		this.itemRepo = itemRepo;
		this.parser = new EMParserItem();
	}

	/*
	 * Implementation of addItem method to add Item
	 */

	@Transactional
	@Override
	public ItemModel addItem(ItemModel item) throws OFDAException {
		if (item != null) {
			if (itemRepo.existsById(item.getItemId())) {
				throw new OFDAException("Item with this id already exists");
			}

			item = parser.parse(itemRepo.save(parser.parse(item)));
		}

		return item;
	}

	/*
	 * Implementation of viewItem method to view Item
	 */

	@Override
	public ItemModel viewItem(Long id) throws OFDAException {
		ItemEntity item = itemRepo.findById(id).orElse(null);
		return parser.parse(item);
	}

	/*
	 * Implementation of updateItem method to update Item
	 */

	@Transactional
	@Override
	public ItemModel updateItem(ItemModel item) throws OFDAException {
		ItemEntity oldItem = itemRepo.findById(item.getItemId()).orElse(null);
		if (oldItem == null) {
			throw new OFDAException("no item with id #" + item.getItemId() + " present");
		} else {
			item = parser.parse(itemRepo.save(parser.parse(item)));
		}
		return item;
	}

	/*
	 * Implementation of removeItem method to remove Item
	 */

	@Transactional
	@Override
	public boolean removeItem(Long id) throws OFDAException {
		ItemEntity oldItem = itemRepo.findById(id).orElse(null);
		boolean isDeleted = false;
		if (oldItem == null) {
			throw new OFDAException("no item with id #" + id + " present");
		} else {
			itemRepo.deleteById(id);
			isDeleted = true;
		}
		return isDeleted;
	}

	/*
	 * Implementation of viewAllItems method to view all Items
	 */
	@Override
	public List<ItemModel> viewAllItems() throws OFDAException {

		return itemRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

}