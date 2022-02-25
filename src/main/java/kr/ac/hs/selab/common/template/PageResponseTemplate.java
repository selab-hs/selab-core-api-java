package kr.ac.hs.selab.common.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PageResponseTemplate<T> implements Serializable {
    @JsonIgnore
    private final transient HttpStatus status;
    private final String message;
    private final String code;
    private final LocalDateTime serverDateTime;
    private final List<T> data;
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final boolean firstPage;
    private final boolean lastPage;

    protected PageResponseTemplate(HttpStatus status, ResponseMessage message, Page<T> page) {
        this.status = status;
        this.message = message.name();
        this.code = message.getCode();
        this.serverDateTime = LocalDateTime.now();
        this.data = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.firstPage = page.isFirst();
        this.lastPage = page.isLast();
    }

    public static <T> PageResponseTemplate<T> ok(final ResponseMessage message, final Page<T> page) {
        return new PageResponseTemplate<T>(HttpStatus.OK, message, page);
    }
}