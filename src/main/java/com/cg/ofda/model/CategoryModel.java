package com.cg.ofda.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryModel {
	
	/*
	 * All the private members are validate here with suitable datatypes
	 * 
	 */
	@NotNull(message="category id cannot be null")
	private Long catId;

	@NotEmpty(message="Category Name cannot be empty")
	@NotNull(message="Category Name cannot be omitted")
	private String categoryName;
	
	@NotEmpty(message="itemList cannot be empty")
	@NotNull(message="itemList cannot be omitted")
	private List<ItemModel> itemList;
	/*
	 * A default Constructor with no implementation
	 */
	public CategoryModel() {
		// default
	}


	/*
	 * A Parameterized Constructor for assigning the values to private members
	 */
	

	public CategoryModel(@NotNull(message = "category id cannot be null") Long catId,
			@NotEmpty(message = "Category Name cannot be empty") @NotNull(message = "Category Name cannot be omitted") String categoryName) {
			super();
		this.catId = catId;
		this.categoryName = categoryName;
	}


	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	


	/* 
	 * Corresponding HashCode and Equals methods 
	 * 
	 * */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catId == null) ? 0 : catId.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryModel other = (CategoryModel) obj;
		if (catId == null) {
			if (other.catId != null)
				return false;
		} else if (!catId.equals(other.catId))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		return true;
	}


	

	
	/*
	 * Corresponding toString
	 * */

	
	@Override
	public String toString() {
		return String.format("CategoryModel [catId=%s, categoryName=%s, itemList=%s]", catId, categoryName, itemList);
	}
	
	
	

}