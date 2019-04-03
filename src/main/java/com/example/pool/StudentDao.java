package com.example.pool;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class StudentDao {
	public void save(String name, String sex, double age, String address) {
		MongoDatabase database = MongoManager.getDatabase();
		MongoCollection<Document> collection = database.getCollection("student");
		Document docment = new Document();
		docment.put("name", name);
		docment.put("sex", sex);
		docment.put("age", age);
		docment.put("address", address);
		collection.insertOne(docment);
	}

}
