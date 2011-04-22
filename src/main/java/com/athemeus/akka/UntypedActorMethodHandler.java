package com.athemeus.akka;

import java.lang.reflect.Method;

import scala.Function1;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dispatch.Future;
import akka.japi.Procedure;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import static akka.actor.Actors.*;

public class UntypedActorMethodHandler implements MethodHandler {

private Object underlyingObject;

private long timeout = 5000;

	public UntypedActorMethodHandler(Object underlyingObject)
	{
		//System.out.println("UNDERLYING CLASS")
		this.underlyingObject = underlyingObject;
	}
	
	public UntypedActorMethodHandler(Object underlyingObject, long timeout)
	{
		this(underlyingObject);
		this.timeout = timeout;
	}
//	
	@Override
	public Object invoke(Object self, Method m, Method proceed, Object[] args)
			throws Throwable {		
		final ActorRef actor = actorOf(UntypedProxyActor.class).start();
		actor.setTimeout(timeout);
		Class<?> returnType = m.getReturnType();
		ProxyMessageType proxyMessage = new ProxyMessageType(underlyingObject, returnType, m, args);
		if(returnType.equals(Void.TYPE))
		{
			//fire and forget!
			actor.sendOneWay(proxyMessage);			
		}else{
			Future future = actor.sendRequestReplyFuture(proxyMessage);
			//TODO: cache proxy factories			
			ProxyFactory f = new ProxyFactory();
			f.setSuperclass(returnType);
			Class c = f.createClass();
			Object returnObj = c.newInstance();
			((ProxyObject)returnObj).setHandler(new FutureMethodHandler(future));
			return returnObj;
		}
		return Void.TYPE;
	}

}
