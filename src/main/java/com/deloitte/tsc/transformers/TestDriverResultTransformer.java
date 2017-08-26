package com.deloitte.tsc.transformers;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.deloitte.tsc.cdm.TestDriverResult;
import com.deloitte.tsc.cdm.TestRequest;

/**
 * This class is used to generate the output response to the Test Driver API. It
 * is implemented as a Java transformer (instead of a DTL transformation) so
 * that the Apache Math classes can be used for calculation of statistics from
 * results.
 * 
 * @author David Hunter
 * @apiviz.landmark
 * @apiviz.uses org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
 *
 */
public class TestDriverResultTransformer extends AbstractMessageTransformer {

	/**
	 * Called by Anypoint to perform the transformation. Uses the message's
	 * payload to gather the response times (milliseconds and nanoseconds) for
	 * each test result, and then uses the Apache Math library to get statistics
	 * about all of those results.
	 * 
	 * @param message
	 *            The incoming message, from which the payload is pulled, where
	 *            the array of results are initially stored.
	 * @param outputEncoding
	 *            Not used
	 * @return A Java object with the response statistics. (The Java object is
	 *         serializable to JSON, for returning from the API.)
	 */
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
		@SuppressWarnings("unchecked")
		CopyOnWriteArrayList<TestRequest> payload = (CopyOnWriteArrayList<TestRequest>)message.getPayload();

		TestDriverResult result = new TestDriverResult();
		result.setTestType(payload.get(0).getTestType());
		result.setNumIterations(payload.get(0).getNumIterations());
		
		DescriptiveStatistics milliStats = new DescriptiveStatistics();
		DescriptiveStatistics nanoStats = new DescriptiveStatistics();
		
		for(TestRequest r : payload) {
			milliStats.addValue(r.getTestResult().getCompletedInMillis());
			nanoStats.addValue(r.getTestResult().getCompletedInNanos());
		}
			
		double averageResponseMillis = milliStats.getMean();
		double averageResponseNanos = nanoStats.getMean();
		double standardDeviation = nanoStats.getPopulationVariance();
		
		result.setAverageResponseMillis(averageResponseMillis);
		result.setAverageResponseNanos(averageResponseNanos);
		result.setStandardDeviation(standardDeviation);
		return result;
	}
	
}
