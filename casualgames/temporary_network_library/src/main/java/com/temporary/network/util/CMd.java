package com.temporary.network.util;

public class CMd {


    /****
     *
     * 输出
     *
     * *******/
    public static void syo(String str) {
        System.out.println(str);
    }


    /****
     *
     * 解析json串，当串的value值含有英文的双引号会导致解析出错，所以将value里面的英文双引号替换成中文的双引号
     *
     *
     * ********/
    public static String jsonString(String s){
        char[] temp = s.toCharArray();
        int n = temp.length;
        for(int i =0;i<n;i++){
            if(temp[i]==':'&&temp[i+1]=='"'){
                for(int j =i+2;j<n;j++){
                    if(temp[j]=='"'){
                        if(temp[j+1]!=',' &&  temp[j+1]!='}'){
                            temp[j]='”';
                        }else if(temp[j+1]==',' ||  temp[j+1]=='}'){
                            break ;
                        }
                    }
                }
            }
        }
        return new String(temp);
    }
}
