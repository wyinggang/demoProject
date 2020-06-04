package com.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Test extends Date {

    public static void main(String[] args) {
        new Test().test();

        List<String> sequences = new Test().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        System.out.println(sequences);

        int [] ints = {3,2,6,5,0,3};
        int maxProfit = new Test().maxProfit(2, ints);
        System.out.println(maxProfit);
    }

    public void test(){
        System.out.println(super.getClass().getName());
        System.out.println(getClass().getName());
        System.out.println(getClass().getSuperclass().getName());
    }



    public List<String> findRepeatedDnaSequences(String s) {

        List<String> list = new ArrayList<>();
        for (int i=0;i<=s.length()-10;i++){
            list.add(s.substring(i, i+10));
        }
        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        List<String> strings = new ArrayList<>();
        for (Map.Entry<String, Long> stringLongEntry : map.entrySet()) {
            if (stringLongEntry.getValue()>=2){
                strings.add(stringLongEntry.getKey());
            }
        }
        return strings;
    }

    public int maxProfit(int k, int[] prices) {
        int max = 0;
        List<Integer> collect = Arrays.stream(prices).boxed().collect(Collectors.toList());
        collect.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i= 0;i<=k;i++){
            max=collect.get(i)-collect.get(collect.size()-1);
        }
        return max;
    }


}
