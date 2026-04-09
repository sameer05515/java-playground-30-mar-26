package com.p.java8.array;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;

@Service
public class ArrayService {
    public int[] sortedDescending(int[] arr) {
        return Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
    }

    public int[] sorted(int[] arr) {
        return Arrays.stream(arr).sorted().toArray();
    }

    public int[] getOdd(int[] arr) {
        return Arrays.stream(arr).filter(i -> i % 2 != 0).toArray();
    }

    public int[] getEven(int[] arr) {
        return Arrays.stream(arr).filter(i -> i % 2 == 0).toArray();
    }

    //        Solve this:
//
//🔥 Problem 4: Maximum Subarray (Kadane’s Algorithm)
//
//        Input: [-2,1,-3,4,-1,2,1,-5,4]
//        Output: 6 (subarray [4,-1,2,1])

    public int kadaneAlgorithm(int[] arr) {
        int maxSoFar = arr[0];
        int curr = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            maxSoFar = Math.max(maxSoFar, curr);
        }
        return maxSoFar;
    }
}
