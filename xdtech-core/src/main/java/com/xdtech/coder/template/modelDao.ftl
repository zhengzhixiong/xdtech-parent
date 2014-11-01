package com.xdtech.${moduleName}.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.message.model.New;

/**
 * 
 * @author max.zheng
 * @create ${createTime}
 * @since 1.0
 * @see
 */
@Repository
public class ${modelName?cap_first}Dao extends HibernateDao<${modelName?cap_first}, Long>{

}
