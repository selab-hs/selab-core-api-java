package kr.ac.hs.selab.common.presentation;

import kr.ac.hs.selab.common.dto.ResponseDto;
import kr.ac.hs.selab.common.dto.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    private static final String HEALTH_MESSAGE = "Health Good!!~~";

    @GetMapping("/v1/health")
    public ResponseEntity<ResponseDto<String>> check() {
        return ResponseDto.of(ResponseMessage.HEALTH_GOOD, HEALTH_MESSAGE);
    }
}