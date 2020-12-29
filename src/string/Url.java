package string;

import java.util.Arrays;

/**
 * description 编写一个url解析程序，要求在各种情况下解析出协议头，域名，路径，端口号，查询参数，位置标识，
 * 例如：https://www.uvideo.com:8888/watch?v=XG0CFDPGGqc&c=browser#section1  解析结果为：
 * 协议:https
 * 域名:www.uvideo.com
 * 端口号:8888
 * 路径:/watch
 * 查询参数:v=XG0CFDPGGqc, c=browser
 * 位置标识:section1
 *
 * @author 27771
 * create 2020-12-29 16:40
 **/
public class Url {

    static class Solution {

        /**
         * url解析
         * @param netUrl string 字符串
         * @return       string 字符串一维数组
         */
        public String[] urlParsing (String netUrl) {
            String[] res = new String[6];
            int indexBegin = 0, indexEnd = netUrl.indexOf(":");
            //System.out.println(netUrl.substring(indexBegin, indexEnd));
            res[0] = netUrl.substring(indexBegin, indexEnd);

            netUrl = netUrl.substring(indexEnd + 3);
            //System.out.println(netUrl);

            indexEnd = netUrl.indexOf(":");
            //System.out.println(netUrl.substring(indexBegin, indexEnd));
            res[1] = netUrl.substring(indexBegin, indexEnd);

            netUrl = netUrl.substring(indexEnd + 1);
            //System.out.println(netUrl);

            indexEnd = netUrl.indexOf("/");
            //System.out.println(netUrl.substring(indexBegin, indexEnd));
            res[2] = netUrl.substring(indexBegin, indexEnd);

            netUrl = netUrl.substring(indexEnd);
            //System.out.println(netUrl);

            indexEnd = netUrl.indexOf("?");
            //System.out.println(netUrl.substring(indexBegin, indexEnd));
            res[3] = netUrl.substring(indexBegin, indexEnd);

            netUrl = netUrl.substring(indexEnd + 1);
            //System.out.println(netUrl);

            indexEnd = netUrl.indexOf("#");
            //System.out.println(netUrl.substring(indexBegin, indexEnd));
            res[4] = netUrl.substring(indexBegin, indexEnd);

            netUrl = netUrl.substring(indexEnd + 1);
            //System.out.println(netUrl);

            res[5] = netUrl;

            return res;
        }
    }

    public static void main(String[] args) {
        String netUrl = "https://www.uvideo.com:8888/watch?v=XG0CFDPGGqc&c=browser#section1";
        String[] res = new Solution().urlParsing(netUrl);
        System.out.println(Arrays.toString(res));
    }
}