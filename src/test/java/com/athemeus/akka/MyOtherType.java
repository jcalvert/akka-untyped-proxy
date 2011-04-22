package com.athemeus.akka;

public class MyOtherType {

	long createTime;
	
	public MyOtherType(){
		createTime = System.currentTimeMillis();
	}

	public MyOtherType(int i) {
			try {
				Thread.currentThread().sleep(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("I just slept "+i+" before creation.");
	}
}
