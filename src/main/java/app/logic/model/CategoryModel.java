
package app.logic.model;

import app.data.CategoryDao;

public class CategoryModel extends CategoryDao{
  
  private CategoryModel() {
  }
  
  public static CategoryModel getInstance() {
    return CategoryModelHolder.INSTANCE;
  }
  
  private static class CategoryModelHolder {

    private static final CategoryModel INSTANCE = new CategoryModel();
  }
}
