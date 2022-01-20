package kr.ac.hs.selab.common.utils;

import java.util.Objects;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtils {

    public String getCurrentUsername() {
        Authentication authentication = getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails) {
            return getUserDetails(authentication).getUsername();
        }

        return getAuthenticationPrincipal(authentication);
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isNullAuthentication(authentication);
        return authentication;
    }

    private void isNullAuthentication(Authentication authentication) {
        if (Objects.isNull(authentication)) {
            throw new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR);
        }
    }

    private UserDetails getUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        isNullUserDetails(userDetails);
        return userDetails;
    }

    private void isNullUserDetails(UserDetails userDetails) {
        if (Objects.isNull(userDetails.getUsername())) {
            throw new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR);
        }
    }

    private String getAuthenticationPrincipal(Authentication authentication) {
        isNullAuthenticationPrincipal(authentication);
        return authentication.getPrincipal().toString();
    }

    private void isNullAuthenticationPrincipal(Authentication authentication) {
        if (Objects.isNull(authentication.getPrincipal())) {
            throw new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR);
        }
    }
}