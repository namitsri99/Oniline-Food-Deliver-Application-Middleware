package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.CategoryEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.CategoryModel;
import com.cg.ofda.repository.ICategoryRepository;
import com.cg.ofda.util.EMParserCategory;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private EMParserCategory parser;

	public CategoryServiceImpl() {
		this.parser = new EMParserCategory();
	}

	/*
	 * Parameterized for assigning
	 */

	public CategoryServiceImpl(ICategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.parser = new EMParserCategory();
	}

	/*
	 * Implementation of addCategory method to add a category
	 */

	@Transactional
	@Override
	public CategoryModel addCategory(CategoryModel cat) throws OFDAException {
		if (cat != null) {
			if (categoryRepository.existsById(cat.getCatId())) {
				throw new OFDAException("Category with this id already exists");
			}

			cat = parser.parse(categoryRepository.save(parser.parse(cat)));
		}

		return cat;
	}

	/*
	 * Implementation of updateCategory method to update existing category
	 */

	@Transactional
	@Override
	public CategoryModel updateCategory(CategoryModel cat) throws OFDAException {
		CategoryEntity category= categoryRepository.findById(cat.getCatId()).orElse(null);
		if(category== null)
			throw new OFDAException("no category with id #" + cat.getCatId() + " present");
		cat = parser.parse(categoryRepository.save(parser.parse(cat)));
		return cat;
	}

	/*
	 * Implementation of removeCategory method to remove a category
	 */

	@Transactional
	@Override
	public boolean removeCategory(Long catId) throws OFDAException {
		CategoryEntity oldEntity = categoryRepository.findById(catId).orElse(null);
		boolean isDeleted = false;
		if (oldEntity == null) {
			throw new OFDAException("no category with id #" + catId + " present");
		} else {
			categoryRepository.deleteById(catId);
			isDeleted = true;
		}
		return isDeleted;
	}

	/*
	 * Implementation of viewCategory method to view a category
	 */

	@Override
	public CategoryModel viewCategory(Long catId) throws OFDAException {
		CategoryEntity oldCategory = categoryRepository.findById(catId).orElse(null);
		if (oldCategory == null) {
			throw new OFDAException("no category with id #" + catId + " present");
		}
		return parser.parse(categoryRepository.findById(catId).orElse(null));
	}

	/*
	 * Implementation of viewAllCategory method to view all the category
	 */

	@Override
	public List<CategoryModel> viewAllCategory() throws OFDAException {

		return categoryRepository.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

}
