package com.phl.opentsdb;

import com.stumbleupon.async.Callback;
import com.stumbleupon.async.Deferred;
import net.opentsdb.core.TSDB;
import net.opentsdb.uid.NoSuchUniqueName;
import net.opentsdb.uid.UniqueId.UniqueIdType;
import net.opentsdb.utils.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddDataUtil {
	public static String pathToConfigFile = AddDataUtil.class.getClassLoader().getResource("opentsdb.conf").getPath();
	private static Logger LOG = LoggerFactory.getLogger(AddDataUtil.class);

	public static void addData(String metricName, List<TSDBDataPoint> datas) throws Exception {
		LOG.debug("opentsdb.conf pathToConfigFile:" + pathToConfigFile);
		final Config config;
		if (pathToConfigFile != null && !pathToConfigFile.isEmpty()) {
			config = new Config(pathToConfigFile);
		} else {
			// Search for a default config from /etc/opentsdb/opentsdb.conf,
			// etc.
			config = new Config(true);
		}
		final TSDB tsdb = new TSDB(config);
		@SuppressWarnings("unused")
		byte[] byteMetricUID;
		try {
			byteMetricUID = tsdb.getUID(UniqueIdType.METRIC, metricName);
		} catch (IllegalArgumentException iae) {
			System.out.println("Metric name not valid.");
			iae.printStackTrace();
			System.exit(1);
		} catch (NoSuchUniqueName nsune) {
			// If not, great. Create it.
			byteMetricUID = tsdb.assignUid("metric", metricName);
		}
		LOG.debug("prepare to insert datas...");
		long startTime1 = System.currentTimeMillis();
		int n = datas.size();
		
		ArrayList<Deferred<Object>> deferreds = new ArrayList<Deferred<Object>>(n);
		
		for (TSDBDataPoint tsdbDataPoint : datas) {
			Deferred<Object> deferred = tsdb.addPoint(metricName, tsdbDataPoint.getTimestamp(),
					tsdbDataPoint.getValue(), tsdbDataPoint.getTags());
			deferreds.add(deferred);
		}
		
		LOG.debug("Waiting for deferred result to return...");
		Deferred.groupInOrder(deferreds).addErrback(new AddDataUtil().new errBack())
				.addCallback(new AddDataUtil().new succBack()).join();
		long elapsedTime1 = System.currentTimeMillis() - startTime1;
		LOG.debug("\nAdding " + n + " points took: " + elapsedTime1 + " milliseconds.\n");

		// Gracefully shutdown connection to TSDB. This is CRITICAL as it will
		// flush any pending operations to HBase.
		tsdb.shutdown().join();
	}

	class errBack implements Callback<String, Exception> {
		public String call(final Exception e) throws Exception {
			String message = ">>>>>>>>>>>Failure!>>>>>>>>>>>";
			System.err.println(message + " " + e.getMessage());
			e.printStackTrace();
			return message;
		}
	};

	// This is an optional success callback to handle when there is a success.
	class succBack implements Callback<Object, ArrayList<Object>> {
		public Object call(final ArrayList<Object> results) {
			System.out.println("Successfully wrote " + results.size() + " data points");
			return null;
		}
	};
}
