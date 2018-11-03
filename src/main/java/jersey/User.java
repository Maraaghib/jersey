package jersey;

import java.util.*;
import java.io.*;

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
   
   public String firstname;
   public String lastname;

   public void push() throws IOException
   {
      MongoClient mc = new MongoClient();
      MongoDatabase db = mc.getDatabase(DB);
      MongoCollection<Document> collection = db.getCollection(COLLECTION);

      ObjectMapper mapper = new ObjectMapper();
      String       str    = mapper.writeValueAsString(this);
      Document     doc    = Document.parse(str);
      collection.insertOne(doc);
   }
   
   public static List<User> list() throws IOException
   {
      MongoClient mc = new MongoClient();
      MongoDatabase db = mc.getDatabase(DB);
      MongoCollection<Document> collection = db.getCollection(COLLECTION);

      ObjectMapper mapper = new ObjectMapper();
      List<Document> docs = (List<Document>) collection.find().into(new ArrayList<Document>());
      List<User> res = new ArrayList<>();
      for (Document doc : docs)
	 res.add( mapper.readValue(doc.toJson(), User.class) );
	 
      return res;
   }   
}
