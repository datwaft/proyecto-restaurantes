/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Bill;
import app.logic.model.BillModel;
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
@Path("/Orders")
@RequestScoped
public class ControllerOrder {

  @Context
  private UriInfo context;

  
  public ControllerOrder() {
  }
  @Path("/orders")
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Bill> getDishes(Bill bill) {
    try {        
          return BillModel.getInstance().search(bill.getId());
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }
  
  @Path("/status")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Bill updateStatus(Bill bill) {
    try {
      Bill exist = BillModel.getInstance().exist(bill.getId());
      
      if (exist != null) {
        
        System.out.print(exist);
        exist.setStatus(bill.getStatus());
        BillModel.getInstance().edit(exist);
        return exist;
        
      } else {
        
        throw new NotFoundException();
      }
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
}
