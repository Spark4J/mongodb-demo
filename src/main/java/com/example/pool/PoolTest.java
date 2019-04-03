package com.example.pool;

import org.junit.Test;

public class PoolTest {
	@Test
	public void test01() {
		long startTime = System.currentTimeMillis();

		StudentDao studentDao = new StudentDao();
		for (int i = 0; i < 20000; i++) {
			studentDao.save("测试" + i, "男", 25.0, "测试地址" + i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("完成时间："+(endTime-startTime)+"毫秒");//完成时间：7225毫秒
		// 更改init之后 完成时间：4374毫秒
	}

}
