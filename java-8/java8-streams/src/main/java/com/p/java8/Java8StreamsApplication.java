package com.p.java8;

import com.p.java8.array.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class Java8StreamsApplication implements CommandLineRunner {

    @Autowired
    private ArrayService arrayService;

    public static void main(String[] args) {
        SpringApplication.run(Java8StreamsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int[] arr = {1, 2, 5, 3, 8, 4, 9, 6, 7};

        System.out.println(Arrays.toString(arrayService.sorted(arr)));
        System.out.println(Arrays.toString(arrayService.sortedDescending(arr)));

        System.out.println(Arrays.toString(arrayService.getOdd(arr)));
        System.out.println(Arrays.toString(arrayService.getEven(arr)));

        System.out.println(Arrays.stream(new int[]{1, 2, 3, 4, 1, 2, 3, 4, 5, 7, 6, 11, 121, 21})
                .filter(i -> ("" + i).startsWith("1"))
                .boxed()
                .collect(Collectors.toList()));

//        Solve this:
//
//🔥 Problem 4: Maximum Subarray (Kadane’s Algorithm)
//
//        Input: [-2,1,-3,4,-1,2,1,-5,4]
//        Output: 6 (subarray [4,-1,2,1])

        System.out.println(Arrays.stream(new int[]{-2,1,-3,4,-1,2,1,-5,4})
                .sum());

        System.out.println(arrayService.kadaneAlgorithm(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
