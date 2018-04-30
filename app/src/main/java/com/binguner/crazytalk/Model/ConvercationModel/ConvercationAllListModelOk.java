package com.binguner.crazytalk.Model.ConvercationModel;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ConvercationAllListModelOk extends AbstractExpandableItem<ConvercationOneListModel> implements MultiItemEntity {
    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
