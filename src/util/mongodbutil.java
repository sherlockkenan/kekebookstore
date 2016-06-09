package util;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class mongodbutil {
     @SuppressWarnings("resource")
	static public DB getdb(){
    	 @SuppressWarnings("deprecation")
		Mongo mongo = new Mongo();
    	 @SuppressWarnings("deprecation")
		DB db = mongo.getDB("kekebookstore");
    
         System.out.println("Connect to database successfully");  
         return db;
     }
}
