package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.binguner.crazytalk.Adapters.ConvercationAdapters.ConvercationForFinderAdapter;
import com.binguner.crazytalk.Model.ConvercationModel.ConvercationOneListModel;
import com.binguner.crazytalk.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCircleFinder extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static FragmentCircleFinder fragmentCircleFinder;

    //@BindView(R.id.fragment_finder_edittext) EditText fragment_finder_edittext;
    @BindView(R.id.fragment_finder_recyclerview) RecyclerView fragment_finder_recyclerview;

    private LinearLayoutManager linearLayoutManager;
    private List<ConvercationOneListModel> list;
    private ConvercationForFinderAdapter adapter;
    private int lastIndexOfItem;

    public FragmentCircleFinder() {
    }

    public synchronized static FragmentCircleFinder getInstance() {
        if(null == fragmentCircleFinder){
            fragmentCircleFinder = new FragmentCircleFinder();
        }
        return fragmentCircleFinder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_circle_finder, container, false);;
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
        list = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getContext());
        for (int i = 0; i <= 10 ;i++) {
            list.add(new ConvercationOneListModel());
        }
        adapter = new ConvercationForFinderAdapter(R.layout.convercation_one_layout,list);

        View footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_wave_view,null,false);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.addFooterView(footView);
        fragment_finder_recyclerview.setLayoutManager(linearLayoutManager);
        fragment_finder_recyclerview.setAdapter(adapter);

        fragment_finder_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastIndexOfItem + 3 >= linearLayoutManager.getItemCount()){
                    addNewSeeds();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastIndexOfItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void addNewSeeds(){
        fragment_finder_recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != adapter){
                    for(int i = 0; i <= 10; i++){
                        adapter.addData(new ConvercationOneListModel());
                    }
                }
                adapter.loadMoreComplete();
            }
        },1000);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
