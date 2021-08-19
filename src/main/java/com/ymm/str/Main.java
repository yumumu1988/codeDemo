package com.ymm.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String str = "abccccddc";
        longestPalindrome(str);
        String str2 = "A man, a plan, a canal: Panama";
        checkPalindrome(str2);
        checkPalindrome2(str2);
        getLongestPalindrome("1232bcdabcdcba21");
        System.out.println("-=-=");
        getLongestPalindrome("cbbd");
        System.out.println("-=-=");
        getLongestPalindrome("babad");
        System.out.println("-=-=");
        getLongestPalindromeSequence("bbbab");
        getDeep("()((()))()");
        getDeep("((()))");
        getDeep("()()()");
        getDeep("((()(())))");
        getDeep2("()((()))()");
        getDeep2("((()))");
        getDeep2("()()()");
        getDeep2("((()(())))");
        stringToNumber("-00213");
        stringToNumber("-987");
        stringToNumber("-98a7");
        stringToNumber("56789");
        stringToNumber("0089");
        stringToNumber("+00129");
        stringToNumber("+6399");
    }

    /**
     *  根据输入的字符串判断可以组成的最长回文串
     *  思路：判断有多少个成对字符。类似于判断是否已经存在某字符，利用set进行判断。
     */
    private static void longestPalindrome(String str) {
        char[] chars = str.toCharArray();
        HashSet<Character> hashSet = new HashSet<>();
        int count = 0;
        for (char aChar : chars) {
            if (!hashSet.contains(aChar)) {
                hashSet.add(aChar);
            } else {
                hashSet.remove(aChar);
                count++;
            }
        }
        count = hashSet.size() == 0 ? count * 2 : count * 2 + 1;
        System.out.println("可构成的最长回文串长度：" + count);
    }


    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 思路：通过Character.isLetterOrDigit(char)判读是否是字符串或数字重组字符串。依次对比字符串的收尾字符。
     */
    private static void checkPalindrome(String str) {
        str = str.toLowerCase();
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char aChar : chars) {
            if (Character.isLetterOrDigit(aChar)) {
                list.add(aChar);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (i > list.size() - i - 1) {
                break;
            }
            Character front = list.get(i);
            Character behind = list.get(list.size() - i - 1);
            if (!front.equals(behind)) {
                System.out.println("字符串：" + str + "不是回文串");
                return;
            }
        }
        System.out.println("字符串：" + str + "是回文串");
    }

    /**
     *  思路：前后指针依次移动比对。
     */
    private static void checkPalindrome2(String str) {
        str = str.toLowerCase();
        char[] chars = str.toCharArray();
        int front = 0;
        int behind = str.length() - 1;
        while (front < behind) {
            if (!Character.isLetterOrDigit(chars[front])) {
                front++;
            } else if (!Character.isLetterOrDigit(chars[behind])) {
                behind--;
            } else if (chars[front] == chars[behind]) {
                front++;
                behind--;
            } else {
                System.out.println("字符串：" + str + "不是回文串");
                return;
            }
        }
        System.out.println("字符串：" + str + "是回文串");
    }

    /**
     * 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     *
     */
    static int start = 0, end = 0;
    private static void getLongestPalindrome(String str) {
        char[] chars = str.toCharArray();
        int behind = chars.length - 1;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            t1(chars, i, behind);
            if (start > 0 || end > 0) {
                String substring = str.substring(start, end + 1);
                list.add(substring);
                System.out.println("回文串：" + substring);
                start = 0;
                end = 0;
            }
        }
        list.sort((a,b)-> -(a.length() - b.length()));
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                System.out.println("最大回文串：" + list.get(i));
                continue;
            }
            if (list.get(i).length() == list.get(i - 1).length()) {
                System.out.println("最大回文串：" + list.get(i));
            } else {
                break;
            }
        }
    }

    private static void t1(char[] chars,int s, int e) {
        int front = s;
        int behind = e;
        while (behind > front) {
            if (chars[front] == chars[behind]) {
                if (start == 0) {
                    start = front;
                }
                if (end == 0) {
                    end = behind;
                }
                front++;
                behind--;
            } else {
                behind--;
                front = s;
                start = 0;
                end = 0;
            }
        }
    }

    /**
     *  最长回文子序列 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
     */
    public static void getLongestPalindromeSequence(String s) {
//        bbbab
//        char[] chars = str.toCharArray();
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (char aChar : chars) {
//            Integer value = hashMap.get(aChar);
//            if (value != null) {
//                hashMap.put(aChar, 1);
//            } else {
//                hashMap.put(aChar, value + 1);
//            }
//        }
//        int max = 0;
//        for (Integer value: hashMap.values()) {
//            if (value % 2 == 0) {
//                max += value;
//            }
//        }
//        if (max == 0) {
//            System.out.println("无最长会问子序列");
//            return;
//        }
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i>=0; i--){
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        System.out.println("最长回文子串：" + dp[0][len-1]);
    }

    /**
     * 输入包括一个合法的括号序列s,s长度length(2 ≤ length ≤ 50),序列中只包含'('和')'。
     * 输出一个正整数,即这个序列的深度。
     */
    public static void getDeep(String str) {
        char[] chars = str.toCharArray();
        int deep = 0;
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put('(', ')');
        hashMap.put(')', '(');
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (list.size() == 0) {
                list.add(chars[i]);
                continue;
            }
            if (list.get(list.size() - 1).equals(hashMap.get(chars[i]))) {
                deep = Math.max(deep, list.size());
                list.remove(list.size() - 1);
            } else {
                list.add(chars[i]);
            }
        }
        System.out.println("这个序列的深度：" + deep);
    }

    public static void getDeep2(String str) {
        char[] chars = str.toCharArray();
        int deep = 0;
        int max = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                deep++;
            } else {
                max = Math.max(max, deep--);
            }
        }
        System.out.println("这个序列的深度(2)：" + max);
    }

    /**
     * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，
     * 但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。
     * 数值为0或者字符串不是一个合法的数值则返回0
     */
    public static void stringToNumber(String str) {
        char[] chars = str.toCharArray();
        Boolean positive = null;
        if (chars[0] == '-') {
            positive = false;
        } else if (chars[0] == '+') {
            positive = true;
        } else if (!Character.isDigit(chars[0])) {
            System.out.println("字符串：" + 0);
            return;
        }
        int value = 0;
        boolean first = true;
        for (int i = positive == null ? 0 : 1; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                System.out.println("字符串：" + 0);
                return;
            }
            if (first) {
                value = chars[i] - '0';
                first = false;
                continue;
            }
            value = 10 * value + chars[i] - '0';
        }
        if (positive == null || positive) {
            System.out.println("字符串：" + value);
        } else {
            System.out.println("字符串：" + value * -1);
        }
    }
}
