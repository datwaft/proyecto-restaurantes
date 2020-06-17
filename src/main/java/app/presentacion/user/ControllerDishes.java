/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Dish;
import app.logic.model.DishModel;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
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
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Dish updateDishes( Dish dish) {
    try {
         Dish exist = DishModel.getInstance().exist(dish.getId());
      if (exist != null) {        
        System.out.print(dish);
        DishModel.getInstance().edit(dish);
        return dish;
      } else {
         throw new NotFoundException();
      }
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }

 
}
