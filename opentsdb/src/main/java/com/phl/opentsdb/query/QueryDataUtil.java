package com.phl.opentsdb.query;

import com.phl.opentsdb.AddDataUtil;
import com.stumbleupon.async.Callback;
import com.stumbleupon.async.Deferred;
import net.opentsdb.core.DataPoints;
import net.opentsdb.core.Query;
import net.opentsdb.core.TSDB;
import net.opentsdb.core.TSQuery;
import net.opentsdb.utils.Config;
import net.opentsdb.utils.DateTime;

import java.io.IOException;
import java.util.ArrayList;

public class QueryDataUtil {
    private static String pathToConfigFile = AddDataUtil.pathToConfigFile;

    public static ArrayList<DataPoints[]> queryData(final TSQuery query) throws IOException {
        // Create a config object with a path to the file for parsing. Or manually
        // override settings.
        // e.g. config.overrideConfig("tsd.storage.hbase.zk_quorum", "localhost");
        final Config config;
        if (pathToConfigFile != null && !pathToConfigFile.isEmpty()) {
            config = new Config(pathToConfigFile);
        } else {
            // Search for a default config from /etc/opentsdb/opentsdb.conf, etc.
            config = new Config(true);
        }
        final TSDB tsdb = new TSDB(config);

        query.validateAndSetQuery();

        Query[] tsdbqueries = query.buildQueries(tsdb);

        final int nqueries = tsdbqueries.length;
        final ArrayList<DataPoints[]> results = new ArrayList<DataPoints[]>(
                nqueries);
        final ArrayList<Deferred<DataPoints[]>> deferreds =
                new ArrayList<Deferred<DataPoints[]>>(nqueries);

        for (int i = 0; i < nqueries; i++) {
            deferreds.add(tsdbqueries[i].runAsync());
        }

        // Start timer
        long startTime = DateTime.nanoTime();

        class QueriesCB implements Callback<Object, ArrayList<DataPoints[]>> {
            public Object call(final ArrayList<DataPoints[]> queryResults)
                    throws Exception {
                results.addAll(queryResults);
                return null;
            }
        }

        class QueriesEB implements Callback<Object, Exception> {
            @Override
            public Object call(final Exception e) throws Exception {
                System.err.println("Queries failed");
                e.printStackTrace();
                return null;
            }
        }

        try {
            Deferred.groupInOrder(deferreds)
                    .addCallback(new QueriesCB())
                    .addErrback(new QueriesEB())
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // End timer.
        double elapsedTime = DateTime.msFromNanoDiff(DateTime.nanoTime(), startTime);
        System.out.println("Query returned in: " + elapsedTime + " milliseconds.");

        // Gracefully shutdown connection to TSDB
        try {
            tsdb.shutdown().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;

    }

}
