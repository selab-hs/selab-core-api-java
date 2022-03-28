package kr.ac.hs.selab.coreQa.dto.bundle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoreQaCreateBundle {
    private final String email;
    private final String title;
    private final String content;
}