package com.xdtech.core.dao.dynamic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.xml.sax.InputSource;


/**
 * @author WangXuzheng
 * 默认的加载器-将指定配置文件中的sql/hql语句加载到内存中
 */
public class DefaultDynamicHibernateStatementBuilder implements DynamicHibernateStatementBuilder, ResourceLoaderAware {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDynamicHibernateStatementBuilder.class);
	private Map<String, String> namedHQLQueries;
	private Map<String, String> namedSQLQueries;
	private String[] fileNames = new String[0];
	private ResourceLoader resourceLoader;
//	/**
//	 * 查询语句名称缓存，不允许重复
//	 */
//	private Set<String> nameCache = new HashSet<String>();

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public Map<String, String> getNamedHQLQueries() {
		return namedHQLQueries;
	}

	public Map<String, String> getNamedSQLQueries() {
		return namedSQLQueries;
	}

	public void init() throws IOException {
		namedHQLQueries = new HashMap<String, String>();
		namedSQLQueries = new HashMap<String, String>();
		boolean flag = this.resourceLoader instanceof ResourcePatternResolver;
		for (String file : fileNames) {
			if (flag) {
				Resource[] resources = ((ResourcePatternResolver) this.resourceLoader).getResources(file);
				buildMap(resources);
			} else {
				Resource resource = resourceLoader.getResource(file);
				buildMap(resource);
			}
		}
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	private void buildMap(Resource[] resources) throws IOException {
		if (resources == null) {
			return;
		}
		for (Resource resource : resources) {
			buildMap(resource);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	private void buildMap(Resource resource) {
		InputSource inputSource = null;
		try {
			SAXReader saxReader = new SAXReader();
			//去除读取文件方式，因为jar里读取就不是文件了
//			Document document = saxReader.read(resource.getFile());
			Document document = saxReader.read(resource.getInputStream());
			Element root = document.getRootElement();
//			System.out.println(root.getName());
			for(Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				Attribute typeAttr = element.attribute("type");
				if ("HQL".equals(typeAttr.getValue())) {
					putStatementToCacheMap(resource, element, namedHQLQueries);
				}else if ("SQL".equals(typeAttr.getValue())) {
					putStatementToCacheMap(resource, element, namedSQLQueries);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e);
		} finally {
			if (inputSource != null && inputSource.getByteStream() != null) {
				try {
					inputSource.getByteStream().close();
				} catch (IOException e) {
					logger.error(e.toString());
					throw new RuntimeException(e);
				}
			}
		}

	}

	private void putStatementToCacheMap(Resource resource, final Element element, Map<String, String> statementMap)
			throws IOException {
//		Attribute typeAttr = element.attribute("type");
//		Attribute nameAttr = element.attribute("name");
//		String sqlText = element.getText();
//		if ("HQL".equals(typeAttr.getValue())) {
//			
//		}else if ("SQL".equals(typeAttr.getValue())) {
//			
//		}
		String sqlQueryName = element.attribute("name").getText();
		if (statementMap.keySet().contains(sqlQueryName)) {
			throw new RuntimeException("重复的sql语句定义在文件:" + resource.getURI() + "中，必须保证name的唯一.");
		}
		String queryText = element.getText().trim().replaceAll("@#", "<#").replaceAll("@/#", "</#").replace("@", ">");
		statementMap.put(sqlQueryName, queryText);
	}
}