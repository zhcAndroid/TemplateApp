package com.template.app.activity;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.base.BaseRecyclerViewActivity;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.common.baselibrary.net.HttpUtils;
import com.common.baselibrary.net.RequestParam;
import com.common.baselibrary.net.callback.OnResultCallBack;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;
import com.template.app.AppConfig;
import com.template.app.R;
import com.template.app.adapter.TestAdapter;
import com.template.app.arouter.ARouterUriManger;
import com.template.app.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterUriManger.AROUTER_TestActivity)
public class TestActivity extends BaseRecyclerViewActivity {


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        //设置 Header 为 贝塞尔雷达 样式
       // mSmartRefresh.setRefreshHeader(new TaurusHeader(this));

    }

    @Override
    public void initData() {
        super.initData();
//        List<TestBean> data = new ArrayList<>();
//        for(int i=0;i<15;i++){
//            TestBean testBean = new TestBean();
//            testBean.setTest("item"+i);
//            data.add(testBean);
//        }
//        onRefreshSuccess(data);


        //net work
        RequestParam params = new RequestParam();
        params.addParameter("page", 1);
        params.addParameter("rows", 20);
        params.addParameter("annNum", 1628);
        params.addParameter("totalYOrN", true);
        HttpUtils.getInstance().postRequest("tmann/annInfoView/annSearchDG.html",
                params,new RxStringCallback(){
                    @Override
                    public void onError(Object tag, Throwable e) {

                    }

                    @Override
                    public void onCancel(Object tag, Throwable e) {

                    }

                    @Override
                    public void onNext(Object tag, String response) {
                        Log.i("response",response);
                    }
                }
        );

    }


    @Override
    protected BaseQuickAdapter getRecyclerAdapter() {
        return new TestAdapter(R.layout.item_test);
    }


    @Override
    protected void onRefreshData(RefreshLayout refreshLayout) {
        super.onRefreshData(refreshLayout);
        List<TestBean> data = new ArrayList<>();
        for(int i=0;i<10;i++){
            TestBean testBean = new TestBean();
            testBean.setTest("new item"+i);
            data.add(testBean);
        }
        onRefreshSuccess(data);

    }

    @Override
    protected void onLoadMoreData(RefreshLayout refreshLayout) {
        super.onLoadMoreData(refreshLayout);
        List<TestBean> data = new ArrayList<>();
        for(int i=0;i<10;i++){
            TestBean testBean = new TestBean();
            testBean.setTest("more item"+i);
            data.add(testBean);
        }

        onLoadMoreSuccess(data);
    }


}
