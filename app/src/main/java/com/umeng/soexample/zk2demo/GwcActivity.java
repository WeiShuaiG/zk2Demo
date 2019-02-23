package com.umeng.soexample.zk2demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.umeng.soexample.zk2demo.adapter.GwcAdapter;
import com.umeng.soexample.zk2demo.entity.Querygwc;
import com.umeng.soexample.zk2demo.iview.IView;
import com.umeng.soexample.zk2demo.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GwcActivity<T> extends AppCompatActivity implements IView {

    @BindView(R.id.query_gwc)
    RecyclerView queryGwc;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.gou_heji)
    TextView gouHeji;
    @BindView(R.id.js)
    TextView js;
    private PresenterImpl presenter;
    private GwcAdapter adapter;
    private SharedPreferences sp;
    private List<Querygwc.ResultBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwc);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences("ischeck", Context.MODE_PRIVATE);
        presenter = new PresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        queryGwc.setLayoutManager(layoutManager);
        Map<String,String> headmap = new HashMap<>();
        int userId = sp.getInt("userId",0);
        String sessionId = sp.getString("sessionId",null);
        headmap.put("userId",userId+"");
        headmap.put("sessionId",sessionId);
        adapter = new GwcAdapter(mList,this);
        queryGwc.setAdapter(adapter);
        Map<String,Object> map = new HashMap<>();
        presenter.onGetRequest(Contacts.GWC_QUERY,null,headmap,Querygwc.class);

        adapter.setOnItemClick(new GwcAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                boolean status = adapter.thisCheckStatus(position);
                adapter.setCheckStatus(position, !status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onDelete(View view, int position) {
                mList.remove(position);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onNumber(int position, int number) {
                adapter.setShopCount(position, number);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = adapter.allCheckStatus();
                adapter.setAllCheckStatus(!status);
                check.setChecked(!status);
                adapter.notifyDataSetChanged();

                FlushFooter();
            }
        });
    }
    private void FlushFooter() {
        boolean status = adapter.allCheckStatus();
        check.setChecked(status);
        double allPrice = adapter.getAllPrice();
        gouHeji.setText("￥" + allPrice);
    }


    @Override
    public void success(Object success) {
        Querygwc querygwc = (Querygwc) success;
        mList.clear();
        mList.addAll(querygwc.getResult());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }
}
