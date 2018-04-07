package com.binguner.crazytalk.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binguner.crazytalk.CustomView.CustomPagetTransformer;
import com.binguner.crazytalk.R;
import com.binguner.crazytalk.CustomView.ChildViewPager;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentSquare extends Fragment {

    private static FragmentSquare fragmentSquare;
    private OnFragmentInteractionListener mListener;
    private List<CommenFragment> fragments = new ArrayList<>();
    private final String[] imageArray = {"assets://pic1.jpg", "assets://pic2.jpg", "assets://pic3.jpg", "assets://pic4.jpg", "assets://pic5.jpg"};

    @BindView(R.id.square_viewpager) ViewPager square_viewpager;
    @BindView(R.id.indicator_tv) TextView indicator_tv;

    /*int currentIndex;
    boolean mIsChanged;*/

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
        //initViewPager();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        ButterKnife.bind(this,view);
        initViewPager();
        initImageLoader();

        return view;
    }

    private void initViewPager() {

        square_viewpager.setPageTransformer(false,new CustomPagetTransformer(getActivity()));
        for(int i = 0 ; i < 10 ; i++){
            fragments.add(new CommenFragment());
        }
        square_viewpager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CommenFragment fragment = fragments.get(position % 10);
                fragment.bindData(imageArray[position % imageArray.length]);
                return fragment;
            }

            @Override
            public int getCount() {
                return 666;
            }
        });

        square_viewpager.setOffscreenPageLimit(3);
        square_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateIndicatorTv();
                /*mIsChanged=true;
                if(position==0){
                    currentIndex=fragments.size()-2;
                }else if(position==fragments.size()-1){
                    currentIndex=1;
                }else{
                    currentIndex=position;
                }*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                updateIndicatorTv();
                /*if(state==ViewPager.SCROLL_STATE_IDLE){
                    if(mIsChanged){
                        mIsChanged=false;
                        square_viewpager.setCurrentItem(currentIndex,false);
                    }
                }*/
            }
        });
        updateIndicatorTv();
    }

    private void updateIndicatorTv() {
        int totalNum = square_viewpager.getAdapter().getCount();
        int currentItem = square_viewpager.getCurrentItem() + 1;
        indicator_tv.setText(Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity())
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024) // 缓冲大小
                .discCacheFileCount(100) // 缓冲文件数目
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getActivity())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();

        // 2.单例ImageLoader类的初始化
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
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
