package com.athemeus.akka;

import scala.Function1;
import akka.actor.UntypedActor;
import akka.dispatch.Future;

public class UntypedProxyActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if(!(message instanceof ProxyMessageType))
			throw new IllegalArgumentException("Unknown message: " + message);
		ProxyMessageType proxyMessage = (ProxyMessageType)message;
		if(getContext().getSenderFuture().isDefined())
		{	
			getContext().getSenderFuture().get()
				.completeWithResult(proxyMessage.getMethodToInvoke().invoke(proxyMessage.getUnderlyingObject(),
						proxyMessage.getArguments()[0]));
		}else{
			proxyMessage.getMethodToInvoke().invoke(proxyMessage.getUnderlyingObject(), proxyMessage.getArguments());
		}
	}

}
