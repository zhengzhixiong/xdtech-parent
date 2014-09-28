package com.xdtech.common.fomat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xdtech.common.utils.EmptyUtil;



public class TreeBuilder<T extends TreeFormat<T, PK>, PK extends Serializable> {

    public static <E extends TreeFormat<E, P>, P extends Serializable> TreeBuilder<E, P> 
            newTreeBuilder(Class<E> clazz, Class<P> pk) {
        return new TreeBuilder<E, P>();
    } 
    
    
    public Set<T> buildToTreeSet(Collection<T> origin) {
        Set<T> target = new HashSet<T>();
        toTreeFormat(origin, target);
        return target;
    }
    public List<T> buildToTreeList(Collection<T> origin) {
        List<T> target = new ArrayList<T>();
        toTreeFormat(origin, target);
        return target;
        
    }
    /**
     * 构建树
     * @param origin
     * @param target
     */
    private void toTreeFormat(Collection<T> origin, Collection<T> target) {
        LinkedList<T> queue = new LinkedList<T>();
        Map<PK, T> cache = new HashMap<PK, T>();
        for(T data : origin) {
            if(data.getParent() == null) {
            	//添加父节点
                target.add(data);
            } else {
            	//添加非父节点
                queue.add(data);
            }
            //存放节点id-节点实体
            cache.put(data.getId(), data);
        }
        while(EmptyUtil.isNotEmpty(queue)) {
//        	for (T q : queue) {
//				T parent = cache.get(q.getParent().getId());
//				if (parent!=null) {
//					parent.addChildren(q);
//					queue.remove(q);
//				}
//			}
//        	
            int queueSize = queue.size();
            for(int i=0; i<queueSize; i++) {
                T data = queue.poll();
                T parent = cache.get(data.getParent().getId());
                if(parent != null) {
                    parent.addChildren(data);
                } else {
                    queue.add(data);
                }
            }
            if(queue.size() == queueSize) {
                for(int i=0; i<queueSize; i++) {
                    target.add(queue.poll());
                }
            }
        }
    } 
}
