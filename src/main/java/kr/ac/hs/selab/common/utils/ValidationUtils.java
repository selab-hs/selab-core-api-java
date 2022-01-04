package kr.ac.hs.selab.common.utils;

import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public class ValidationUtils {

    public static boolean isWrongWithRegex(String target, String regex) {
        return !Pattern.matches(regex, target);
    }

    public static boolean isWrongWithMaxLength(String target, int maxLength) {
        return !StringUtils.hasText(target) || target.length() > maxLength;
    }

    public static boolean isWrongWithEmpty(String target) {
        return !StringUtils.hasText(target);
    }

    public static boolean isCorrect(String target) {
        return StringUtils.hasText(target);
    }
}