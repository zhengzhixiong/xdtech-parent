package com.xdtech.core.dao;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xdtech.core.config.PropertiesConfigurer;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.PageRequest;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 封装的jdbcdao类
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2015-1-20 上午10:35:11
 */
@Repository
public class JdbcDao
{
	private Logger logger = LoggerFactory.getLogger(JdbcDao.class);
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public Page excuteQuerySqlByJdbc(PageRequest pageRequest,String configSql, Map<String, String> parameters)
	{

		
		Configuration configuration = new Configuration();
		configuration.setNumberFormat("#");
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		String key = UUID.randomUUID().toString();
		stringLoader.putTemplate(key, configSql);
		configuration.setTemplateLoader(stringLoader);
		StringWriter stringWriter = new StringWriter();
		
		try
		{
			Template template = new Template(key,new StringReader(configSql),configuration);
			template.process(parameters, stringWriter); 
		}
		catch (Exception e)
		{
			logger.error("处理JDBCDAO查询参数模板时发生错误：{}",e.toString());
			throw new RuntimeException(e);
		}
		String sql = stringWriter.toString();
		Page page = new Page(pageRequest);
		long totalCount = jdbcTemplate.queryForLong(crateJdbcCountSql(sql));
		page.setTotalItems(totalCount);

		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		if (totalCount > 0)
		{
			rsList = jdbcTemplate.queryForList(createJdbcPageSql(pageRequest, sql));
		}
		page.setResult(rsList);
		return page;
	}

	public Page excuteQuerySqlByJdbc(PageRequest pageRequest,String sql, Object... args)
	{
		Page page = new Page(pageRequest);
		long totalCount = jdbcTemplate.queryForLong(crateJdbcCountSql(sql));
		page.setTotalItems(totalCount);

		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		if (totalCount > 0)
		{
			rsList = jdbcTemplate.queryForList(createJdbcPageSql(pageRequest, sql), args);
		}
		page.setResult(rsList);
		return page;
	}

	private String crateJdbcCountSql(final String sql)
	{
		return "select count(*) from (" + sql + ") AS temp";
	}

	private String createJdbcPageSql(final PageRequest pageRequest, final String sql)
	{
		StringBuffer excuteSql = new StringBuffer();
		if (MYSQL.equals(getDBType()))
		{
			forPaginateMysql(excuteSql, pageRequest, sql);
		}
		else if (ORACLE.equals(getDBType()))
		{
			forPaginateOracle(excuteSql, pageRequest, sql);
		}
		else
		{
			
		}
		return excuteSql.toString();
	}
	
	private void forPaginateMysql(StringBuffer excuteSql,PageRequest pageRequest,String sql) {
		int benginIndex = (pageRequest.getPage() - 1) * pageRequest.getRows();
		int endIndex = pageRequest.getPage() * pageRequest.getRows();
		excuteSql.append(sql);
		excuteSql.append(" limit ").append(benginIndex).append(", ").append(endIndex);
	}
	
	private void forPaginateOracle(StringBuffer excuteSql,PageRequest pageRequest,String sql) {
		int benginIndex = (pageRequest.getPage() - 1) * pageRequest.getRows();
		int endIndex = pageRequest.getPage() * pageRequest.getRows();
		excuteSql.append("select * from ( select row_.*, rownum rownum_ from (  ");
		excuteSql.append(sql);
		excuteSql.append(" ) row_ where rownum <= ").append(endIndex).append(") table_alias");
		excuteSql.append(" where table_alias.rownum_ >= ").append(benginIndex);
	}

	private static String MYSQL = "mysql";
	private static String SQLSERVER = "sqlserver";
	private static String ORACLE = "oracle";
	private static String DEFAULT = "default";
	public static String getDBType()
	{
		String driverClassName = PropertiesConfigurer.getValue("jdbc.url");
		if (driverClassName.contains("mysql"))
		{
			return MYSQL;
		}
		if (driverClassName.contains("sqlserver"))
		{
			return SQLSERVER;
		}
		if (driverClassName.contains("oracle"))
		{
			return ORACLE;
		}
		else
		{
			return null;
		}
	}
}
