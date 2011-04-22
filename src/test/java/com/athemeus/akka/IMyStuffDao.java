package com.athemeus.akka;

import java.util.Map;

public interface IMyStuffDao {

	public MyType save(MyType myType) throws Exception;
	
	public void loadData(Map<String,String> dataMap) throws Exception;
	
}
