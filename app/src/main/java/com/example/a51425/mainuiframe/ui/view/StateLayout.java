package com.example.a51425.mainuiframe.ui.view;


import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.a51425.mainuiframe.R;
import com.example.a51425.mainuiframe.utils.LogUtil;


/**
 * 状态布局，封装了4种状态：正在加载、加载失败、加载为空、正常界面
 * 使用{@link #newInstance(Context, View)} 来创建StateLayout的实现
 * @author wr
 *
 */
public class StateLayout extends RelativeLayout {

	private View loadingView;
	private View failView;
	private View emptyView;
	private View contentView;
	private boolean isShow;


	public StateLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * 创建一个StateLayout实现
	 * @param contentView 正常想要展示的View
	 */
	public static StateLayout newInstance(Context context, View contentView) {
		StateLayout stateLayout = (StateLayout) LayoutInflater.from(context).inflate(R.layout.state_layout, null);
		stateLayout.contentView=contentView;
		// StateLayout inflate之后就有3个状态的View了，还需要第四种状态
		FrameLayout view = (FrameLayout) stateLayout.findViewById(R.id.fl_layout_root);
		view.addView(contentView);
		//当添加完成后暂时给隐藏掉
		contentView.setVisibility(View.GONE);
		return stateLayout;
	}

	
	
	/**
	 * 渲染完毕后展示获取view对象
	 */
	@Override
	protected void onFinishInflate() {
		loadingView = findViewById(R.id.loadingView);
		failView = findViewById(R.id.failView);
		emptyView = findViewById(R.id.emptyView);

		showLoadingView();
	}
	
	/** 显示正在加载的View */
	public void showLoadingView() {
		showView(loadingView);
	}
	
	/** 显示失败的View */
	public void showFailView() {
		showView(failView);
	}
	
	/** 显示加载为空的View */
	public void showEmptyView() {
		showView(emptyView);
	}
	
	/** 显示正常界面的View */
	public void showContentView() {
		showView(contentView);
	}

	/** 
	 * 显示指定的View，并且隐藏其它的View
	 * @param view 指定要显示的View
	 */
	private void showView(View view) {

		//获取包含布局的frameLayout
		FrameLayout childAt = (FrameLayout) getChildAt(1);
		for (int i = 0; i <childAt.getChildCount(); i++) {
			View child = childAt.getChildAt(i);	//
			child.setVisibility(view == child ? View.VISIBLE : View.GONE);

		}
	}


	/**
	 * 获取状态栏的高度
	 * @return
	 */
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public void showStatusBar(boolean isShow,int showColor ){
		//增加自定义的StatusBar
		this.isShow = isShow;
		if (isShow){
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
				LinearLayout statusBar = (LinearLayout) getChildAt(0);
				statusBar.getLayoutParams().height = getStatusBarHeight();
				statusBar.setBackgroundColor(showColor);
				statusBar.requestLayout();

			}
		}
	}
	
}
