package com.neu.DAO;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.neu.pojo.UsersPOJO;

public class usersDAO {

	public MongoClient client;
	public MongoDatabase mongoDB;
	public MongoCollection<Document> usersCollection;
	
	public usersDAO(){
		client = new MongoClient();
		mongoDB = client.getDatabase("AmazonReviewsTitles");
		usersCollection = mongoDB.getCollection("users");
	}
	public ArrayList<UsersPOJO> searchByKeyword(String searchVar){
		Document query = new Document();
		query.put("UserID", Integer.parseInt(searchVar));
		FindIterable<Document> result = usersCollection.find(query);
		MongoCursor<Document> cursor = result.iterator();
		ArrayList<UsersPOJO> users = new ArrayList<UsersPOJO>();
		System.out.println("Cursor Value:"+cursor);
			while (cursor.hasNext()){
				UsersPOJO userP = new UsersPOJO();				
				Document document = cursor.next();
				userP.setAge(document.get("Age").toString());
				userP.setLocation(document.get("Location").toString());
				userP.setUserID(document.get("UserID").toString());
				users.add(userP);
			}
		cursor.close();
		return users;		
	}
}
