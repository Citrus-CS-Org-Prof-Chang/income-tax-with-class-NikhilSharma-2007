package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class Dummy {

    // Static arrays to hold tax thresholds and rates
    static int[] salaryThresholds;
    static double[] taxRates;

    // Static block ensures tax tables are initialized before any method is called
    static {
        salaryThresholds = new int[]{0, 20000, 50000, 100000, Integer.MAX_VALUE};
        taxRates = new double[]{0.0, 0.10, 0.20, 0.30, 0.40};
    }

    // Main method for user interaction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter annual income (-1 to exit): ");
            int income = scanner.nextInt();

            if (income == -1) {
                break;
            }

            double rate = getTaxRate(income);
            double taxToPay = income * rate;

            System.out.printf("Annual Income: %d Tax rate: %.2f Tax to pay: %.2f\n",
                    income, rate, taxToPay);
        }

        scanner.close();
    }

    // Returns the correct tax rate for a given salary
    public static double getTaxRate(int salary) {
        if (salary < 0) return 0.0;  // Guard against invalid input

        for (int i = 0; i < salaryThresholds.length - 1; i++) {
            if (salary >= salaryThresholds[i] && salary < salaryThresholds[i + 1]) {
                return taxRates[i];
            }
        }

        return 0.0;  // Fallback
    }

    // Allows setting custom tax tables, useful for unit testing
    public static void setTaxTable(int[] thresholds, double[] rates) {
        if (thresholds != null && rates != null && thresholds.length == rates.length + 1) {
            salaryThresholds = thresholds;
            taxRates = rates;
        }
    }

    // FIXED: Changed from returning 1 to 0 to pass the test
    public static int dummy() {
        return 0;
    }
}