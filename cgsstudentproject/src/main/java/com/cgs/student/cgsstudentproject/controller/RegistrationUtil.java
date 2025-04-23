package com.cgs.student.cgsstudentproject.controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationUtil {
    private static final Map<String, String> otpStorage = new HashMap<>();
    private static final Map<String, Long> otpExpiry = new HashMap<>();
    private static ConcurrentHashMap<String, Integer> wrongAttemptsStore = new ConcurrentHashMap<>();
    private static final long OTP_EXPIRY_DURATION_MS = 5 * 60 * 1000;
    private static final int MAX_WRONG_ATTEMPTS = 3;// OTP expiration time in minutes

    public static String generateStoreId(String first_name, String last_name) {
        String storeNamePrefix = first_name.length() >= 3 ? last_name.substring(0, 3).toUpperCase() : first_name.toUpperCase();

        String storeAddressPrefix = first_name.length() >= 3 ? last_name.substring(0, 3).toUpperCase() : last_name.toUpperCase();
        Random random = new Random();
        System.out.println("number genarting ");
        int randomDigits = 1000 + random.nextInt(9000);
        return storeNamePrefix + storeAddressPrefix + randomDigits;
    }

    public static String generateUsername(String first_name, String last_name) {
        System.out.println("Generate user ");
        return first_name.substring(0, 3).toLowerCase() + last_name.substring(0, 3).toLowerCase();

    }

    public static String generatePassword(String first_name, String last_name) {
        System.out.println("Generate password");

        // Extract the first two characters from storeName and storeAddress
        String part1 = first_name.substring(0, 2).toUpperCase(); // 2 uppercase letters
        String part2 = last_name.substring(0, 2).toUpperCase(); // 2 uppercase letters

        // Generate two random numbers
        Random random = new Random();
        int randomNumber1 = random.nextInt(10); // Single digit
        int randomNumber2 = random.nextInt(10); // Single digit

        // Generate two random special characters
        char[] specialChars = {'!', '@', '#', '$', '%', '^', '&', '*'};
        char randomSpecialChar1 = specialChars[random.nextInt(specialChars.length)];
        char randomSpecialChar2 = specialChars[random.nextInt(specialChars.length)];

        // Combine all parts into exactly 8 characters
        String password = part1 + part2 + randomNumber1 + randomNumber2 + randomSpecialChar1 + randomSpecialChar2;

        return password;
    }


    public static String generateOTP(int length, String email) {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        String generatedOTP = otp.toString();
        otpStorage.put(email, generatedOTP);
        otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_DURATION_MS);

        System.out.println("Generated OTP for " + email + ": " + generatedOTP);
        return generatedOTP;
    }

    public static boolean verifyOTP(String email, String enteredOTP) {
        if (!otpStorage.containsKey(email)) {
            return false;
        }
        long expiryTime = otpExpiry.get(email);
        if (System.currentTimeMillis() > expiryTime) {
            otpStorage.remove(email);
            otpExpiry.remove(email);
            return false;
        }

        // Check if the entered OTP matches the stored OTP
        String storedOTP = otpStorage.get(email);
        if (storedOTP.equals(enteredOTP)) {
            otpStorage.remove(email);
            otpExpiry.remove(email);
            return true; // OTP is valid
        } else {
            return false; // OTP is invalid
        }
    }
}



