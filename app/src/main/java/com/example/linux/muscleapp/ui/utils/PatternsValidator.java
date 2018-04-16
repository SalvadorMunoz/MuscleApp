package com.example.linux.muscleapp.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linux on 8/01/18.
 */

public class PatternsValidator {
    public static boolean isPasswordValid(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
