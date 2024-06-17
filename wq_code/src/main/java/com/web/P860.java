package com.web;

public class P860 {
    public static void main(String[] args) {
        P860 solution = new P860();

        // Test cases
        int[] test1 = {5, 5, 5, 10, 20}; // Expected output: true
        int[] test2 = {5, 5, 10}; // Expected output: true
        int[] test3 = {10, 10}; // Expected output: false
        int[] test4 = {5, 5, 10, 10, 20}; // Expected output: false

        System.out.println("Test 1: " + solution.lemonadeChange(test1)); // Output should be true
        System.out.println("Test 2: " + solution.lemonadeChange(test2)); // Output should be true
        System.out.println("Test 3: " + solution.lemonadeChange(test3)); // Output should be false
        System.out.println("Test 4: " + solution.lemonadeChange(test4)); // Output should be false
    }

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else {
                    return false;
                }
            } else if (bills[i] == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
