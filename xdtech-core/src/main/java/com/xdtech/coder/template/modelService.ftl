package com.xdtech.${moduleName}.service;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.${moduleName}.model.${modelName?cap_first};
import com.xdtech.${moduleName}.vo.${modelName?cap_first}Item;

/**
 * 
 * @author max.zheng
 * @create ${createTime}
 * @since 1.0
 * @see
 */
public interface ${modelName?cap_first}Service extends IBaseService<${modelName?cap_first}>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdate${modelName?cap_first}(${modelName?cap_first}Item item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param newId
	 * @return
	 */
	${modelName?cap_first}Item load${modelName?cap_first}Item(Long ${modelName}Id);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create ${createTime}
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean delete${modelName?cap_first}Info(long id);

}
