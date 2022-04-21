package kr.ac.hs.selab.core_qa.converter;

import kr.ac.hs.selab.core_qa.domain.CoreQa;
import kr.ac.hs.selab.core_qa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.core_qa.dto.bundle.CoreQaFindByPageBundle;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaFindByIdResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaFindByPageResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CoreQaConverter {
    public CoreQa toCoreQa(CoreQaCreateBundle bundle) {
        return CoreQa.builder()
                .title(bundle.title())
                .content(bundle.content())
                .memberId(bundle.memberId())
                .build();
    }

    public CoreQaFindByIdResponse toCoreQaFindByIdResponse(CoreQa qa) {
        return CoreQaFindByIdResponse.builder()
                .id(qa.getId())
                .title(qa.getTitle())
                .content(qa.getContent())
                .memberId(qa.getMemberId())
                .build();
    }

    public CoreQaFindByPageResponse toCoreQaFindByPageResponse(CoreQaFindByPageBundle bundle) {
        var coreQaFindByIdResponses = bundle.getCoreQas()
                .stream()
                .map(CoreQaConverter::toCoreQaFindByIdResponse)
                .toList();

        return CoreQaFindByPageResponse.builder()
                .totalCount(bundle.getTotalCount())
                .pageNumber(bundle.getPageable().getPageNumber())
                .pageSize(bundle.getPageable().getPageSize())
                .sort(bundle.getPageable().getSort().toString())
                .coreQas(coreQaFindByIdResponses)
                .build();
    }
}
