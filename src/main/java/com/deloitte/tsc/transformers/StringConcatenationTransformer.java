package com.deloitte.tsc.transformers;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;
import org.mule.transformer.AbstractMessageTransformer;

public class StringConcatenationTransformer extends AbstractMessageTransformer {
	
	private static final String MESSAGE = "ABCDEFGHIJ";

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		String returnString = "";
		int numIterations = (Integer)message.getProperty("numIterations", PropertyScope.INVOCATION);
				
		for(int i = 0; i < numIterations; i++) {
			returnString += MESSAGE;
		}
		
		return returnString;
	}

}
