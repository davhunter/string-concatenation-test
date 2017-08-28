package com.deloitte.tsc.cdm;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * This POJO class is used to represent the result to be returned back from the
 * Test Driver API.
 * 
 * @author David Hunter, Deloitte
 *
 * @apiviz.landmark
 * @apiviz.uses org.codehaus.jackson.annotate.JsonAutoDetect
 */
@JsonAutoDetect
public class TestDriverResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String testType;
	private double averageResponseMillis;
	private double averageResponseNanos;
	private int numIterations;

	public TestDriverResult(String testType, float averageResponseMillis, float averageResponseNanos,
			int numIterations) {
		super();
		this.testType = testType;
		this.averageResponseMillis = averageResponseMillis;
		this.averageResponseNanos = averageResponseNanos;
		this.numIterations = numIterations;
	}

	public TestDriverResult() {
		this.testType = "";
		this.numIterations = 0;
		this.averageResponseMillis = this.averageResponseNanos = 0.0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestDriverResult [testType=");
		builder.append(testType);
		builder.append(", averageResponseMillis=");
		builder.append(averageResponseMillis);
		builder.append(", averageResponseNanos=");
		builder.append(averageResponseNanos);
		builder.append(", numIterations=");
		builder.append(numIterations);
		builder.append("]");
		return builder.toString();
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public double getAverageResponseMillis() {
		return averageResponseMillis;
	}

	public void setAverageResponseMillis(double averageResponseMillis) {
		this.averageResponseMillis = averageResponseMillis;
	}

	public double getAverageResponseNanos() {
		return averageResponseNanos;
	}

	public void setAverageResponseNanos(double averageResponseNanos) {
		this.averageResponseNanos = averageResponseNanos;
	}

	public int getNumIterations() {
		return numIterations;
	}

	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}
}
