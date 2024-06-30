package com.example.regexproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexModule {




    public boolean search(String text, String regex) {
        return text.matches(".*" + regex + ".*");
    }

    public boolean match(String text, String regex) {
        return text.matches(regex);
    }

    public String replace(String text, String regex, String replacement) {
        return text.replaceAll(regex, replacement);
    }
}


