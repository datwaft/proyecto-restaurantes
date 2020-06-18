/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Address;
import app.logic.model.AddressModel;
import app.logic.model.CategoryModel;
import app.logic.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/Address")
@RequestScoped
public class ControllerAddress {

  @Context
  private UriInfo context;

  @GET
  @Path("/address/{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Address> get(@PathParam("id") String id) {
    try {
      System.out.println("la pttttttttttttttttta");
      System.out.println(id);
      
      return AddressModel.getInstance().getAll(id);
    } catch (Exception ex) {
      return new ArrayList<Address>();
    }
  }

  @POST
  @Path("/create")
  @Produces({MediaType.APPLICATION_JSON})
  public Address get(Address add) {
    try {

      System.out.print(add.getAddress1());
      AddressModel.getInstance().create(add);
      return add;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  @PUT
  @Path("/update")
  @Produces({MediaType.APPLICATION_JSON})
  public Address update(Address add) {
    try {
      
      Address exist = AddressModel.getInstance().find(add.getId());
      System.out.println("la wea llega aqui");
      System.out.print(add.getCountry());
      if (exist != null)
      {
        exist.setAddress1(add.getAddress1());
        exist.setAddress2(add.getAddress2());
        exist.setCity(add.getCity());
        exist.setCountry(add.getCountry());
        exist.setPostcode(add.getPostcode());
        exist.setState(add.getState());
        
        AddressModel.getInstance().edit(exist);
        return exist;
      }
      else
      {
        throw new NotFoundException();
      }
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
  
  @DELETE
  @Path("/delete/{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public Boolean del(@PathParam("id") String id) {
    try {  
      Address exist = AddressModel.getInstance().find(Integer.valueOf(id));
      if (exist != null)
      {
        AddressModel.getInstance().delete(exist);
        return true;
      }
      else
        return false;
      
      
    } catch (Exception ex) {
      return false;
    }
  }
    
}
