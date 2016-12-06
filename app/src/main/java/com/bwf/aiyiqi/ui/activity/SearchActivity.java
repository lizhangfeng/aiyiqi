package com.bwf.aiyiqi.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingActivity;
import com.bwf.aiyiqi.common.db.GreenDaoUtils;
import com.bwf.aiyiqi.common.db.bean.SearchHistory;
import com.bwf.aiyiqi.common.db.bean.SearchHistoryDao;
import com.bwf.aiyiqi.common.entity.SearchBean;
import com.bwf.aiyiqi.common.http.api.SearchApi;
import com.bwf.aiyiqi.common.http.callback.SearchCallBack;
import com.bwf.aiyiqi.common.util.ToastUtil;
import com.bwf.aiyiqi.databinding.ActivitySearchBinding;
import com.bwf.aiyiqi.ui.adapter.SearchHistoryAdapter;
import com.bwf.aiyiqi.ui.adapter.SearchListAdapter;
import com.bwf.aiyiqi.ui.view.DividerItemDecoration;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * 搜索页面
 */
public class SearchActivity extends BaseDataBindingActivity<ActivitySearchBinding> implements SearchCallBack {

    /*  搜索结果 */
    private SearchApi searchApi;
    private SearchListAdapter adapter;
    private int page = 1;

    /* 搜索历史 */
    private List<SearchHistory> searchHistoryList;
    private SearchHistoryDao searchHistoryDao;
    private SearchHistoryAdapter historyAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        searchHistoryDao = GreenDaoUtils.getInstance().getmDaoSession().getSearchHistoryDao();
        searchApi = new SearchApi();
        searchApi.setSearchCallBack(this);

        initSearchHistory();
        initSearch();
        initSearchAdapter();

    }

    /**
     * 初始化搜索历史
     */
    private void initSearchHistory() {
//        searchHistoryList = searchHistoryDao.loadAll();//默认升序
        searchHistoryList = searchHistoryDao.queryBuilder().orderDesc(SearchHistoryDao.Properties.Time).build().list();
        if (searchHistoryList != null && !searchHistoryList.isEmpty()) {
            viewDataBinding.imgNoSearch.setVisibility(View.GONE);
            viewDataBinding.rcSearchHistory.setVisibility(View.VISIBLE);
        }
        historyAdapter = new SearchHistoryAdapter(this);
        historyAdapter.setDatas(searchHistoryList);
        viewDataBinding.rcSearchHistory.setAdapter(historyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewDataBinding.rcSearchHistory.setLayoutManager(layoutManager);
        viewDataBinding.rcSearchHistory.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    /**
     * 初始化adapter
     */
    private void initSearchAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewDataBinding.rcSearchResult.setLayoutManager(layoutManager);
        viewDataBinding.rcSearchResult.setCanPullDown(false);
        adapter = new SearchListAdapter(this);
        viewDataBinding.rcSearchResult.setAdapter(adapter);
    }

    /**
     * 初始化搜索监听
     */
    private void initSearch() {
        //输入监听
        viewDataBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    viewDataBinding.imgNoSearch.setVisibility(View.GONE);
                    viewDataBinding.rcSearchResult.setVisibility(View.VISIBLE);
                    String kw = viewDataBinding.etSearch.getText().toString().trim();
                    saveHistory(kw);
                    searchApi.getSearchResult("" + page, kw);
                } else {
                    if (searchHistoryList != null && !searchHistoryList.isEmpty()) {
                        viewDataBinding.imgNoSearch.setVisibility(View.GONE);
                        viewDataBinding.rcSearchHistory.setVisibility(View.VISIBLE);
                    } else {
                        viewDataBinding.imgNoSearch.setVisibility(View.VISIBLE);
                    }
                    viewDataBinding.rcSearchResult.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewDataBinding.etSearch.setDrawable();
            }
        });

        //点击键盘完成按钮监听
        viewDataBinding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ToastUtil.showToast(textView.getText().toString());
                return false;
            }
        });

        //点击取消按钮
        viewDataBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 保存搜索历史
     *
     * @param kw
     */
    private void saveHistory(String kw) {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setContent(kw);
        searchHistory.setTime(System.currentTimeMillis());
        Query query = searchHistoryDao.queryBuilder().where(SearchHistoryDao.Properties.Content.eq(kw)).build();
        List<SearchHistory> list = query.list();

        //如果存在则更新，不存在则插入数据
        if (list != null && !list.isEmpty()) {
            list.get(0).setSearchHistory(searchHistory);
            searchHistoryDao.update(list.get(0));
        } else {
            searchHistoryDao.insert(searchHistory);
        }

    }

    @Override
    public void getSearchResultData(List<SearchBean> beanList) {
        adapter.setDatas(beanList);
        adapter.notifyDataSetChanged();
    }
}
