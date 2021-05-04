package com.cg.ofda.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.ItemModel;
import com.cg.ofda.service.IItemService;


@RestController
@RequestMapping("/item")
public class ItemRestController {
	
	@Autowired
	private IItemService itemService;
	
	/*
	 * to retrieve all items
	 * return : List<item>
	 * params : NIL
	 */
	
	@GetMapping
	public ResponseEntity<List<ItemModel>> viewAllItems() throws OFDAException {
		return new ResponseEntity<>(itemService.viewAllItems(), HttpStatus.OK);
	}
	
	
	/*
	 * to add an item
	 * return : item
	 * params : NIL
	 */
	
	@PostMapping
	public ResponseEntity<ItemModel> addItem(@RequestBody ItemModel item) throws OFDAException {
		item = itemService.addItem(item);
		return new ResponseEntity<>(item, HttpStatus.CREATED);
	}
	
	/*
	 * to modify an item
	 * return : item
	 * params : NIL
	 */
	
	@PutMapping
	public ResponseEntity<ItemModel> updateItem(@RequestBody ItemModel item) throws OFDAException {
		item = itemService.updateItem(item);
		// return new ResponseEntity<>(emp, HttpStatus.OK);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	/*
	 * to remove an item
	 * return : void
	 * params : itemId
	 */
	
	@DeleteMapping("/{itemId}")
	public ResponseEntity<Void> removeItem(@PathVariable("itemId") Long id) throws OFDAException{
		ResponseEntity<Void> response = null;
		ItemModel item = itemService.viewItem(id);
		if (item == null) {
			// response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			response = ResponseEntity.notFound().build();
		} else {
			itemService.removeItem(id);
			// response = new ResponseEntity<>(HttpStatus.OK);
			response = ResponseEntity.ok().build();
		}
		return response;
	}
	
	/*
	 * to retrieve an item
	 * return : item
	 * params : itemId
	 */
	
	@GetMapping("/{itemId}")
	public ResponseEntity<ItemModel> viewItem(@PathVariable("itemId") Long id) throws OFDAException {
		ResponseEntity<ItemModel> response = null;
		ItemModel item = itemService.viewItem(id);
		if (item == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			itemService.viewItem(id);
			response = new ResponseEntity<>(item, HttpStatus.OK);
		}
		return response;
	}
}