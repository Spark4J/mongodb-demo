package com.example.crud;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDel {
	public static void main(String[] args) {
		//获取连接
		MongoClient client=new MongoClient();
		//得到数据库
		MongoDatabase database = client.getDatabase("test");
		//得到集合封装对象
		MongoCollection<Document> collection = database.getCollection("student");
		
//		BasicDBObject bson=new BasicDBObject("name", "牛魔王");
		BasicDBObject bson=new BasicDBObject("age", new BasicDBObject("$gte",40));
		collection.deleteOne(bson);//删除记录（符合条件的第一条记录）
		//collection.deleteMany(bson);//删除符合条件的全部记录

	}

}
