package com.phl.opentsdb.query.dto;

import net.opentsdb.core.TSSubQuery;
import net.opentsdb.stats.QueryStats;

import java.util.ArrayList;
import java.util.HashMap;

public class OpenTSQuery {
	/** User given start date/time, could be relative or absolute */
	private String start;

	/** User given end date/time, could be relative, absolute or empty */
	private String end;

	/** User's timezone used for converting absolute human readable dates */
	private String timezone;

	/** Options for serializers, graphs, etc */
	private HashMap<String, ArrayList<String>> options;

	/**
	 * Whether or not to include padding, i.e. data to either side of the start/
	 * end dates
	 */
	private boolean padding;

	/** Whether or not to suppress annotation output */
	private boolean no_annotations;

	/** Whether or not to scan for global annotations in the same time range */
	private boolean with_global_annotations;

	/** Whether or not to show TSUIDs when returning data */
	private boolean show_tsuids;

	/** A list of parsed sub queries, must have one or more to fetch data */
	private ArrayList<TSSubQuery> queries;

	/** Whether or not the user wasn't millisecond resolution */
	private boolean ms_resolution;

	/** Whether or not to show the sub query with the results */
	private boolean show_query;

	/** Whether or not to include stats in the output */
	private boolean show_stats;

	/** Whether or not to include stats summary in the output */
	private boolean show_summary;

	/** Whether or not to delete the queried data */
	private boolean delete = false;

	/**
	 * A flag denoting whether or not to align intervals based on the calendar
	 */
	private boolean use_calendar;

	/** The query status for tracking over all performance of this query */
	private QueryStats query_stats;
}
