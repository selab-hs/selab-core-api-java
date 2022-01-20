package kr.ac.hs.selab.common.presentation;

import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController implements HealthSdk {

    private static final String HEALTH_MESSAGE = "Health Good!!~~";

    @Override
    @GetMapping
    public String check() {
        return HEALTH_MESSAGE;
    }
}