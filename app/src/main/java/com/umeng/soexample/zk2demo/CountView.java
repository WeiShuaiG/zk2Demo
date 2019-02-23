package com.umeng.soexample.zk2demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by W on 2019/1/12 10:58.
 */

public class CountView extends RelativeLayout implements View.OnClickListener {

    private int mnumber = 0;
    private TextView send_count_minus;
    private TextView send_count_number;
    private TextView send_count_plus;

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.add_remove_view, this);
        initView();
    }

    private void initView() {
        send_count_minus = findViewById(R.id.delete_tv);
        send_count_number = findViewById(R.id.product_number_tv);
        send_count_plus = findViewById(R.id.add_tv);
        send_count_minus.setOnClickListener(this);
        send_count_plus.setOnClickListener(this);
    }

    public void setNumber(int number) {
        mnumber = number;
        send_count_number.setText(mnumber + "");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_tv:
                if (mnumber > 0) {
                    mnumber--;
                    send_count_number.setText(mnumber + "");
                    if (onChangeNumber != null) {
                        onChangeNumber.getNumber(mnumber);
                    }
                }
                break;
            case R.id.add_tv:
                mnumber++;
                send_count_number.setText(mnumber + "");
                if (onChangeNumber != null) {
                    onChangeNumber.getNumber(mnumber);
                }
                break;
        }
    }

    public interface OnChangeNumber {
        void getNumber(int number);
    }

    private OnChangeNumber onChangeNumber;

    public void setOnChangeNumber(OnChangeNumber onChangeNumber) {
        this.onChangeNumber = onChangeNumber;
    }
}
