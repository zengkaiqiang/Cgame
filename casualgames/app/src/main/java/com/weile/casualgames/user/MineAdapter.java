package com.weile.casualgames.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weile.casualgames.R;


/**
 * “我的“ 界面grigVIew Adapter
 */
public class MineAdapter extends BaseAdapter {
    private Context mContext;
    private String[] titles;
    private int[] icons;
//    private LayoutInflater mInflater;

    public MineAdapter(Context mContext) {
        this.mContext = mContext;
//        mInflater = LayoutInflater.from(mContext);
        titles = new String[]{"我的主页", "我关注的", "我的消息", "点赞记录", "排行榜", "建议反馈"};
        icons = new int[]{R.mipmap.ic_game_contact, R.mipmap.ic_game_contact,
                R.mipmap.ic_game_contact, R.mipmap.ic_game_contact,
                R.mipmap.ic_game_contact, R.mipmap.ic_game_contact};
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MineItemHolder holder = null;
        if (convertView == null) {
            holder = new MineItemHolder();
            convertView = View.inflate(mContext, R.layout.mine_item, null);
            holder.mine_item_tv = (TextView) convertView.findViewById(R.id.mine_item_tv);
            holder.mine_item_img = (ImageView) convertView.findViewById(R.id.mine_item_img);
            holder.unread_img = (ImageView) convertView.findViewById(R.id.unread_img);
            convertView.setTag(holder);
        } else {
            holder = (MineItemHolder) convertView.getTag();
        }
        holder.mine_item_tv.setText(titles[position]);
        holder.mine_item_img.setImageDrawable(mContext.getResources().getDrawable(icons[position]));

//        if (position == 1 && AppContext.sHaveTopicsByUFollowings) {
//            holder.unread_img.setVisibility(View.VISIBLE);
//        } else {
//            holder.unread_img.setVisibility(View.GONE);
//        }

//        if (position == 2 && AppContext.hasNoticeUnread) {
//            holder.unread_img.setVisibility(View.VISIBLE);
//        } else {
//            holder.unread_img.setVisibility(View.GONE);
//        }
        return convertView;
    }

    class MineItemHolder {
        ImageView mine_item_img;
        ImageView unread_img;
        TextView mine_item_tv;
    }
}
