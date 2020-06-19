/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Additional;
import app.logic.AdditionalCategory;
import app.logic.Dish;
import app.logic.model.AdditionalCategoryModel;
import app.logic.model.AdditionalModel;
import app.logic.model.CategoryModel;
import app.logic.model.DishModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Mario
 */
@Path("/Dishes")
@RequestScoped
public class ControllerDishes {

  @Context
  private UriInfo context;

  
  public ControllerDishes() {
  }
  @Path("/dishes")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Dish> getDishes() {
    try {
           return DishModel.getInstance().getAll();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }
  
  @Path("/update")
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Dish updateDishes( Dish dish) {
    try {
         Dish exist = DishModel.getInstance().exist(dish.getId());
      if (exist != null) {        
//        dish.setCategory(CategoryModel.getInstance().find(2));
        
        DishModel.getInstance().edit(dish);
        return dish;
      } else {
         throw new NotFoundException();
      }
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }

  @Path("/complements/{id}")
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> getDishes(@PathParam("id") String id) {
    try {
      Map<String, Object> listas = new HashMap<>();
      List<AdditionalCategory> addC = new ArrayList<>();
      List<Additional> add = new ArrayList<>();


      addC = AdditionalCategoryModel.getInstance().searchByDish(1);
      System.out.println(addC);
      add = AdditionalModel.getInstance().searchByDish(1);
      System.out.println(add);

      listas.put("AdditionalCategory", addC);
      listas.put("Additionals", add);

      return listas;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  @POST
  @Path("/create")
  @Produces({MediaType.APPLICATION_JSON})
  public Dish create(Dish add) {
    try {
//      app.logic.Category cat = CategoryModel.getInstance().find(1);
//      add.setCategory(cat);
      System.out.print(add.getDescription());
      DishModel.getInstance().create(add);
      return add;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
 
}
