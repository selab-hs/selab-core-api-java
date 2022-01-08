package kr.ac.hs.selab.auth;

import java.util.Objects;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        isNullAuthentication(authentication);

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();

            isNullSpringSecurityUser(springSecurityUser);

            return springSecurityUser.getUsername();
        }

        if (Objects.isNull(authentication.getPrincipal())) {
            throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
        }

        return authentication.getPrincipal().toString();
    }
    
    private static void isNullAuthentication(Authentication authentication) {
        if (Objects.isNull(authentication)) {
            throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
        }
    }

    private static void isNullSpringSecurityUser(UserDetails springSecurityUser) {
        if (Objects.isNull(springSecurityUser.getUsername())) {
            throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
        }
    }
}