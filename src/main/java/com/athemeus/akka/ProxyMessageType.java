package com.athemeus.akka;

import java.lang.reflect.Method;

public class ProxyMessageType {

	Object underlyingObject;
	Class<?> returnType;
	Method methodToInvoke;
	Object[] arguments;
	
	public ProxyMessageType(Object underlyingObject, Class<?> returnType,
			Method methodToInvoke, Object[] arguments) {
		super();
		this.underlyingObject = underlyingObject;
		this.returnType = returnType;
		this.methodToInvoke = methodToInvoke;
		this.arguments = arguments;
	}
	
	public Object getUnderlyingObject() {
		return underlyingObject;
	}
	public void setUnderlyingObject(Object underlyingObject) {
		this.underlyingObject = underlyingObject;
	}
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	public Method getMethodToInvoke() {
		return methodToInvoke;
	}
	public void setMethodToInvoke(Method methodToInvoke) {
		this.methodToInvoke = methodToInvoke;
	}
	public Object[] getArguments() {
		return arguments;
	}
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
}
