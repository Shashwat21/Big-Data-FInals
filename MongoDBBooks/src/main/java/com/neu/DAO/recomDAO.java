package com.neu.DAO;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.neu.pojo.RecomPOJO;
import com.neu.pojo.UsersPOJO;

public class recomDAO {

	public MongoClient client;
	public MongoDatabase mongoDB;
	public MongoCollection<Document> recomCollection;
	
	public recomDAO(){
		client = new MongoClient();
		mongoDB = client.getDatabase("AmazonReviewsTitles");
		recomCollection = mongoDB.getCollection("recommendations");
	}
	public ArrayList<RecomPOJO> getRecommendation(String searchVar){
		Document query = new Document();
		query.put("UserID", Integer.parseInt(searchVar));
		FindIterable<Document> result = recomCollection.find(query);
		MongoCursor<Document> cursor = result.iterator();
		ArrayList<RecomPOJO> recoms = new ArrayList<RecomPOJO>();
		System.out.println("Cursor Value:"+cursor);
			while (cursor.hasNext()){
				RecomPOJO recP = new RecomPOJO();				
				Document document = cursor.next();
				recP.setUserID(document.get("UserID").toString());
				recP.setRecommendations(document.get("Recommendations").toString());
				recoms.add(recP);
			}
		cursor.close();
		return recoms;		
	}
}
