package com.xdtech.${moduleName}.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.${moduleName}.dao.${modelName?cap_first}Dao;
import com.xdtech.${moduleName}.model.${modelName?cap_first};
import com.xdtech.${moduleName}.service.${modelName?cap_first}Service;
import com.xdtech.${moduleName}.vo.${modelName?cap_first}Item;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create ${createTime}
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class ${modelName?cap_first}ServiceImpl implements ${modelName?cap_first}Service {
	@Autowired
	private ${modelName?cap_first}Dao ${modelName}Dao;

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param entity
	 */
	public void save(${modelName?cap_first} entity) {
		${modelName}Dao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param entity
	 */
	public void delete(${modelName?cap_first} entity) {
		${modelName}Dao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		${modelName}Dao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param id
	 * @return
	 */
	public ${modelName?cap_first} get(Long id) {
		return ${modelName}Dao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @return
	 */
	public List<${modelName?cap_first}> getAll() {
		return ${modelName}Dao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> ${modelName}s = null;
		long rows = 0;
		if (pg!=null) {
			Page<${modelName?cap_first}> page = ${modelName}Dao.findPage(pg,"from ${modelName?cap_first} order by createTime desc", values);
			${modelName}s = BeanUtils.copyListProperties(
					${modelName?cap_first}Item.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<${modelName?cap_first}> ${modelName}List = ${modelName}Dao.find("from ${modelName?cap_first} order by id desc", values);
			${modelName}s = BeanUtils.copyListProperties(
					${modelName?cap_first}Item.class, ${modelName}List);
			rows = ${modelName}s.size();
		}
		results.put("total", rows);
		results.put("rows", ${modelName}s);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdate${modelName?cap_first}(${modelName?cap_first}Item item) {
		${modelName?cap_first} ${modelName} = null;
		if (item.getId()==null) {
			${modelName} = new ${modelName?cap_first}();
		}else {
			${modelName} = ${modelName}Dao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(${modelName}, item);
		${modelName}Dao.save(${modelName});
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param ${modelName}Id
	 * @return
	 */
	public ${modelName?cap_first}Item load${modelName?cap_first}Item(Long ${modelName}Id) {
		${modelName?cap_first} ${modelName} = ${modelName}Dao.get(${modelName}Id);
		${modelName?cap_first}Item ${modelName}Item = new ${modelName?cap_first}Item();
		BeanUtils.copyProperties(${modelName}Item, ${modelName});
		return ${modelName}Item;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean delete${modelName?cap_first}Info(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean delete${modelName?cap_first}Info(List<Long> ${modelName}Ids) {
		for (Long id : ${modelName}Ids) {
			delete(id);
		}
		return true;
	}

}
