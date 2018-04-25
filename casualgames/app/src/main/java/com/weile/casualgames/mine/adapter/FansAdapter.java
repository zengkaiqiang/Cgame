package com.weile.casualgames.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.mine.model.FansVo;

import java.util.ArrayList;

public class FansAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FansVo> detailList;

    private ViewHolder holder;

    public FansAdapter(Context context, ArrayList<FansVo> detailsList) {
        mContext = context;
        detailList = new ArrayList<FansVo>();

    }


    public void initItem(ArrayList<FansVo> morelist) {
        this.detailList = morelist;
        notifyDataSetChanged();

    }

    /**
     * 上拉加载更多的数据
     *
     * @param morelist
     */
    public void addMoreItems(ArrayList<FansVo> morelist) {
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
    public FansVo getItem(int position) {

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
        TextView signTV;
        Button isFollowBtn;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder holder = null;
            final FansVo details = getItem(position);
//            CLog.syo("航点适配器传进来的值="+details.toString());
            final int p = position;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.listitem_fans, null);
                holder = new ViewHolder();


                holder.nameTV = (TextView) convertView
                        .findViewById(R.id.tv_name);
                holder.signTV = (TextView) convertView
                        .findViewById(R.id.tv_sign);

                holder.isFollowBtn = (Button) convertView
                        .findViewById(R.id.btn_follow);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nameTV.setText(details.getName());
            holder.signTV.setText(details.getSign());

            if (details.isFollow()) {
                setBtnValue(true, holder.isFollowBtn);
            } else {
                setBtnValue(false, holder.isFollowBtn);
            }
            final Button selectBtn = holder.isFollowBtn;
            holder.isFollowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (details.isFollow()) {
                        setBtnValue(false, selectBtn);
                        details.setFollow(false);
                    } else {
                        setBtnValue(true, selectBtn);
                        details.setFollow(true);
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
            btn.setText("已关注");
        } else {
            btn.setBackground(mContext.getResources().getDrawable(R.mipmap.but_attention_pressed));
            btn.setText("关注");
        }
    }

}
