package com.xdtech.web.freemark.tags;

import freemarker.template.SimpleHash;

public class EasyTags extends SimpleHash {
	private static final long serialVersionUID = 1L;

	public EasyTags() {
        put("datagrid", new DataGridTag());
        put("linkbutton", new LinkButtonTag());
        put("menubutton", new MenuButtonTag());
        put("searchbox", new SearchBoxTag());
        put("tree", new TreeTag());
    }
}
