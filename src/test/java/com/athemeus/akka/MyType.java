package com.athemeus.akka;

public class MyType {

	long createTime;
	long modifiedTime;
	
	public MyType(){
		createTime = System.currentTimeMillis();
	}

	
	public MyType(int i) {
			super();
			try {
				Thread.currentThread().sleep(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("I just slept "+i+" before creation.");
	}

	public long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
