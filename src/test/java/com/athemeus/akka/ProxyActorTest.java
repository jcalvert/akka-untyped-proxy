package com.athemeus.akka;
import java.util.HashMap;

import org.junit.Test;

import com.athemeus.akka.IMyStuffDao;
import com.athemeus.akka.MyType;
import com.athemeus.akka.ProxyHomeCompanion;
import com.athemeus.akka.MyStuffDaoImpl;

import akka.actor.*;

public class ProxyActorTest {
	
	//@Test
	public void testActorProxy() throws Exception
	{
		MyStuffDaoImpl impl = new MyStuffDaoImpl();
		MyType myType = new MyType();
		impl.save(myType);
		impl.loadData(new HashMap<String,String>());
		MyType myType2 = new MyType();
		impl.save(myType2);
		System.out.println("TYPE 1 START "+myType.getModifiedTime());
		System.out.println("TYPE 2 START "+myType2.getModifiedTime());
	}
	
	@Test
	public void testActorProxy2() throws Exception
	{		
		ProxyHomeCompanion companion = new ProxyHomeCompanion();
		companion.setClassToActorize(MyStuffDaoImpl.class);
		companion.setActorizedObject(new MyStuffDaoImpl());
		IMyStuffDao impl = (IMyStuffDao) companion.create();
		MyType myType = new MyType();
		MyType returnedMytype = impl.save(myType);
		impl.loadData(new HashMap<String,String>());
		MyType myType2 = new MyType();
		MyType returnedMytype2	= impl.save(myType2);
		System.out.println("About to access ");
		System.out.println("TYPE 1 START "+returnedMytype.getModifiedTime()+" "+System.currentTimeMillis());
		System.out.println("TYPE 2 START "+returnedMytype2.getModifiedTime());
	}
}