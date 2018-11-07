package jersey;

import java.util.*;
import java.io.*;
import java.security.*;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User
{
   public static final String DB = "users";
   public static final String COLLECTION = "users";

   public String id;
   
   public String firstname;
   public String lastname;
   
   public User add() throws IOException
   {
      id = newId();
      collection().insertOne(toDocument());
      return this;
   }
   public void update() throws IOException
   {
      Document query = new Document();
      query.put("id", id);
      
      collection().replaceOne(query, toDocument());
   }   
   public static List<User> list() throws IOException
   {
      List<Document> docs = (List<Document>) collection().find().into(new ArrayList<Document>());
      List<User> res = new ArrayList<>();
      for (Document doc : docs)
      {
	 User u = fromDocument(doc);
	 //u.id = null;
	 res.add(u);
      }
	 
      return res;
   }
   public static User get(String id) throws IOException
   {
      Document query = new Document();
      query.put("id", id);
      
      return fromDocument( collection().find(query).first() );
   }
   public static void delete(String id) throws IOException
   {
      Document query = new Document();
      query.put("id", id);
      
      collection().deleteOne(query);
   }
   
   public Document toDocument() throws IOException
   {
      return Document.parse( new ObjectMapper().writeValueAsString(this) );
   }
   
   public static MongoCollection<Document> collection()
   {
      return new MongoClient().getDatabase(DB).getCollection(COLLECTION);
   }
   public static User fromDocument(Document doc) throws IOException
   {
      return new ObjectMapper().readValue(doc.toJson(), User.class);
   }
   public static String newId()
   {
      String base32 = "0123456789abcdfghijklmnpqrsvwxyz";
      SecureRandom rand = new SecureRandom();
      String res = "";
      for (int i = 0; i < 32; i++)
	 res = res + base32.charAt(rand.nextInt(base32.length()));

      return res;
   }
}
