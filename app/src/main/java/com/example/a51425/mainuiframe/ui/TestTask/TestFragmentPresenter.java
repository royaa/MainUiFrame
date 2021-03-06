package com.example.a51425.mainuiframe.ui.TestTask;

import com.cyxk.wrframelibrary.base.BasePresenter2;
import com.cyxk.wrframelibrary.net.HttpCallBack;
import com.cyxk.wrframelibrary.utils.ToastUtil;
import com.example.a51425.mainuiframe.APP;
import com.example.a51425.mainuiframe.bean.SpeciesBean;
import com.example.a51425.mainuiframe.ui.TestTask2.TestFragment2;

/**
 * Created by 51425 on 2017/6/12.
 */

public class TestFragmentPresenter extends TestContract.Presenter<TestFragment,TestLogic> {


    public TestFragmentPresenter(TestFragment fragment, TestLogic model) {
        super(fragment, model);
    }

    @Override
    public void initData() {
        mModel.getData(new HttpCallBack<SpeciesBean>() {
            @Override
            public void onSuccess(SpeciesBean bean) {
                context.detailData(bean);

            }

            @Override
            public void onJsonError() {

            }

            @Override
            public void mustExit(String needShow) {

            }

            @Override
            public void onFailure(String error) {
                ToastUtil.showToast(APP.getContext(),error);

            }
        });

    }
}
