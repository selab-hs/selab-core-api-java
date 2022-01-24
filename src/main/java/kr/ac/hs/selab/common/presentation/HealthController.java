package kr.ac.hs.selab.common.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController implements HealthSdk {

    private static final String HEALTH_MESSAGE = "Health Good!!~~";

    @Override
    @GetMapping
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTemplate<String> check() {
        return ResponseTemplate.ok(ResponseMessage.HEALTH_GOOD, HEALTH_MESSAGE);
    }
}