package com.deloitte.tsc.transformers;

import org.mule.transformer.AbstractMessageTransformer;

/**
 * Abstract class used by all of the concatenation transformers, for the purpose
 * of centralizing common code.
 * 
 * @author David Hunter, Deloitte
 * @apiviz.landmark
 */
public abstract class AbstractConcatenationTransformer extends AbstractMessageTransformer {
	/**
	 * The message to be concatenated (over and over). Externalized into a
	 * property file, so that different tests (based on different texts) can
	 * easily be performed.
	 */
	protected String messageToAppend;

	public String getMessageToAppend() {
		return messageToAppend;
	}

	public void setMessageToAppend(String messageToAppend) {
		this.messageToAppend = messageToAppend;
	}

}
