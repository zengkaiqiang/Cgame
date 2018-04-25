package com.weile.casualgames.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.mine.model.BlackListVo;
import com.weile.casualgames.view.widget.RoundImageView;

import java.util.ArrayList;

public class BlackListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<BlackListVo> detailList;

    private ViewHolder holder;

    public BlackListAdapter(Context context, ArrayList<BlackListVo> detailsList) {
        mContext = context;
        detailList = new ArrayList<BlackListVo>();

    }


    public void initItem(ArrayList<BlackListVo> morelist) {
        this.detailList = morelist;
        notifyDataSetChanged();

    }

    /**
     * 上拉加载更多的数据
     *
     * @param morelist
     */
    public void addMoreItems(ArrayList<BlackListVo> morelist) {
        for (int i = 0; i < morelist.size(); i++) {
            this.detailList.add(morelist.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return detailList == null ? 0 : detailList.size();
    }

    @Override
    public BlackListVo getItem(int position) {

        return detailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unused")
    private int count = 0;
    @SuppressWarnings("unused")
    private long sum = 0L;

    @Override
    public int getViewTypeCount() {
        return 1;

    }

    static class ViewHolder {


        TextView nameTV;
        RoundImageView roundImageView;
        Button isSelectBtn;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder holder = null;
            final BlackListVo details = getItem(position);
//            CLog.syo("航点适配器传进来的值="+details.toString());
            final int p = position;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.listitem_blacklist, null);
                holder = new ViewHolder();


                holder.nameTV = (TextView) convertView
                        .findViewById(R.id.tv_user_name);
                holder.roundImageView = (RoundImageView) convertView
                        .findViewById(R.id.iv_user_header);


                holder.isSelectBtn = (Button) convertView
                        .findViewById(R.id.btn_blacklist);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nameTV.setText(details.getName());


            if (details.isSelect()) {
                setBtnValue(true, holder.isSelectBtn);
            } else {
                setBtnValue(false, holder.isSelectBtn);
            }
            final Button selectBtn = holder.isSelectBtn;
            holder.isSelectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (details.isSelect()) {
                        setBtnValue(false, selectBtn);
                        details.setSelect(false);
                    } else {
                        setBtnValue(true, selectBtn);
                        details.setSelect(true);
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();

        }
        return convertView;
    }

    /**
     * 设置关注按钮状态改变的内容值
     */
    public void setBtnValue(boolean isSelect, Button btn) {
        if (isSelect) {
            btn.setBackground(mContext.getResources().getDrawable(R.mipmap.but_attention_normal));
            btn.setText("解除拉黑");
        } else {
            btn.setBackground(mContext.getResources().getDrawable(R.mipmap.but_blacklist_pressed));
            btn.setText("拉黑");
        }
    }

}
