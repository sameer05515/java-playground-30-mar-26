package com.coding.practice.interview.questions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAnagrams {
  public static void main(String[] args) {
    //    Input: ["eat","tea","tan","ate","nat","bat"]
    //    Output: [["eat","tea","ate"],["tan","nat"],["bat"]]

    String[] anagrams = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};

    List<String> input = Arrays.asList(anagrams);

    Collection<List<String>> result =
        input.stream()
            .collect(
                Collectors.groupingBy(
                    word -> {
                      char[] chars = word.toCharArray();
                      Arrays.sort(chars); // sorted chars as key
                      return new String(chars);
                    }))
            .values();

    System.out.println(result);

    var data =
        input.stream()
            .collect(
                Collectors.groupingBy(
                    word -> {
                      char[] chars = word.toCharArray();
                      Arrays.sort(chars); // sorted chars as key
                      return new String(chars);
                    }));

    System.out.println(data);
  }
}
