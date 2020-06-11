package app.presentacion.user;

import app.logic.User;
import app.logic.model.UserModel;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;

@Path("/users")
public class Personas {

    
    @GET
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> get(@PathParam("cedula") String cedula) {
        try {
            return UserModel.getInstance().getAll();
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
}
