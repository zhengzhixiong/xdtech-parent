/**
 * Copyright (c) 2005-2011 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SimpleHibernateDao.java 1594 2011-05-11 14:22:29Z calvinxiu $
 */
package com.xdtech.core.orm.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xdtech.core.orm.utils.AssertUtils;
import com.xdtech.core.orm.utils.ReflectionUtils;

/**
 * ��װHibernateԭ��API��DAO���ͻ���.
 * 
 * �ο�Spring2.5�Դ���Petlinc����, ȡ����HibernateTemplate, ֱ��ʹ��Hibernateԭ��API.
 * 
 * @param <T> DAO�����Ķ�������
 * @param <ID> ��������
 * 
 * @author calvin
 */
public class SimpleHibernateDao<T, ID extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;
	
	

	/**
	 * ͨ������ķ��Ͷ���ȡ�ö�������Class.
	 * eg.
	 * public class UserDao extends SimpleHibernateDao<User, Long>
	 */
	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public SimpleHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * ȡ��sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * ����@Autowired������ע��SessionFactory, ���ж��SesionFactory��ʱ�����������ر�����.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	/**
	 * ȡ�õ�ǰSession.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * �����������޸ĵĶ���.
	 */
	public void save(final T entity) {
		AssertUtils.notNull(entity, "entity����Ϊ��");
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}
	
	public void saveAll(final List<T> entities) {
		AssertUtils.notNull(entities, "entity����Ϊ��");
		for (T entity : entities) {
			save(entity);
		}
		logger.debug("save entities: {}", entities);
	}

	/**
	 * ɾ������.
	 * 
	 * @param entity ���������session�еĶ����id���Ե�transient����.
	 */
	public void delete(final T entity) {
		AssertUtils.notNull(entity, "entity����Ϊ��");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}
	
	public void deleteAll(final List<T> entities) {
		AssertUtils.notNull(entities, "entity����Ϊ��");
		for (T entity : entities) {
			delete(entity);
		}
		logger.debug("delete entities: {}", entities);
	}

	/**
	 * ��idɾ������.
	 */
	public void delete(final ID id) {
		AssertUtils.notNull(id, "id����Ϊ��");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	/**
	 * ��id��ȡ����.
	 */
	public T get(final ID id) {
		AssertUtils.notNull(id, "id����Ϊ��");
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * ��id�б���ȡ�����б�.
	 */
	public List<T> get(final Collection<ID> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 *	��ȡȫ������.
	 */
	public List<T> getAll() {
		return find();
	}

	/**
	 *	��ȡȫ������, ֧�ְ���������.
	 */
	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	/**
	 * �����Բ��Ҷ����б�, ƥ�䷽ʽΪ���.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		AssertUtils.hasText(propertyName, "propertyName����Ϊ��");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/**
	 * �����Բ���Ψһ����, ƥ�䷽ʽΪ���.
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		AssertUtils.hasText(propertyName, "propertyName����Ϊ��");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * ��HQL��ѯ�����б�.
	 * 
	 * @param values �����ɱ�Ĳ���,��˳���.
	 */
	public <X> List<X> findByHql(final String hql, final Object... values) {
		return createHqlQuery(hql, values).list();
	}

	/**
	 * ��HQL��ѯ�����б�.
	 * 
	 * @param values ��������,�����ư�.
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * ��HQL��ѯΨһ����.
	 * 
	 * @param values �����ɱ�Ĳ���,��˳���.
	 */
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createHqlQuery(hql, values).uniqueResult();
	}

	/**
	 * ��HQL��ѯΨһ����.
	 * 
	 * @param values ��������,�����ư�.
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * ִ��HQL���������޸�/ɾ������.
	 * 
	 * @param values �����ɱ�Ĳ���,��˳���.
	 * @return ���¼�¼��.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createHqlQuery(hql, values).executeUpdate();
	}

	/**
	 * ִ��HQL���������޸�/ɾ������.
	 * 
	 * @param values ��������,�����ư�.
	 * @return ���¼�¼��.
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * ���ݲ�ѯHQL������б�����Query����.
	 * ��find()�����ɽ��и������Ĳ���.
	 * 
	 * @param values �����ɱ�Ĳ���,��˳���.
	 */
	public Query createHqlQuery(final String queryString, final Object... values) {
		AssertUtils.hasText(queryString, "queryString����Ϊ��");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * ���ݲ�ѯHQL������б�����Query����.
	 * ��find()�����ɽ��и������Ĳ���.
	 * 
	 * @param values ��������,�����ư�.
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		AssertUtils.hasText(queryString, "queryString����Ϊ��");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * ��Criteria��ѯ�����б�.
	 * 
	 * @param criterions �����ɱ��Criterion.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * ��Criteria��ѯΨһ����.
	 * 
	 * @param criterions �����ɱ��Criterion.
	 */
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/**
	 * ����Criterion��������Criteria.
	 * ��find()�����ɽ��и������Ĳ���.
	 * 
	 * @param criterions �����ɱ��Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * ��ʼ������.
	 * ʹ��load()�����õ��Ľ��Ƕ���Proxy, �ڴ���View��ǰ��Ҫ���г�ʼ��.
	 * �������entity, ��ֻ��ʼ��entity��ֱ������,�������ʼ���ӳټ��صĹ������Ϻ�����.
	 * �����ʼ����������,��ִ��:
	 * Hibernate.initialize(user.getRoles())����ʼ��User��ֱ�����Ժ͹�������.
	 * Hibernate.initialize(user.getDescription())����ʼ��User��ֱ�����Ժ��ӳټ��ص�Description����.
	 */
	public void initProxyObject(Object proxy) {
		Hibernate.initialize(proxy);
	}

	/**
	 * Flush��ǰSession.
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * ΪQuery����distinct transformer.
	 * Ԥ���ع��������HQL�������������ظ�, ��Ҫ����distinct����.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * ΪCriteria����distinct transformer.
	 * Ԥ���ع��������HQL�������������ظ�, ��Ҫ����distinct����.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * ȡ�ö����������.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * �ж϶��������ֵ�����ݿ����Ƿ�Ψһ.
	 * 
	 * ���޸Ķ�����龰��,����������޸ĵ�ֵ(value)��������ԭ����ֵ(orgValue)�����Ƚ�.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}

	
	
}