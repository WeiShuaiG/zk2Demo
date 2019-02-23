package com.umeng.soexample.zk2demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.zk2demo.adapter.GjzAdapter;
import com.umeng.soexample.zk2demo.entity.Gjz;
import com.umeng.soexample.zk2demo.iview.IView;
import com.umeng.soexample.zk2demo.presenter.PresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView, XRecyclerView.LoadingListener {

    @BindView(R.id.shou_gjc_fen)
    ImageView shouGjcFen;
    @BindView(R.id.shou_gjc_sou)
    EditText shouGjcSou;
    @BindView(R.id.gjz_sou)
    TextView gjzSou;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shou_gjz_recy)
    XRecyclerView shouGjzRecy;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.meiyou)
    TextView meiyou;
    private GjzAdapter adapter;
    private PresenterImpl presenter;
    private int count = 6;
    private int page = 1;
    private List<Gjz.ResultBean> mList = new ArrayList<>();
    private Bundle bundle;
    private HashMap<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new PresenterImpl(this);
        adapter = new GjzAdapter(mList, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        shouGjzRecy.setLayoutManager(staggeredGridLayoutManager);
        shouGjzRecy.setAdapter(adapter);
        shouGjzRecy.setLoadingListener(this);
        shouGjzRecy.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        shouGjzRecy.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
        bundle = new Bundle();
        adapter.setClick(new GjzAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int positon, int id) {
                Gjz gjz = new Gjz();
                Gjz.ResultBean bean = new Gjz.ResultBean();

                bean.setSaleNum(mList.get(positon).getSaleNum());
                bean.setPrice(mList.get(positon).getPrice());
                bean.setCommodityId(mList.get(positon).getCommodityId());
                bean.setCommodityName(mList.get(positon).getCommodityName());
                bean.setMasterPic(mList.get(positon).getMasterPic());
                bundle.putParcelable("bean", bean);
                EventBus.getDefault().postSticky(gjz);
                Intent intent = new Intent(MainActivity.this, XiangqingActivity.class);
                intent.putExtra("Bean", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(Object success) {
        Gjz gjz = (Gjz) success;
        List<Gjz.ResultBean> list = gjz.getResult();
        Log.e("tag", list.size() + "");
        if (list.size() > 0) {

            mList.clear();
            mList.addAll(list);
            adapter.notifyDataSetChanged();
            shouGjzRecy.setVisibility(View.VISIBLE);
            msg.setVisibility(View.GONE);
        } else {
            mList.clear();

            shouGjzRecy.setVisibility(View.GONE);
            msg.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public void error(Object error) {

    }

    @OnClick(R.id.gjz_sou)
    public void onViewClicked() {
        String keyword = shouGjcSou.getText().toString().trim();
        map.put("count", count + "");
        map.put("page", page + "");
        map.put("keyword", keyword + "");
        presenter = new PresenterImpl(this);
        presenter.onGetRequest(Contacts.SHOU_GJZ, map, null, Gjz.class);

    }

    @Override
    public void onRefresh() {
        page = 1;
        mList.clear();
        String keyword = shouGjcSou.getText().toString().trim();
        map.put("keyword", keyword + "");
        map.put("count", count + "");
        map.put("page", page + "");
        presenter.onGetRequest(Contacts.SHOU_GJZ, map, null, Gjz.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shouGjzRecy.refreshComplete();
            }
        }, 2000);

    }

    @Override
    public void onLoadMore() {
        page++;
        String keyword = shouGjcSou.getText().toString().trim();
        map.put("keyword", keyword + "");
        map.put("count", count + "");
        map.put("page", page + "");
        presenter.onGetRequest(Contacts.SHOU_GJZ, map, null, Gjz.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shouGjzRecy.refreshComplete();
            }
        }, 2000);


    }
}
