package com.example.qqq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FindpageFragment extends android.support.v4.app.Fragment implements OnClickListener {
	@SuppressWarnings("deprecation")
	LocalActivityManager manager = null;
	ViewPager mViewPager;
	private LinearLayout linearLayout1;
	private TextView textView1, textView2, textView3,textView4;
	private int currIndex = 0;// ��ǰҳ�����
	private ImageView imageView;// ҳ�����⶯��ͼƬ
	private int textViewW = 0;
	private List<View> listViews;
	private Resources resources;
	private View view1, view2, view3,view4,view;
	Context context;
	/* mList���������Ҫ��ʾ����� */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		  view = inflater.inflate(R.layout.findpage_fragment,container,false);
		      context=getActivity();
		      manager = new LocalActivityManager(getActivity(), true);
		        manager.dispatchCreate(savedInstanceState);
			resources = this.getResources();
			initControl();
			initViewPager();
			InitTextView();
			InitImageView();
		
		return view;
		
	}
	private void initControl() {
		imageView = (ImageView) view.findViewById(R.id.cursor2);
		linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayout2);
		mViewPager = (ViewPager) view.findViewById(R.id.viewpage_notification2);
		mViewPager.setOffscreenPageLimit(3);/* Ԥ����ҳ�� */
	}

	/* ��ʼ��ViewPager */
	@SuppressLint("InflateParams")
	private void initViewPager() {
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getActivity().getLayoutInflater();
		
		
		Intent intent = new Intent(context, EassyAusles_Page.class);
		listViews.add(getView("A", intent));
        Intent intent2 = new Intent(context, Exppage.class);
        listViews.add(getView("B", intent2));
        Intent intent3 = new Intent(context, GossipTalkActivity.class);
        listViews.add(getView("C", intent3));
        Intent intent4 = new Intent(context, EassySkillsActivity.class);
        listViews.add(getView("D", intent4));
        
		mViewPager.setAdapter(new MyPagerAdapter(listViews));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
	}

	/* ��ʼ������ҳ���б� */
	 private View getView(String id, Intent intent) {
	        return manager.startActivity(id, intent).getDecorView();
	    }

	/* ��ʼ��ҳ������ */
	private void InitTextView() {
		textView1 = (TextView) view.findViewById(R.id.text1);
		textView2 = (TextView) view.findViewById(R.id.text2);
		textView3 = (TextView) view.findViewById(R.id.Interview2);
		textView4 = (TextView) view.findViewById(R.id.Failed);
		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
		textView4.setOnClickListener(new MyOnClickListener(3));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/**
	 * ViewPager������
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/* ҳ���л����� */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			if (textViewW == 0) {
				textViewW = textView1.getWidth();
			}
			Animation animation = new TranslateAnimation(textViewW * currIndex,
					textViewW * arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);/* True:ͼƬͣ�ڶ�������λ�� */
			animation.setDuration(300);
			imageView.startAnimation(animation);
			setTextTitleSelectedColor(arg0);
			setImageViewWidth(textViewW);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/* ���ñ����ı�����ɫ */
	private void setTextTitleSelectedColor(int arg0) {
		int count = mViewPager.getChildCount();
		for (int i = 0; i < count; i++) {
			TextView mTextView = (TextView) linearLayout1.getChildAt(i);
			if (arg0 == i) {
				mTextView.setTextColor(0xffc80000);
			} else {
				mTextView.setTextColor(0xff969696);
			}
		}
	}

	/* ����ͼƬ��� */
	private void setImageViewWidth(int width) {
		if (width != imageView.getWidth()) {
			LayoutParams laParams = (LayoutParams) imageView.getLayoutParams();
			laParams.width = width;
			imageView.setLayoutParams(laParams);
		}
	}

	/* ���������� */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			mViewPager.setCurrentItem(index);
		}
	}

	/* ��ʼ������ */
	private void InitImageView() {
		Matrix matrix = new Matrix();
		matrix.postTranslate(0, 0);
		imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
	}

}
