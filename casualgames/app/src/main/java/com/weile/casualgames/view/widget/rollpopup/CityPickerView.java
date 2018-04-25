package com.weile.casualgames.view.widget.rollpopup;

import android.content.Context;
import android.content.res.AssetManager;


import com.weile.casualgames.view.widget.rollpopup.listener.OnCitySelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class CityPickerView extends OptionsPickerView {

    private final Context mContext;

    // 省数据集合
    private ArrayList<String> mListProvince = new ArrayList<>();
    // 市数据集合
    private ArrayList<ArrayList<String>> mListCity = new ArrayList<>();
    // 区数据集合
    private ArrayList<ArrayList<ArrayList<String>>> mListArea = new ArrayList<>();
    private JSONObject mJsonObj;

    public CityPickerView(Context context) {
        super(context);
        mContext = context;
        // 初始化Json对象
        initJsonData();
        // 初始化Json数据
        initJsonDatas();
        initCitySelect();
    }

    private void initCitySelect() {
        setTitle("选择城市");
        setPicker(mListProvince, mListCity, mListArea, true);
        setCyclic(false, false, false);
        setSelectOptions(0, 0, 0);
        setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                if(mOnCitySelectListener != null){
                    if(mListCity.size() > option1 && mListCity.get(option1).size() > option2){
                        if(mListArea.size() > option1 && mListArea.get(option1).size() > option2
                                && mListArea.get(option1).get(option2).size() > option3){
                            String prov = mListProvince.get(option1);
                            String city = mListCity.get(option1).get(option2);
                            String area = mListArea.get(option1).get(option2).get(option3);
                            mOnCitySelectListener.onCitySelect(prov.concat(city).concat(area));
                            mOnCitySelectListener.onCitySelect(prov, city, area);
                        }
                    }
                }
            }
        });
    }

    /** 从assert文件夹中读取省市区的json文件，然后转化为json对象 */
    private void initJsonData() {
        AssetManager assets = mContext.getAssets();
        try {
            InputStream is = assets.open("city.json");
            byte[] buf = new byte[is.available()];
            is.read(buf);
            String json = new String(buf, "UTF-8");
            mJsonObj = new JSONObject(json);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取最后的字符串
     * */
    public  String getAllStr(int option1, int option2, int option3)
    {
       String str= mListProvince.get(option1)+" ";
       str+=mListCity.get(option1).get(option2)+" ";
        str+=mListArea.get(option1).get(option2).get(option3);
        return str;
    }

    /**
     * 获取最后的省
     * */
    public  String getProvinceStr(int option1)
    {
        return mListProvince.get(option1);
    }

    /**
     * 获取最后的市
     * */
    public  String getCityStr(int option1, int option2)
    {
        return mListCity.get(option1).get(option2);
    }

    /**
     * 获取最后的区
     * */
    public  String getAreaStr(int option1, int option2, int option3)
    {
        return mListArea.get(option1).get(option2).get(option3);
    }

    /** 初始化Json数据，并释放Json对象 */
    private void initJsonDatas(){
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 获取每个省的Json对象
                String province = jsonP.getString("name");

                ArrayList<String> options2Items_01 = new ArrayList<>();
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<>();
                JSONArray jsonCs = jsonP.getJSONArray("city");
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonC = jsonCs.getJSONObject(j);// 获取每个市的Json对象
                    String city = jsonC.getString("name");
                    options2Items_01.add(city);// 添加市数据

                    ArrayList<String> options3Items_01_01 = new ArrayList<>();
                    JSONArray jsonAs = jsonC.getJSONArray("area");
                    for (int k = 0; k < jsonAs.length(); k++) {
                        options3Items_01_01.add(jsonAs.getString(k));// 添加区数据
                    }
                    options3Items_01.add(options3Items_01_01);
                }
                mListProvince.add(province);// 添加省数据
                mListCity.add(options2Items_01);
                mListArea.add(options3Items_01);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    public OnCitySelectListener mOnCitySelectListener;

    public void setOnCitySelectListener(OnCitySelectListener listener) {
        this.mOnCitySelectListener = listener;
    }
}
