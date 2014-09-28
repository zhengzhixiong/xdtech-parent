/*
 * Project Name: feui
 * File Name: ShiroTagFreeMarkerConfigurer.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.web.freemark;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.xdtech.web.freemark.tags.EasyTags;
import com.xdtech.web.freemark.tags.ShiroTags;
import com.xdtech.web.freemark.tags.TestTag;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2014-9-11 下午5:34:48
 */
public class SysFreeMarkerConfigurer extends FreeMarkerConfigurer {
 
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
//        this.getConfiguration().setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
        this.getConfiguration().setSharedVariable("test", new TestTag());
        this.getConfiguration().setSharedVariable("eu", new EasyTags());
    }
     
}