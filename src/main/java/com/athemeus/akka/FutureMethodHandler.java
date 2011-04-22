package com.athemeus.akka;

import java.lang.reflect.Method;

import akka.dispatch.Future;

import javassist.util.proxy.MethodHandler;

public class FutureMethodHandler implements MethodHandler {

	Future future;
	
	public FutureMethodHandler(Future future)
	{
		this.future = future; 
	}
	
	@Override
	public Object invoke(Object self, Method m, Method proceed, Object[] args)
			throws Throwable {
			return m.invoke(future.await().result().get(), args);
	}

}
