package com.plugin.blog.demo.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class UserExtensionInfoItem {
    private static List<UserExtensionInfoItem> ExtensionInfoList;
    private String mId;
    private String mName;

    public UserExtensionInfoItem(String id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public static List<UserExtensionInfoItem> getExtensionInfoList() {
        if (ExtensionInfoList == null) {
            ExtensionInfoList = new ArrayList<>();
            IExtensionRegistry extensionRegister = Platform.getExtensionRegistry();
            IExtensionPoint extensionPoint = extensionRegister.getExtensionPoint(
                    "com.plugin.blog.demo.userExtensionInfo");
            IExtension[] extensions = extensionPoint.getExtensions();
            for (IExtension ext : extensions) {
                for (IConfigurationElement ce : ext.getConfigurationElements()) {
                    String id = ce.getAttribute("id");
                    String name = ce.getAttribute("name");

                    ExtensionInfoList.add(new UserExtensionInfoItem(id, name));
                }
            }
        }
        return ExtensionInfoList;
    }
}
