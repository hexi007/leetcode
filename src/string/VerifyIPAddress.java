package string;

import java.util.Arrays;

/**
 * description 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址
 * IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
 * 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
 * IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。
 * 比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。
 * 而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。
 * 所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
 * 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。
 * 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。 同时，在 IPv6 地址中，多余的 0 也是不被允许的。
 * 比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
 * 说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。
 *
 * @author 27771
 * create 2021-09-27 10:50
 **/
public class VerifyIPAddress {

    static public class Solution {
        /**
         * 验证IP地址
         * @param IP string字符串 一个IP地址字符串
         * @return string字符串
         */
        public String solve (String IP) {
            // write code here
            String[] ips = IP.split("\\.");
            int ipv4Length = 4;
            boolean legal = true;
            if (ips.length == ipv4Length) {
                for (String ip : ips) {
                    if (ip.charAt(0) == '0' && ip.length() > 1) {
                        legal = false;
                        break;
                    }
                    try {
                        int num = Integer.parseInt(ip);
                        if (num < 0 || num > 255) {
                            legal = false;
                            break;
                        }
                    } catch (Exception e) {
                        legal = false;
                        break;
                    }

                }
                if (legal) {
                    return "IPv4";
                }
            }
            ips = IP.split(":");
            int ipv6Length = 8;
            if (ips.length == ipv6Length) {
                for (String ip : ips) {
                    if (ip.length() > 4) {
                        legal = false;
                        break;
                    }
                    if (ip.length() > 1 && ip.charAt(0) == '0' && ip.charAt(1) == '0') {
                        legal = false;
                        break;
                    }
                }
                if (legal) {
                    return "IPv6";
                }
            }
            return "Neither";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solve("1a1.4.5.6"));
    }
}