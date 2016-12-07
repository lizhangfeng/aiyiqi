package com.bwf.aiyiqi.ui.fragment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingFragment;
import com.bwf.aiyiqi.common.entity.ArticleBean;
import com.bwf.aiyiqi.common.entity.HomePicBean;
import com.bwf.aiyiqi.common.http.api.HomeApi;
import com.bwf.aiyiqi.common.http.callback.HomeCallBack;
import com.bwf.aiyiqi.common.util.IntentUtils;
import com.bwf.aiyiqi.databinding.FragmentHomeBinding;
import com.bwf.aiyiqi.databinding.HeaderviewHomeBinding;
import com.bwf.aiyiqi.ui.activity.ScanActivity;
import com.bwf.aiyiqi.ui.activity.SearchActivity;
import com.bwf.aiyiqi.ui.adapter.HomeAdsAdapter;
import com.bwf.aiyiqi.ui.adapter.HomeListAdapter;
import com.bwf.aiyiqi.ui.listener.HomeClickListener;
import com.jingchen.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 */
public class HomeFragment extends BaseDataBindingFragment<FragmentHomeBinding> implements HomeCallBack, HomeClickListener {

    private HomeAdsAdapter adsAdapter;

    private HomeListAdapter homeListAdapter;

    private HeaderviewHomeBinding headerviewHomeBinding;

    private HomeApi homeApi;

    private List<ArticleBean> articleBeen;

    private int page = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        //导航
        headerviewHomeBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.headerview_home, null, false);
        adsAdapter = new HomeAdsAdapter(getContext());
        headerviewHomeBinding.viewPagerHome.setAdapter(adsAdapter);

        //下面列表
        homeListAdapter = new HomeListAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewDataBinding.rcHome.setLayoutManager(layoutManager);
        viewDataBinding.rcHome.setAdapter(homeListAdapter);
        homeListAdapter.setHeaderView(headerviewHomeBinding.getRoot());

        //添加点击事件
        headerviewHomeBinding.setEvent(this);
        viewDataBinding.homeTitle.setEvent(this);

        //下拉刷新
        viewDataBinding.refreshLayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {//下拉刷新
                homeApi.getHomeData();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {//加载更多
                page++;
                if (articleBeen != null && articleBeen.size() >= 1){
                    ArticleBean lastBean = articleBeen.get(articleBeen.size() - 1);
                    homeApi.getHomeList("" + page, lastBean.type, lastBean.id);
                }
            }
        });

        getHomePic();

    }

    /**
     * 获取首页广告和文章列表
     */
    private void getHomePic() {
        articleBeen = new ArrayList<>();
        homeApi = new HomeApi();
        homeApi.setRefreshLayout(viewDataBinding.refreshLayout);
        homeApi.setCallBack(this);
        homeApi.getHomeData();
    }

    @Override
    public void getHomePic(List<HomePicBean> homePicBeanList) {
        adsAdapter.setDatas(homePicBeanList);
        adsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHomeListData(List<ArticleBean> articleBeen) {

        if (articleBeen != null) {

            if (articleBeen.size() < 10) {
                viewDataBinding.rcHome.setCanPullUp(false);
            } else {
                viewDataBinding.rcHome.setCanPullUp(true);
            }

            if (page == 1)
                this.articleBeen = articleBeen;
            else
                this.articleBeen.addAll(articleBeen);

            homeListAdapter.setDatas(this.articleBeen);
            homeListAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void goScan(View view) {
        IntentUtils.openActivity(getContext(), ScanActivity.class);
    }

    @Override
    public void goSearch(View view) {
        IntentUtils.openActivity(getContext(), SearchActivity.class);
    }

    @Override
    public void goDecoration(View view) {

    }

    @Override
    public void goSameCity(View view) {

    }

    @Override
    public void goDecorationSchool(View view) {

    }

    @Override
    public void goDecorationBudget(View view) {

    }

    @Override
    public void goBuilding(View view) {

    }

    @Override
    public void goDrawing(View view) {

    }

    @Override
    public void goOrder(View view) {

    }

    @Override
    public void goDesign(View view) {

    }
}
