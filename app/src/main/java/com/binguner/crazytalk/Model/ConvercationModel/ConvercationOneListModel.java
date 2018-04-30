package com.binguner.crazytalk.Model.ConvercationModel;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ConvercationOneListModel implements MultiItemEntity{

    private String convercationName;

    public String getConvercationName() {
        return convercationName;
    }

    public void setConvercationName(String convercationName) {
        this.convercationName = convercationName;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
