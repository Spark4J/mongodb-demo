package com.example.crud;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoFind {
	/**
	 * 查询全部
	 * @param args
	 */
	public static void main(String[] args) {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合

		System.out.println("begin");
		FindIterable<Document> list = collection.find();//获取文档集合
		for (Document doc : list) {//遍历集合中的文档输出数据
			System.out.println("name:" + doc.getString("name"));
			System.out.println("sex:" + doc.getString("sex"));
			try {
				System.out.println("age:" + doc.getDouble("age"));//默认为浮点型
			} catch (Exception e) {
				System.out.println("age:" + doc.get("age"));
			}
			System.out.println("address:" + doc.getString("address"));
			System.out.println("--------------------------");
		}
		System.out.println("end");
	}
	
	/**
	 * 匹配查询
	 */
	@Test
	public void test01() {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合
		
		//构建查询条件
		BasicDBObject bson=new BasicDBObject("name", "孙悟空");		
		FindIterable<Document> list = collection.find(bson);//获取文档集合
		for (Document document : list) {
			System.out.println(document);
		}
	}
	
	/**
	 * 模糊查询
	 */
	@Test
	public void test02() {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合
		
		//构建查询条件
		Pattern queryPattern = Pattern.compile("^.*洞.*$");
		BasicDBObject bson=new BasicDBObject("address", queryPattern);		
		FindIterable<Document> list = collection.find(bson);//获取文档集合
		for (Document document : list) {
			System.out.println(document);
		}
	}
	
	/**
	 * 大于小于
	 */
	@Test
	public void test03() {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合
		
		//查询年龄小于38的		
		BasicDBObject bson=new BasicDBObject("age", new BasicDBObject("$lt",38));
		FindIterable<Document> list = collection.find(bson);//获取文档集合
		for (Document document : list) {
			System.out.println(document);
		}
	}
	
	/**
	 * 条件连接 $and
	 */
	@Test
	public void test04() {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合
		
		//查询年龄小于38的 大于35的		
		BasicDBObject bson1 = new BasicDBObject("age", new BasicDBObject("$lt", 38));
		BasicDBObject bson2 = new BasicDBObject("age", new BasicDBObject("$gt", 35));
		BasicDBObject bson = new BasicDBObject("$and", Arrays.asList(bson1, bson2));
		FindIterable<Document> list = collection.find(bson);//获取文档集合
		for (Document document : list) {
			System.out.println(document);
		}
	}
	
	/**
	 * 条件连接 $or
	 */
	@Test
	public void test05() {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合
		
		//查询年龄小于38的 大于35的		
		BasicDBObject bson1 = new BasicDBObject("age", new BasicDBObject("$lt", 38));
		BasicDBObject bson2 = new BasicDBObject("sex", "女");
		BasicDBObject bson = new BasicDBObject("$or", Arrays.asList(bson1, bson2));
		FindIterable<Document> list = collection.find(bson);//获取文档集合
		for (Document document : list) {
			System.out.println(document);
		}
	}
	//分页查询
	@Test
	public void test06() {
		MongoClient client = new MongoClient();//创建连接对象
		MongoDatabase database = client.getDatabase("test");//获取数据库		
		MongoCollection<Document> collection = database.getCollection("student");//获取集合
		FindIterable<Document> list = collection.find();
		list.skip(1);
		list.limit(1);
		System.out.println(collection.count());//40003
	}

}
