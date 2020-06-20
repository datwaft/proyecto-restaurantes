/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.presentacion.user;

import app.logic.Bill;
import app.logic.model.BillModel;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
@Path("/Orders")
@RequestScoped
public class ControllerOrder {

  @Context
  private UriInfo context;

  
  public ControllerOrder() {
  }
  @Path("/orders/{id}")
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Bill> getOrders(@PathParam("id") String id) {
    try {        
          return BillModel.getInstance().search(id);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }
  
  @Path("/orders/all")
  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public List<Bill> getAllOrders() {
    try {        
          return BillModel.getInstance().getAll();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
  }
  
  
  
  @Path("/status")
  @PUT
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
  
  
  @POST
  @Path("/create/{date}")
  @Produces({MediaType.APPLICATION_JSON})
  public Bill create(Bill add, @PathParam("date") String day) {
    try {

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = format.parse(day);

      add.setOrderTime(date);

      BillModel.getInstance().create(add);

      List<Bill> bill = BillModel.getInstance().getAll();
      Bill exist = bill.get(bill.size() - 1);

      return exist;
    } catch (Exception ex) {
      throw new NotFoundException();
    }
  }
}
