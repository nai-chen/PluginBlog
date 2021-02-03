package com.plugin.blog.demo.extension;

import java.util.HashMap;

import com.plugin.blog.demo.data.UserInfo;

public class UserExtensionInfo extends UserInfo {
    private HashMap<String, String> mContent = new HashMap<>();

    public UserExtensionInfo() {
        super("扩展信息", "显示和查看扩展信息");
    }

    public void setValue(String key, String value) {
        String oldValue = mContent.get(key);

        mContent.put(key, value);
        firePropertyChange(key, oldValue, value);
    }

    public String getValue(String key) {
        String value = mContent.get(key);
        return value == null ? "" : value;
    }

    @Override
    public String getText() {
        String text = "";
        for (UserExtensionInfoItem infoItem :
                UserExtensionInfoItem.getExtensionInfoList()) {
            text += infoItem.getName() + "：" + getValue(infoItem.getId()) + "\n";
        }
        return text;
    }
}
