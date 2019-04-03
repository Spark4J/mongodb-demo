package com.example.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoAdd {
	public static void main(String[] args) {
		//获取连接
		MongoClient client = new MongoClient();
		//得到数据库
		MongoDatabase database = client.getDatabase("test");
		//得到集合封装对象
		MongoCollection<Document> collection = database.getCollection("student");
		/*// 一次插入一条记录
		Map<String, Object> map = new HashMap();
		map.put("name", "铁扇公主");
		map.put("sex", "女");
		map.put("age", 35.0);
		map.put("address", "翠云山芭蕉洞");
		Document doc = new Document(map);
		collection.insertOne(doc);//插入一条记录*/
		
		// 一次插入多条记录
		List<Document> documents = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "孙悟空");
		map.put("sex", "男");
		map.put("age", 36);
		map.put("address", "花果山水帘洞");
		documents.add(new Document(map));
		map.put("name", "牛魔王");
		map.put("sex", "男");
		map.put("age", 40);
		map.put("address", "翠云山芭蕉洞、积雷山摩云洞");
		documents.add(new Document(map));
		collection.insertMany(documents);//插入多条文档
		
	}

}
