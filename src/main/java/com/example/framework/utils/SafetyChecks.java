package com.example.framework.utils;


import java.util.Set;


public class SafetyChecks {


private static final Set<String> COMMON_BREACHED = Set.of(
"123456","password","12345678","qwerty","abc123","letmein","admin","welcome"
);


public static void validateNotPersonalEmail(String username) {
if (username == null) return;
String u = username.trim().toLowerCase();
if (u.endsWith("@gmail.com") || u.endsWith("@yahoo.com") || u.endsWith("@outlook.com")) {
throw new IllegalArgumentException("Refusing to use personal email in automation: " + username);
}
}


public static void validatePasswordBasic(String password) {
if (password == null || password.isEmpty()) {
throw new IllegalArgumentException("Password is empty");
}
String p = password.trim();
if (p.length() < 8) {
throw new IllegalArgumentException("Password too short (min 8 chars). Use a test account with a strong password.");
}
if (COMMON_BREACHED.contains(p.toLowerCase())) {
throw new IllegalArgumentException("Password is too common / known-breached. Pick a different password.");
}
if (!p.matches(".*[A-Za-z].*") || !p.matches(".*\\d.*")) {
throw new IllegalArgumentException("Password should contain letters and numbers.");
}
}
}