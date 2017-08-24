package com.phl.opentsdb.query.http;


import com.phl.opentsdb.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opentsdb.client.ExpectResponse;
import org.opentsdb.client.HttpClient;
import org.opentsdb.client.HttpClientImpl;
import org.opentsdb.client.request.Filter;
import org.opentsdb.client.request.Query;
import org.opentsdb.client.request.QueryBuilder;
import org.opentsdb.client.request.SubQueries;
import org.opentsdb.client.response.SimpleHttpResponse;

import static com.phl.opentsdb.DateUtil.YYYY_MM_DD_HH_MM_SS;

/**
 * 版权所有：awifi </br>
 * 创建日期: 2017年8月5日 下午7:07:34</br>
 * 创建作者： Dingxc </br>
 * 文件名称：OpenTsdbHttpQueryClient.java </br>
 * 版本：v1.0 </br>
 * 功能：opentsdb 单个监控项查询工具类(因github例子出现时间查询问题，所以新增这个查询类) </br>
 *
 */
public class OpenTsdbHttpQueryClient {
	private static final Logger LOG = LoggerFactory.getLogger(OpenTsdbHttpQueryClient.class);
	private HttpClient httpClient;

	/** 构造函数 */
	public OpenTsdbHttpQueryClient(String opentsdbUrl) {
		this.httpClient = new HttpClientImpl(opentsdbUrl);
	}
	
	/**
	 * 单个过滤条件 查询 
	 * @param metric 监控项 ，必填
	 * @param startTime 开始时间，必填，格式：yyyy-MM-dd HH:mm:ss
	 * @param endTime 结束时间，选填，格式：yyyy-MM-dd HH:mm:ss
	 * @param tagk tagk
	 * @param tagvType 过滤类型
	 * @param tagvFilter tagv
	 * @param groupBy 是否group_by，选填，默认true           
	 * @param aggregator 聚合函数
	 * @param downsample 采样率
	 * @return json 格式字符串
	 * @throws IOException
	 */
	public String queryData(String metric, String startTime, String endTime, String tagk, String tagvType,
			String tagvFilter, Boolean groupBy, String aggregator, String downsample) throws IOException {
		if (StringUtil.isEmptyOrNull(metric)) {
			throw new IllegalArgumentException("Param 'metric' must not be null");
		}
		if (StringUtil.isEmptyOrNull(startTime)) {
			throw new IllegalArgumentException("Param 'startTime' must not be null");
		}
		QueryBuilder queryBuilder = QueryBuilder.getInstance();
		Query query = queryBuilder.getQuery();
		
		query.setStart(parseToDate(startTime, YYYY_MM_DD_HH_MM_SS).getTime() / 1000);
		if (StringUtil.isNotEmptyAndNull(endTime)) {
			query.setEnd(parseToDate(endTime, YYYY_MM_DD_HH_MM_SS).getTime() / 1000);
		}

		List<SubQueries> sqList = new ArrayList<SubQueries>();
		SubQueries sq = new SubQueries();
		sq.setMetric(metric);
		sq.setAggregator(aggregator);

		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = new Filter();
		filter.setTagk(tagk);
		filter.setType(tagvType);
		filter.setFilter(tagvFilter);
		filter.setGroupBy(groupBy != null ? groupBy : true);
		filters.add(filter);

		sq.setFilters(filters);
		if (StringUtil.isNotEmptyAndNull(downsample)) {
			sq.setDownsample(downsample);
		}
		sqList.add(sq);
		query.setQueries(sqList);

		LOG.debug("query request：{}", queryBuilder.build()); // 这行起到校验作用
		SimpleHttpResponse spHttpResponse = httpClient.pushQueries(queryBuilder, ExpectResponse.DETAIL);
		LOG.debug("response.content: {}", spHttpResponse.getContent());

		if (spHttpResponse.isSuccess()) {
			return spHttpResponse.getContent();
		}
		return null;
	}
	
	/**
	 * 多个过滤条件查询
	 * @param metric 监控项，必填
	 * @param startTime 开始时间，必填
	 * @param endTime 结束时间，选填
	 * @param filters 过滤器列表，必填
	 * @param aggregator 聚合函数，必填
	 * @param downsample 向下采样率，选填
	 * @return
	 * @throws IOException
	 */
	public String queryData(String metric, String startTime, String endTime, List<Filter> filters, String aggregator,
			String downsample) throws IOException {

		if (StringUtil.isEmptyOrNull(metric)) {
			throw new IllegalArgumentException("Param 'metric' must not be null");
		}
		if (StringUtil.isEmptyOrNull(startTime)) {
			throw new IllegalArgumentException("Param 'startTime' must not be null");
		}

		QueryBuilder queryBuilder = QueryBuilder.getInstance();
		Query query = queryBuilder.getQuery();

		query.setStart(parseToDate(startTime, YYYY_MM_DD_HH_MM_SS).getTime() / 1000);
		if (StringUtil.isNotEmptyAndNull(endTime)) {
			query.setEnd(parseToDate(endTime, YYYY_MM_DD_HH_MM_SS).getTime() / 1000);
		}
		List<SubQueries> sqList = new ArrayList<SubQueries>();
		SubQueries sq = new SubQueries();
		sq.setMetric(metric);
		sq.setAggregator(aggregator);
		sq.setFilters(filters);
		if (StringUtil.isNotEmptyAndNull(downsample)) {
			sq.setDownsample(downsample);
		}
		sqList.add(sq);
		query.setQueries(sqList);

		LOG.debug("query request：{}", queryBuilder.build()); // 这行起到校验作用
		SimpleHttpResponse spHttpResponse = httpClient.pushQueries(queryBuilder, ExpectResponse.DETAIL);
		LOG.debug("response.content: {}", spHttpResponse.getContent());

		if (spHttpResponse.isSuccess()) {
			return spHttpResponse.getContent();
		}
		return null;
	}

	private static Date parseToDate(String s, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
}
