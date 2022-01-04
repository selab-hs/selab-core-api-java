package kr.ac.hs.selab.common.utils;

import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public class ValidationUtils {

    public static boolean isWrong(String target, String regex) {
        return !Pattern.matches(regex, target);
    }

    public static boolean isWrong(String target, int maxLength) {
        return !StringUtils.hasText(target) || target.length() > maxLength;
    }

    public static boolean isCorrect(String target) {
        return StringUtils.hasText(target);
    }
}