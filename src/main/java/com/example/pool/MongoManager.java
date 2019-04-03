package com.example.pool;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

public class MongoManager {
	private static MongoClient mongoClient = null;

	//对mongoClient初始化
	private static void init() {
//		mongoClient = new MongoClient();
		//连接池选项
		Builder builder = new MongoClientOptions.Builder();//选项构建者	
		builder.connectTimeout(5000);//设置连接超时时间
		builder.socketTimeout(5000);//读取数据的超时时间
		builder.connectionsPerHost(30);//每个地址最大请求数		
		builder.writeConcern(WriteConcern.NORMAL);//写入策略，仅抛出网络异常
		MongoClientOptions options = builder.build();
		mongoClient = new MongoClient("127.0.0.1", options);

	}

	public static MongoDatabase getDatabase() {
		if (mongoClient == null) {
			init();
		}
		return mongoClient.getDatabase("test");
	}

}
