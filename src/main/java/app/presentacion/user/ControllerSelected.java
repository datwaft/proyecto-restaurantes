/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Bill;
import app.logic.SelectedAdditional;
import app.logic.SelectedAdditionalCategory;
import app.logic.SelectedDish;
import app.logic.model.BillModel;
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

      Sdish = SelectedDishModel.getInstance().searchByBill(1);
      System.out.println(Sdish);
      SaddCat = SelectedAdditionalCategoryModel.getInstance().searchByBill(1);
      System.out.println(SaddCat);
      Sadd = SelectedAdditionalModel.getInstance().searchByBill(1);
      System.out.println(Sadd);

      listas.put("SelectedDishes", Sdish);
      listas.put("SelectedAdditionalCategory", SaddCat);
      listas.put("SelectedAdditional", Sadd);

      return listas;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
}
