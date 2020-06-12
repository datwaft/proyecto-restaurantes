/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.model.CategoryModel;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;


@Path("/category")
@RequestScoped
public class ControllerCategory {

  @Context
  private UriInfo context;

  @GET
    @Path("/us")
    @Produces({MediaType.APPLICATION_JSON})
    public List<app.logic.Category> get() {
        try {
           return CategoryModel.getInstance().getAll();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
}
