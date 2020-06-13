///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package app.presentacion.user;
//
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.UriInfo;
//import javax.ws.rs.Produces;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PUT;
//import javax.enterprise.context.RequestScoped;
//import javax.ws.rs.NotFoundException;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.MediaType;
//
///**
// * REST Web Service
// *
// * @author Mario
// */
//@Path("/Additionals")
//@RequestScoped
//public class ControllerAdditionals {
//
//  @Context
//  private UriInfo context;
//
//
//  public ControllerAdditionals() {
//  }
//
//  @GET
//  @Path("/additionals")
//  @Produces(MediaType.APPLICATION_JSON)
//  public String getAdditionals() {
//    
//    try {
//            //operaciones de la bd para traer por id.
//        } catch (Exception ex) {
//            throw new NotFoundException(); 
//        }
//    return null;
//  }
//  
//  @GET
//  @Path("/adi-cats")
//  @Produces(MediaType.APPLICATION_JSON)
//  public String getAdditionalCategories() {
//    
//    try {
//            //operaciones de la bd para traer por id.
//        } catch (Exception ex) {
//            throw new NotFoundException(); 
//        }
//    return null;
//  }
//
//}
