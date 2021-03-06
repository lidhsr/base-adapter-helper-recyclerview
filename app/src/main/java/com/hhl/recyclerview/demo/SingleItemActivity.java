package com.hhl.recyclerview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.hhl.adapter.BaseAdapterHelper;
import com.hhl.adapter.QuickAdapter;
import com.hhl.recyclerview.demo.entity.Person;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/9/7.
 */
public class SingleItemActivity extends AppCompatActivity {

    private QuickAdapter<Person> mQuickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        getSupportActionBar().setTitle("单一类型的Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mQuickAdapter = new QuickAdapter<Person>(this, R.layout.single_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Person item) {
                helper.setText(R.id.tv_username, item.getUsername())
                        .setText(R.id.tv_age, item.getAge() + "岁")
                        .setText(R.id.tv_desc, item.getDesc());
                ImageLoader.getInstance().displayImage(item.getAvatar(), helper.getImageView(R.id.iv_avatar));
            }
        };

        recyclerView.setAdapter(mQuickAdapter);

        initData();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setUsername("小明测试" + i);
            person.setAge(i);
            person.setDesc("我是一名小学生，今年刚上3年级，我的理想是当一名科学家！");
            if (i % 4 == 0) {
                person.setAvatar("http://f.hiphotos.baidu.com/image/pic/item/cdbf6c81800a19d8218dbbd436fa828ba71e4650.jpg");
            } else if (i % 4 == 1) {
                person.setAvatar("http://i7.hexunimg.cn/2015-08-18/178405037.jpg");
            } else if (i % 4 == 2) {
                person.setAvatar("http://img3.cache.netease.com/photo/0003/2015-08-11/B0NTII0000B70003.jpg");
            } else if (i % 4 == 3) {
                person.setAvatar("http://img5.cache.netease.com/hebei/2015/8/17/20150817152654f2082_500.jpg");
            }
            list.add(person);
        }
        mQuickAdapter.addAll(list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
