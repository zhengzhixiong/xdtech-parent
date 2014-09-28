
package com.xdtech.common.fomat;

import java.io.Serializable;

public interface TreeFormat<T, PK extends Serializable> extends Serializable {
    PK getId();
    
    T getParent();
    
    void addChildren(T child);
}
