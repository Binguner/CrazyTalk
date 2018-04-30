package com.binguner.crazytalk.Adapters.ConvercationAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.binguner.crazytalk.Model.ConvercationModel.ConvercationAllListModelOk;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.UI.TalkActivity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;
import java.util.Set;

public class ConvercationAllOneAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    Context context;
    public ConvercationAllOneAdapter(List<MultiItemEntity> data,Context context) {
        super(data);
        this.context = context;
        addItemType(0, R.layout.convercation_all_layout);
        addItemType(1, R.layout.convercation_one_layout);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        //Log.d("tatatata",helper.getAdapterPosition()+"  " + helper.getItemViewType() );
        try {
            if (helper.getAdapterPosition() == 0) {
                helper.setText(R.id.convercation_layout_name_all, "好友回复");
            }
            if (helper.getAdapterPosition() == 4) {
                helper.setText(R.id.convercation_layout_name_all, "限时讨论组");
            }
            if (helper.getAdapterPosition() == 8) {
                helper.setText(R.id.convercation_layout_name_all, "我创建的群");
            }
            if (helper.getAdapterPosition() == 12) {
                helper.setText(R.id.convercation_layout_name_all, "我加的群");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (helper.getItemViewType()){
            case 0:
                try {
                    //helper.setImageResource(R.id.convercation_layout_nam_arrow,R.drawable.ic_arrow_drop_down_black_24dp);
                    int postion = helper.getAdapterPosition();
                    final ConvercationAllListModelOk modelOk = (ConvercationAllListModelOk) item;
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position1 = helper.getAdapterPosition();
                            if(modelOk.isExpanded()){
                                collapse(position1);
                                helper.setImageResource(R.id.convercation_layout_nam_arrow,R.drawable.ic_keyboard_arrow_right_black_24dp);
                            }else if(!modelOk.isExpanded()){
                                expand(position1);
                                helper.setImageResource(R.id.convercation_layout_nam_arrow,R.drawable.ic_arrow_drop_down_black_24dp);
                            }
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case 1:

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context,"TTT",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, TalkActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }
}
