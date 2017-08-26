package com.deloitte.tsc.cdm;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * This POJO represents a test to be executed, as well as the data returned back from that test.
 * 
 * @author David Hunter, Deloitte
 * @apiviz.landmark
 * @apiviz.uses org.codehaus.jackson.annotate.JsonAutoDetect
 */
@JsonAutoDetect
public class TestRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String testType;
	private int numIterations;
	private TestResult testResult;

	public TestRequest(String testType, int numIterations, TestResult testResult) {
		super();
		this.testType = testType;
		this.numIterations = numIterations;
		this.testResult = testResult;
	}

	public TestRequest() {
		this.testType = "";
		this.numIterations = 0;
		this.testResult = null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestRequest [");
		if (testType != null) {
			builder.append("testType=");
			builder.append(testType);
			builder.append(", ");
		}
		builder.append("numIterations=");
		builder.append(numIterations);
		builder.append(", ");
		if (testResult != null) {
			builder.append("testResult=");
			builder.append(testResult);
		}
		builder.append("]");
		return builder.toString();
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public int getNumIterations() {
		return numIterations;
	}

	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
}
