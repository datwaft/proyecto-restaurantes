/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.AdditionalCategory;
import app.logic.Bill;
import app.logic.SelectedAdditional;
import app.logic.SelectedAdditionalCategory;
import app.logic.SelectedDish;
import app.logic.model.AdditionalCategoryModel;
import app.logic.model.AdditionalModel;
import app.logic.model.BillModel;
import app.logic.model.DishModel;
import app.logic.model.SelectedAdditionalCategoryModel;
import app.logic.model.SelectedAdditionalModel;
import app.logic.model.SelectedDishModel;
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
@Path("/Selected")
@RequestScoped
public class ControllerSelected {

  @Context
  private UriInfo context;

  public ControllerSelected() {
  }

  @Path("/getall/{id}")
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Map<String, Object> getDishes(@PathParam("id") String id) {
    try {
      Map<String, Object> listas = new HashMap<String, Object>();
      List<SelectedDish> Sdish = new ArrayList<>();
      List<SelectedAdditionalCategory> SaddCat = new ArrayList<>();
      List<SelectedAdditional> Sadd = new ArrayList<>();

      Sdish = SelectedDishModel.getInstance().searchByBill(Integer.parseInt(id));
      System.out.println(Sdish);
      SaddCat = SelectedAdditionalCategoryModel.getInstance().searchByBill(Integer.parseInt(id));
      System.out.println(SaddCat);
      Sadd = SelectedAdditionalModel.getInstance().searchByBill(Integer.parseInt(id));
      System.out.println(Sadd);

      listas.put("SelectedDishes", Sdish);
      listas.put("SelectedAdditionalCategory", SaddCat);
      listas.put("SelectedAdditional", Sadd);

      return listas;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  
  @POST
  @Path("/dish-create")
  @Produces({MediaType.APPLICATION_JSON})
  public SelectedDish createSelectedDish(SelectedDish add) {
    try {
//      //---------------
//      app.logic.Dish prueba1 = DishModel.getInstance().getAll().get(0);
//      add.setDish(prueba1);
//      app.logic.Bill prueba2 = BillModel.getInstance().exist(1);
//      add.setBill(prueba2);
//      //----------------------

      System.out.print(add.getQuantity());
      SelectedDishModel.getInstance().create(add);
       List<SelectedDish> list = SelectedDishModel.getInstance().getAll();
      return list.get(list.size()-1);
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  @PUT
  @Path("/addcategory-create")
  @Produces({MediaType.APPLICATION_JSON})
  public SelectedAdditionalCategory createSelectedCategory(SelectedAdditionalCategory add) {
    try {
//      //---------------
//      SelectedDish prueba1 = SelectedDishModel.getInstance().getAll().get(0);
//      add.setSelectedDish(prueba1);
//      AdditionalCategory prueba2 = AdditionalCategoryModel.getInstance().getAll().get(0);
//      add.setAdditionalCategory(prueba2);
//      //----------------------

      SelectedAdditionalCategoryModel.getInstance().create(add);
       List<SelectedAdditionalCategory> list = SelectedAdditionalCategoryModel.getInstance().getAll();
      return list.get(list.size()-1);
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  @POST
  @Path("/additional-create")
  @Produces({MediaType.APPLICATION_JSON})
  public SelectedAdditional createSelectedAdditional(SelectedAdditional add) {
    try {
//      ---------------
//      SelectedAdditionalCategory prueba1 = SelectedAdditionalCategoryModel.getInstance().getAll().get(0);
//      add.setSelectedAdditionalCategory(prueba1);
//      app.logic.Additional prueba2 = AdditionalModel.getInstance().getAll().get(0);
//      add.setAdditional(prueba2);
//      ----------------------


      SelectedAdditionalModel.getInstance().create(add);
       List<SelectedAdditional> list = SelectedAdditionalModel.getInstance().getAll();
      return list.get(list.size()-1);
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
}
