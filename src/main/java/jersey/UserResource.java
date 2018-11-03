package jersey;

import java.util.*;
import java.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("users")
public class UserResource
{
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> get() throws IOException
   {
      return User.list();
   }
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public void post(User user) throws IOException
   {
      user.push();
   }
}
