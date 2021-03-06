package com.template.app.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.base.BaseRecyclerViewActivity;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.template.app.R;
import com.template.app.adapter.TestAdapter;
import com.template.app.arouter.ARouterUriManger;
import com.template.app.bean.TestBean;
import com.template.app.mvp.contract.TestContract;
import com.template.app.mvp.presnter.TestPresenter;

import java.util.List;

/**
 * 使用契约类Contract是为了减少java文件数量 方便查看管理
 * 这里的mvp模式 的model层弱化了 因为已经的封装了网络层 这里把网络层看做了model'层
 */
@Route(path = ARouterUriManger.AROUTER_TestActivity)
public class TestActivity extends BaseRecyclerViewActivity<TestBean, TestPresenter> implements TestContract.TestView {

    @Override
    public TestPresenter getPresenter() {
        return new TestPresenter();
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
        presenter.getDataFromServer();
    }


    @Override
    protected BaseQuickAdapter getRecyclerAdapter() {
        return new TestAdapter(R.layout.item_test);
    }


    @Override
    protected void onRefreshData(RefreshLayout refreshLayout) {
        super.onRefreshData(refreshLayout);
        pageIndex = 0;
        presenter.getDataFromServer();

    }

    @Override
    protected void onLoadMoreData(RefreshLayout refreshLayout) {
        super.onLoadMoreData(refreshLayout);
        presenter.getDataFromServer();

    }

    /**
     * 显示列表数据
     *
     * @param list
     */
    @Override
    public void showList(List<TestBean> list) {
        ToastUtils.show("showList 执行了");
        setDataList(list);
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        TestBean testBean = (TestBean) adapter.getData().get(position);
        ToastUtils.show(testBean.name);

    }


}
