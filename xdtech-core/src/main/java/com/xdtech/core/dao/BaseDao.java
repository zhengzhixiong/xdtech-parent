package com.xdtech.core.dao;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xdtech.core.dao.dynamic.AddScalar;
import com.xdtech.core.dao.dynamic.DynamicHibernateStatementBuilder;
import com.xdtech.core.dao.dynamic.StatementTemplate;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.core.model.BaseItem;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.PageRequest;
import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.core.orm.utils.AssertUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
@Repository
public class BaseDao<BaseModel> extends HibernateDao<BaseModel, Long> implements InitializingBean{

	/**
	 * 模板缓存
	 */
	protected Map<String, StatementTemplate> templateCache;
	protected DynamicHibernateStatementBuilder dynamicStatementBuilder;
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Autowired
	public void setDynamicStatementBuilder(DynamicHibernateStatementBuilder dynamicStatementBuilder) {
		this.dynamicStatementBuilder = dynamicStatementBuilder;
	}
	
	
	public void afterPropertiesSet() throws Exception {
		templateCache = new HashMap<String, StatementTemplate>();
		if(this.dynamicStatementBuilder == null){
			throw new RuntimeException("dynamicStatementBuilder初始化失败！");
		}
		dynamicStatementBuilder.init();
		Map<String,String> namedHQLQueries = dynamicStatementBuilder.getNamedHQLQueries();
		Map<String,String> namedSQLQueries = dynamicStatementBuilder.getNamedSQLQueries();
		Configuration configuration = new Configuration();
		configuration.setNumberFormat("#");
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		for(Entry<String, String> entry : namedHQLQueries.entrySet()){
			stringLoader.putTemplate(entry.getKey(), entry.getValue());
			templateCache.put(entry.getKey(), new StatementTemplate(StatementTemplate.HQL,new Template(entry.getKey(),new StringReader(entry.getValue()),configuration)));
		}
		for(Entry<String, String> entry : namedSQLQueries.entrySet()){
			stringLoader.putTemplate(entry.getKey(), entry.getValue());
			templateCache.put(entry.getKey(), new StatementTemplate(StatementTemplate.SQL,new Template(entry.getKey(),new StringReader(entry.getValue()),configuration)));
		}
		configuration.setTemplateLoader(stringLoader);
	}
	
	protected String processTemplate(StatementTemplate statementTemplate,Map<String, String> parameters){
		StringWriter stringWriter = new StringWriter();
		try {
			statementTemplate.getTemplate().process(parameters, stringWriter);
		} catch (Exception e) {
			logger.error("处理DAO查询参数模板时发生错误：{}",e.toString());
			throw new RuntimeException(e);
		}
		return stringWriter.toString();
	}
	
	/**
	 * 例如 from TUser tu where 1=1 and tu....... 和  select t.* from test_user where 1=1 and ....
	 * @param queryName 查询的名称
	 * @param parameters 参数 
	 * @return
	 */
	public <X> List<X> findByNamedQuery(final String queryName, final Map<String, String> parameters) {
		StatementTemplate statementTemplate = templateCache.get(queryName);
		String statement = processTemplate(statementTemplate,parameters);
		if(statementTemplate.getSqlType().equals(StatementTemplate.HQL)){
			return findByHql(statement);
		}else{
			return findBySQL(statement);
		}
	}
	/**
	 * 如果是查询item必须有对应item类型，不然转换会异常
	 * @param queryName      查询对应sql
	 * @param baseCondition  配置条件
	 * @return
	 */
	public <X> List<X> findByNamedQuery(final String queryName, final BaseCondition baseCondition) {
		AssertUtils.notNull(baseCondition, "查询条件不能为空");
		StatementTemplate statementTemplate = templateCache.get(queryName);
		String statement = processTemplate(statementTemplate,baseCondition.getQueryConditions());
		if(statementTemplate.getSqlType().equals(StatementTemplate.HQL)){
			return findByHql(statement);
		}else{
			return findBySQLItem(statement,baseCondition.getBaseItem());
		}
	}
	/**
	 * 支持自定义item查询
	 * @param rowMapper
	 * @param queryName 查询的名称
	 * @param parameters 参数 动态sql条件参数
	 * @return
	 */
	public <X> List<X> findByNamedQuery(final String queryName,BaseItem baseItem, final Map<String, String> parameters) {
		StatementTemplate statementTemplate = templateCache.get(queryName);
		String statement = processTemplate(statementTemplate,parameters);
		
		if(StatementTemplate.HQL.equals(statementTemplate.getSqlType())){
			return findByHql(statement);
		}else{
			return findBySQLItem(statement,baseItem);
		}
	}
	
