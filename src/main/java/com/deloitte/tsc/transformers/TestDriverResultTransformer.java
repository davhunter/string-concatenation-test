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
 * @author David Hunter, Deloitte
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
		
		nanoStats = getTrimmedList(nanoStats);
		milliStats = getTrimmedList(milliStats);
			
		double averageResponseMillis = milliStats.getMean();
		double averageResponseNanos = nanoStats.getMean();
		
		result.setAverageResponseMillis(averageResponseMillis);
		result.setAverageResponseNanos(averageResponseNanos);
		return result;
	}
	
	/**
	 * Gets a "trimmed" list of values from a <code>DescriptiveStatistics</code>
	 * object. Pulls any values out of the list that are more than 1 standard
	 * deviation from the mean.
	 * 
	 * @param inputRange
	 *            The original list of values to be "trimmed"
	 * @return A new list of values, containing only those values that were
	 *         within 1 standard deviation of the original mean
	 */
	private DescriptiveStatistics getTrimmedList(DescriptiveStatistics inputRange) {
		double mean = inputRange.getMean();
		double sd = inputRange.getStandardDeviation();
		double lowerRange = mean - sd;
		double upperRange = mean + sd;
		DescriptiveStatistics ds = new DescriptiveStatistics();
		
		for(int i = 0; i < inputRange.getN(); i++) {
			double value = inputRange.getElement(i);
			if(value > lowerRange && value < upperRange) {
				ds.addValue(value);
			}
		}
		
		return ds;
	}
	
}
