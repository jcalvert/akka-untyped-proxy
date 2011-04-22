package com.athemeus.akka;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class MyStuffDaoImpl implements IMyStuffDao {

	public MyType save(MyType myType) throws Exception {
		busyWait("save ");
		myType.setModifiedTime(System.currentTimeMillis());
		return myType;
	}

	public void loadData(Map<String, String> dataMap) throws Exception {
		busyWait("loadData ");
	}
	
	private void busyWait(String name) throws InterruptedException
	{
		String processId = UUID.randomUUID().toString();
		System.out.println(name+" with ID "+processId+" starting at "+System.currentTimeMillis());
		Random random = new Random();
		int time = random.nextInt(2000); 
		Thread.currentThread().sleep(time);
		System.out.println(name+" with ID "+processId+" completed at "+System.currentTimeMillis());
	}

}
