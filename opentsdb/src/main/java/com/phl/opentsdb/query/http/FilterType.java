package com.phl.opentsdb.query.http;

public class FilterType {
	/**
	 * tagv的过滤规则: 精确匹配多项迭代值，多项迭代值以'|'分隔，大小写敏感
	 */
	public static String FILTER_TYPE_LITERAL_OR = "literal_or";

	/**
	 * tagv的过滤规则: 通配符匹配，大小写敏感
	 */
	public static String FILTER_TYPE_WILDCARD = "wildcard";

	/**
	 * tagv的过滤规则: 正则表达式匹配
	 */
	public static String FILTER_TYPE_REGEXP = "regexp";

	/**
	 * tagv的过滤规则: 精确匹配多项迭代值，多项迭代值以'|'分隔，忽略大小写
	 */
	public static String FILTER_TYPE_ILITERAL_OR = "iliteral_or";

	/**
	 * tagv的过滤规则: 通配符匹配，忽略大小写
	 */
	public static String FILTER_TYPE_IWILDCARD = "iwildcard";

	/**
	 * tagv的过滤规则: 通配符取非匹配，大小写敏感
	 */
	public static String FILTER_TYPE_NOT_LITERAL_OR = "not_literal_or";

	/**
	 * tagv的过滤规则: 通配符取非匹配，忽略大小写
	 */
	public static String FILTER_TYPE_NOT_ILITERAL_OR = "not_iliteral_or";

	/**
	 * tagv的过滤规则:
	 * <p/>
	 * Skips any time series with the given tag key, regardless of the value.
	 * This can be useful for situations where a metric has inconsistent tag
	 * sets. NOTE: The filter value must be null or an empty string
	 */
	public static String FILTER_TYPE_NOT_KEY = "not_key";
}
