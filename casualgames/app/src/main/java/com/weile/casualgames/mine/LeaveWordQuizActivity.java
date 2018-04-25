package com.weile.casualgames.mine;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.weile.casualgames.MainActivity;
import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.DragLinearView;
import com.weile.casualgames.view.widget.draglinearutil.ImageLoaderUtil;

import java.util.LinkedList;


public class LeaveWordQuizActivity extends BaseActivity implements View.OnClickListener {

    private DragLinearView dragLinearView;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_leave_word_quiz);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        initFindId();
initValue();

    }



    public void initFindId() {
        dragLinearView = (DragLinearView) findViewById(R.id.dragLinerView);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;

        }
    }

    public void initValue()
    {
        //设置最大行数
        dragLinearView.setMaxRows(1);
        //设置一行的个数
        dragLinearView.setMaxRowsItemCount(4);
        dragLinearView.setITEM_SPACE(20);

        dragLinearView.setOnAddClickListener(new DragLinearView.OnAddClickListener() {
            @Override
            public void onAddClick() {
                dragLinearView.addDelayItemView(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_game_double_a),null);
            }
        });
        dragLinearView.setOnItemViewListener(new DragLinearView.OnItemViewListener() {
            @Override
            public void onAddItem(ImageView imageView, Object tag) {
//                Toast.makeText(MainActivity.this,"添加成功的回调",Toast.LENGTH_SHORT).show();
                if(tag!=null && !TextUtils.isEmpty(tag.toString())){
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
                    ImageLoaderUtil.displayImage(LeaveWordQuizActivity.this,tag.toString(),imageView,displayImageOptions);
                }
            }

            @Override
            public void onItemClick(View itemView, Object tag) {
                Toast.makeText(LeaveWordQuizActivity.this,"点击",Toast.LENGTH_SHORT).show();
            }
        });


}

    /**
     * 一次性添加多张图片
     * @param view
     */
    public void addMutilImgNoAnimClick(View view){
        dragLinearView.removeAllItemView();
        LinkedList<DragLinearView.ImageTagElement> imageTagElementList = new LinkedList<DragLinearView.ImageTagElement>();
        for(int i=0;i<8;i++){
            imageTagElementList.add(new DragLinearView.ImageTagElement(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_game_double_a),null));
        }
        dragLinearView.addMutilItemView(imageTagElementList,false);
    }

    /**
     * 一次性添加多张图片
     * @param view
     */
    public void addMutilImgClick(View view){
        dragLinearView.removeAllItemView();
        LinkedList<DragLinearView.ImageTagElement> imageTagElementList = new LinkedList<DragLinearView.ImageTagElement>();
        for(int i=0;i<8;i++){
            imageTagElementList.add(new DragLinearView.ImageTagElement(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_game_double_a),null));
        }
        dragLinearView.addMutilItemView(imageTagElementList);
    }

    /**
     * 异步加载图片
     * @param view
     */
    public void addAsyncMutilImgClick(View view){
        dragLinearView.removeAllItemView();
        LinkedList<DragLinearView.ImageTagElement> imageTagElementList = new LinkedList<DragLinearView.ImageTagElement>();
        String url = "http://www.iyi8.com/uploadfile/2014/1102/20141102084643851.jpg";
        for(int i=0;i<8;i++){
            imageTagElementList.add(new DragLinearView.ImageTagElement(null,url));
        }
        dragLinearView.addMutilItemView(imageTagElementList);
    }



}
