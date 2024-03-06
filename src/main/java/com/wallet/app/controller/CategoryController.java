package com.wallet.app.controller;

import com.wallet.app.db.entity.Category;
import com.wallet.app.service.CategoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
    Category category = categoryService.retrieveCategoryById(id);
    if (category != null) {
      return ResponseEntity.ok(category);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.retrieveAllCategories();
    return ResponseEntity.ok(categories);
  }

  @PostMapping("/create")
  public ResponseEntity<Category> createCategory(@RequestBody Category toSave) {
    Category savedCategory = categoryService.createCategory(toSave);

    if (savedCategory != null) {
      return ResponseEntity.ok(savedCategory);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public Category udpateCategory(@PathVariable String id, @RequestBody Category toUpdate) {
    toUpdate.setCategoryId(id);
    return categoryService.updateCategory(toUpdate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> deleteCategory(@PathVariable String id) {
    Category deletedCategory = categoryService.deleteCategoryById(id);
    if (deletedCategory != null) {
      return ResponseEntity.ok(deletedCategory);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
