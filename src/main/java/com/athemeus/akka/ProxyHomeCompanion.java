package com.athemeus.akka;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import akka.actor.UntypedActor;
import akka.dispatch.Future;

public class ProxyHomeCompanion {
	
	Class classToActorize;
	Object actorizedObject;
	private ProxyFactory f;
	
	public Class getClassToActorize() {
		return classToActorize;
	}

	public void setClassToActorize(Class classToActorize) {
		this.classToActorize = classToActorize;
	}

	public Object getActorizedObject() {
		return actorizedObject;
	}

	public void setActorizedObject(Object actorizedObject) {
		this.actorizedObject = actorizedObject;
	}
	
	public Object create() throws Exception
	{
		if(f == null)
		{
			 f = new ProxyFactory();
			 f.setSuperclass(classToActorize);
		}
		Class c = f.createClass();
		MethodHandler mi = new UntypedActorMethodHandler(actorizedObject);
		Object returnObj = c.newInstance();		
		((ProxyObject)returnObj).setHandler(mi);
		return returnObj;
	}
	
}
