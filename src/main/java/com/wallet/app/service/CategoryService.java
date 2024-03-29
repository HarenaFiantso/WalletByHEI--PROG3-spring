package com.wallet.app.service;

import com.wallet.app.db.entity.Category;
import com.wallet.app.repository.CategoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
  private CategoryRepository categoryRepository;

  public Category retrieveCategoryById(String categoryId) {
    return categoryRepository.findById(categoryId);
  }

  public List<Category> retrieveAllCategories() {
    return categoryRepository.findAll();
  }

  public Category createCategory(Category toSave) {
    return categoryRepository.save(toSave);
  }

  public Category updateCategory(Category toUpdate) {
    return categoryRepository.update(toUpdate);
  }

  public Category deleteCategoryById(String toDelete) {
    return categoryRepository.delete(toDelete);
  }
}
