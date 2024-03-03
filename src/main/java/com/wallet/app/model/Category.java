package com.wallet.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
  @Id private String categoryId;
  private String CategoryName;

  public Category(String categoryId, String categoryName) {
    this.categoryId = categoryId;
    CategoryName = categoryName;
  }

  public Category() {}

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return CategoryName;
  }

  public void setCategoryName(String categoryName) {
    CategoryName = categoryName;
  }
}
