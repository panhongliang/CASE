package com.phl.opentsdb;

import java.io.Serializable;
import java.util.Map;

public class TSDBDataPoint implements Serializable{
	
	private static final long serialVersionUID = 5620151055344215029L;
	
	//public String metricName;
	/**建议10位，精确到秒的时间，不推荐使用毫秒，超过13位报错*/
	private long timestamp;
	private long value;
	private Map<String, String> tags;
	
	public TSDBDataPoint(long timestamp, long value) {
		super();
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public TSDBDataPoint(long timestamp, long value, Map<String, String> tags) {
		super();
		this.timestamp = timestamp;
		this.value = value;
		this.tags = tags;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

}
