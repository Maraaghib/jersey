package jersey;

import java.util.*;
import java.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("users")
public class UserResource
{
   @GET
   @Path("list")
   @Produces(MediaType.APPLICATION_JSON)
   public List<User> list() throws IOException
   {
      return User.list();
   }

   @POST
   @Path("get")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public User get(User id) throws IOException
   {
      return User.get(id.id);
   }

   @POST
   @Path("delete")
   @Consumes(MediaType.APPLICATION_JSON)
   public void delete(User id) throws IOException
   {
      User.delete(id.id);
   }

   @POST
   @Path("add")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public User add(User user) throws IOException
   {
      return user.add();
   }

   @POST
   @Path("update")
   @Consumes(MediaType.APPLICATION_JSON)
   public void update(User user) throws IOException
   {
      user.update();
   }
}
