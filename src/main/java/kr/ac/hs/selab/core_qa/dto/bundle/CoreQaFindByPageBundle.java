package kr.ac.hs.selab.core_qa.dto.bundle;

import kr.ac.hs.selab.core_qa.domain.CoreQa;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@Getter
public class CoreQaFindByPageBundle {
    private final Long totalCount;
    private final Pageable pageable;
    private final Page<CoreQa> coreQas;
}