	public int excuteByNamedQuery(final String queryName,final Map<String, String> parameters) {
		StatementTemplate statementTemplate = templateCache.get(queryName);
		String statement = processTemplate(statementTemplate,parameters);
		
		if(StatementTemplate.HQL.equals(statementTemplate.getSqlType())){
			return createHqlQuery(statement).executeUpdate();
		}else{
			return createSQLQuery(statement).executeUpdate();
		}
	}
	
	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * @param sqlQueryString sql语句
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public Query createSQLQuery(final String sqlQueryString,Class<?> beanClass,Map<String, String> queryFields, final Object... values) {
		SQLQuery  query = getSession().createSQLQuery(sqlQueryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		if (beanClass!=null) {
			//添加要查询字段的标量  
            AddScalar.addSclar(query, beanClass, queryFields);  
			query.setResultTransformer(Transformers.aliasToBean(beanClass)); 
		}
		return query;
	}
	
	
	
	/**
	 * 按SQL查询对象列表,并将结果集转换成指定的对象列表
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> findBySQL(final String sql, final Object... values) {
		return createSQLQuery(sql,null,null, values).list();
	}
	
	
	/**
	 * 
	 * @param pageRequest 页查询信息
	 * @param queryName   查询的sql语句名称，由xml里配置
	 * @param baseCondition 查询条件，如果是查询item的时候需要设置对应item，查询model可不用
	 * @return 页集合信息
	 */
	public Page findPageByNamedQuery(final PageRequest pageRequest, final String queryName, final BaseCondition baseCondition) {
		AssertUtils.notNull(pageRequest, "page不能为空");
		Page page = new Page(pageRequest);
		StatementTemplate statementTemplate = templateCache.get(queryName);
		Map<String, String> parameters = baseCondition!=null?baseCondition.getQueryConditions():null;
		String statement = processTemplate(statementTemplate,parameters);
		Query q = null;
		if (pageRequest.isCountTotal()) {
			long totalCount = 0;
			if (StatementTemplate.SQL.equals(statementTemplate.getSqlType())) {
				totalCount = countSqlResult(statement);
				if (baseCondition.isItem()) {
					q = createSQLQuery(statement,baseCondition.getBaseItem().getClass(),baseCondition.getBaseItem().getQueryFields());
				}
			}else if (StatementTemplate.HQL.equals(statementTemplate.getSqlType())) {
				totalCount = countHqlResult(statement);
				q = createHqlQuery(statement);
			}
			page.setTotalItems(totalCount);
		}
		 
		setPageParameterToQuery(q, pageRequest);
		List result = q.list();
		page.setResult(result);
		return page;
	}
	/**
	 * 查询总记录数
	 * @param sql
	 * @return
	 */
	public long countSqlResult(final String sql) {
		String countHql = String.format("select count(*) from (%s) s_count", sql);
		try {
			//查询出来是biginteger，用字符串过来转
			String countNum = createSQLQuery(countHql).uniqueResult().toString();
			long count = Long.valueOf(countNum);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 按HQL查询对象列表，并将对象封装成指定的对象,这些对象必须是@entity注解的实体
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> findByHQLItem(final String hql, final Object... values) {
		List<X> result = createHqlQuery(hql, values).list();
		return result;
	}
	
	//http://blog.csdn.net/crazycoder2010/article/details/7414152#comments
	
	/**
	 * 按SQL查询对象列表 ，返回的对象就是baseItem
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> findBySQLItem(final String sql,BaseItem baseItem, final Object... values) {
		List<X> result = createSQLQuery(sql,baseItem.getClass(),baseItem.getQueryFields(), values).list();
		return result;
	}
	
	/**
	 * 
	 * @param pageRequest 页查询信息
	 * @param queryName   查询的sql语句名称，由xml里配置
	 * @param baseCondition 查询条件，如果是查询item的时候需要设置对应item，查询model可不用
	 * @return 页集合信息
	 */
	public Page findPageBySql(final PageRequest pageRequest,final String sql,final BaseCondition baseCondition) {
		AssertUtils.notNull(pageRequest, "page不能为空");
		Page page = new Page(pageRequest);
//		StatementTemplate statementTemplate = templateCache.get(queryName);
//		Map<String, String> parameters = baseCondition!=null?baseCondition.getQueryConditions():null;
//		String statement = processTemplate(statementTemplate,parameters);
		Query q = null;
		if (pageRequest.isCountTotal()) {
			long totalCount = 0;
//			if (StatementTemplate.SQL.equals(statementTemplate.getSqlType())) {
				totalCount = countSqlResult(sql);
				q = createSQLQuery(sql);
//				if (baseCondition.isItem()) {
//					q = createSQLQuery(statement,baseCondition.getBaseItem().getClass(),baseCondition.getBaseItem().getQueryFields());
//				}
//			}else if (StatementTemplate.HQL.equals(statementTemplate.getSqlType())) {
//				totalCount = countHqlResult(statement);
//				q = createHqlQuery(statement);
//			}
			page.setTotalItems(totalCount);
		}
		 
		setPageParameterToQuery(q, pageRequest);
		List result = q.list();
		page.setResult(result);
		return page;
	}
	
	public Page excuteQuerySqlByJdbc(final PageRequest pageRequest,final String sql,Object... args) {
		Page page = new Page(pageRequest);
		long totalCount = jdbcTemplate.queryForLong(crateJdbcCountSql(sql));
		page.setTotalItems(totalCount);
		List<Map<String, Object>> rsList = new ArrayList<Map<String,Object>>();
		if (totalCount>0) {
			rsList = jdbcTemplate.queryForList(createJdbcPageSql(pageRequest, sql), args); 
		}	
		page.setResult(rsList);
		return page;
	}
	
	private String crateJdbcCountSql(final String sql) {
		return "select count(*) from ("+sql+") AS temp";
	}
	
	private String createJdbcPageSql(final PageRequest pageRequest,final String sql) {
		StringBuffer sb = new StringBuffer(sql);
		int benginIndex = (pageRequest.getPage()-1)*pageRequest.getRows();
		int endIndex = pageRequest.getPage()*pageRequest.getRows();
		sb.append(" limit "+benginIndex+","+endIndex);
		return sb.toString();
	}
}
