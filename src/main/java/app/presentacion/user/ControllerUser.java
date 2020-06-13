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
public class ControllerUser {

    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public User getUser(User userData) {
        try {
            User user = UserModel.getInstance().search(userData.getEmail()).get(0);
            if (user == null)
              throw new Exception("Invalid user");
            else if(!user.getPassword().equals(userData.getPassword()))
            {
             throw new Exception("Invalid password");
            }
            return user;
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
            throw new NotFoundException(); 
        }
    }
    
    @PUT
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean registerUser(User userData) {
        try {
            System.out.print(userData);
            UserModel.getInstance().create(userData);
            return true;
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
            throw new NotFoundException(); 
        }
    }
}
