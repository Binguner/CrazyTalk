package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binguner.crazytalk.CustomView.CustomPagetTransformer;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.CustomView.ChildViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentSquare extends Fragment {

    private static FragmentSquare fragmentSquare;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.square_viewpager) ChildViewPager square_viewpager;

    public FragmentSquare() {
        // Required empty public constructor
    }


    public synchronized static FragmentSquare getInstance() {
        if(fragmentSquare == null){
            fragmentSquare = new FragmentSquare();
        }
        return fragmentSquare;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        ButterKnife.bind(this,view);
        initViewPager();

        return view;
    }

    private void initViewPager() {
        square_viewpager.setPageTransformer(false,new CustomPagetTransformer(getActivity()));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
