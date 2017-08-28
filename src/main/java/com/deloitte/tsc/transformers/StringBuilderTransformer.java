package com.deloitte.tsc.transformers;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transport.PropertyScope;

/**
 * This class is used for performing a transformation in which a string is
 * concatenated to over and over again (with the number of iterations specified
 * by the <code>numIterations</code> flow variable), via the
 * <code>StringBuilder</code> class.
 * 
 * @author David Hunter, Deloitte
 * @apiviz.landmark
 * @apiviz.uses java.lang.StringBuilder
 */
public class StringBuilderTransformer extends AbstractConcatenationTransformer {

	/**
	 * Called by Anypoint to perform a transformation; no real data is used, a
	 * string is just concatenated over and over.
	 * 
	 * @param message
	 *            The incoming Anypoint message; not used
	 * @param outputEncoding
	 *            Not used
	 * 
	 * @return A string containing the concatenated string, which is not used by
	 *         the calling flow
	 */
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		StringBuilder returnString = new StringBuilder();
		int numIterations = (Integer)message.getProperty("numIterations", PropertyScope.INVOCATION);
				
		for(int i = 0; i < numIterations; i++) {
			returnString.append(this.messageToAppend);
		}
		
		return returnString.toString();
	}

}
