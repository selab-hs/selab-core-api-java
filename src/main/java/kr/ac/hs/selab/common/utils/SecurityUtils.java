package kr.ac.hs.selab.common.utils;

import java.util.Objects;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtils {

    // TODO : 메서드 역할 분리 작업 진행 필요
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

    private void isNullAuthentication(final Authentication authentication) {
        if (Objects.isNull(authentication)) {
            throw new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR);
        }
    }

    private UserDetails getUserDetails(final Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        isNullUserDetails(userDetails);
        return userDetails;
    }

    private void isNullUserDetails(final UserDetails userDetails) {
        if (Objects.isNull(userDetails.getUsername())) {
            throw new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR);
        }
    }

    private String getAuthenticationPrincipal(final Authentication authentication) {
        isNullAuthenticationPrincipal(authentication);
        return authentication.getPrincipal().toString();
    }

    private void isNullAuthenticationPrincipal(final Authentication authentication) {
        if (Objects.isNull(authentication.getPrincipal())) {
            throw new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR);
        }
    }
}