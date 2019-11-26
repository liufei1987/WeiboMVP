package com.liufeismart.weibo.bean;

import androidx.annotation.Nullable;

public class DefaultGroupBean {
    private long id;
    private String name;
    private boolean enable;

    private boolean canDelete = true;//是否能被删除

    public DefaultGroupBean(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean canDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof DefaultGroupBean) {
            DefaultGroupBean bean = (DefaultGroupBean)obj;
            if(bean.name.equals(this.name)){
                return true;
            }
        }
        return false;
    }
}
