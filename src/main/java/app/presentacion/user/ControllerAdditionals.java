/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Additional;
import app.logic.AdditionalCategory;
import app.logic.model.AdditionalCategoryModel;
import app.logic.model.AdditionalModel;
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
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Mario
 */
@Path("/Additionals")
@RequestScoped
public class ControllerAdditionals {

  @Context
  private UriInfo context;


  public ControllerAdditionals() {
  }

  @GET
  @Path("/additionals")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Additional> getAdditionals() {
    
    try {
             System.out.println("Llega a jalar las additional category");
            return AdditionalModel.getInstance().getAll();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }
  
  @GET
  @Path("/adi-cats")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AdditionalCategory> getAdditionalCategories() {
    
    try {
          System.out.println("Llega a jalar las additional category");
           return AdditionalCategoryModel.getInstance().getAll();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }

  @POST
  @Path("/create")
  @Produces({MediaType.APPLICATION_JSON})
  public Additional create(Additional add) {
    try {
      
      add.setAdditionalCategory(AdditionalCategoryModel.getInstance().find(1));
      System.out.print(add.getDescription());
      AdditionalModel.getInstance().create(add);
      return add;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  @POST
  @Path("/create-cat")
  @Produces({MediaType.APPLICATION_JSON})
  public AdditionalCategory createCategory(AdditionalCategory add) {
    try {
      add.setDish(DishModel.getInstance().find(1));
      System.out.print(add.getDescription());
      AdditionalCategoryModel.getInstance().create(add);
      return add;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
}
