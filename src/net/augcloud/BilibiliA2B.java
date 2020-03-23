package net.augcloud;


import java.io.IOException;
import java.util.HashMap;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/23 15:56
 * @description：JAVA Bilibili爬虫需要用到
 * @version: 1.0
 */
public class BilibiliA2B {
    
    private static String result = null;
    private static String table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
    private static int[] s = {11,10,3,8,4,6};
    private static int xor = 177451812;
    private static long add = 8728348608L;
    private static HashMap<String,Integer> number2Word;
    
    static{
        number2Word = new HashMap<>();
        for(int i = 0,size = table.length();i<size;i++){
            number2Word.put(getIndex(table,i),i);
        }
    }
    
    private static String topOffIndex(String before,String updated,int index){
      return  before.substring(0,index)+updated+before.substring(index+1);
    }
    
    private static String getIndex(String string,int index){
        return string.substring(index,index+1);
    }
    
    
    /**
     * av转bv
     *
     * @time 2020-03-24 3:58
     * @param x av号
     * @return: bv号
     **/
    public static String enc(long x) {
        
        x = (x^xor)+add;
        String r = "BV1  4 1 7  ";
        
        for (int i = 0; i < 6; i++) {
            r = topOffIndex(r,getIndex(table,  Integer.valueOf((int) ((x/Math.pow (58,i))%58))),s[i]);
          
        }
        return r;
    }
    
    /**
     * bv转av
     *
     * @time 2020-03-24 3:58
     * @param x bv号
     * @return: av号
    **/
    public static long dec(String x){
        long r = 0;
        for (int i = 0; i < 6; i++) {
            r+=(long)number2Word.get(
                    getIndex(x, s[i]))*(long)Math.pow(58,i);
        }
        return (r-add)^xor;
    }
    
    /**
     * 主方法
     *
     * @time 2020-03-24 3:57
     * @param args 传参
     * @return:
    **/
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(enc(170001));
        System.out.println(dec(enc(170001)));
    }
}
