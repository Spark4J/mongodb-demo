package com.example.crud;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoUpdate {
	public static void main(String[] args) {
		//获取连接
		MongoClient client = new MongoClient();
		//得到数据库
		MongoDatabase database = client.getDatabase("test");
		//得到集合封装对象
		MongoCollection<Document> collection = database.getCollection("student");

		//修改的条件
		BasicDBObject filter = new BasicDBObject("name", "铁扇公主");
		//修改后的值
		BasicDBObject update = new BasicDBObject("$set",
				new BasicDBObject("name", "玉面狐狸").append("address", "积雷山摩云洞").append("age", 18));
		//参数1：修改条件  参数2：修改后的值
		collection.updateOne(filter, update);
		//collection.updateMany(filter, update);//修改符合条件的所有记录

	}

}
