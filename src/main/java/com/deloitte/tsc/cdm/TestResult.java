package com.deloitte.tsc.cdm;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * This POJO represents the data returned back from a call to the Test API.
 * 
 * @author David Hunter, Deloitte
 * @apiviz.landmark
 * @apiviz.uses org.codehaus.jackson.annotate.JsonAutoDetect
 */
@JsonAutoDetect
public class TestResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String testType;
	private int completedInMillis;
	private int completedInNanos;
	private int numIterations;

	public TestResult(String testType, int completedInMillis, int completedInNanos, int numIterations) {
		super();
		this.testType = testType;
		this.completedInMillis = completedInMillis;
		this.completedInNanos = completedInNanos;
		this.numIterations = numIterations;
	}

	public TestResult() {
		this.testType = "";
		this.completedInMillis = this.completedInNanos = this.numIterations = 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestResult [testType=");
		builder.append(testType);
		builder.append(", completedInMillis=");
		builder.append(completedInMillis);
		builder.append(", completedInNanos=");
		builder.append(completedInNanos);
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

	public int getCompletedInMillis() {
		return completedInMillis;
	}

	public void setCompletedInMillis(int completedInMillis) {
		this.completedInMillis = completedInMillis;
	}

	public int getCompletedInNanos() {
		return completedInNanos;
	}

	public void setCompletedInNanos(int completedInNanos) {
		this.completedInNanos = completedInNanos;
	}

	public int getNumIterations() {
		return numIterations;
	}

	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}
}
