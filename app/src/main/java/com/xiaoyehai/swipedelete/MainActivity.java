package com.xiaoyehai.swipedelete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.xiaoyehai.swipedelete.widget.SwipeLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * listView的item布局实现滑动删除
 * 1.应用场景：替换item长按删除
 * 2.实现逻辑:
 * a.自定义一个可以滑动的布局;
 * b.将该布局放入adapter的布局中,需要处理滑动冲突;
 */
public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        initListView();
    }

    private void initListView() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目 " + i);
        }
        mListView.setAdapter(new MyAdapter(this, list));

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    //如果垂直滑动，则需要关闭已经打开的layout
                    SwipeLayoutManager.getInstance().closeCurrentLayout();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
