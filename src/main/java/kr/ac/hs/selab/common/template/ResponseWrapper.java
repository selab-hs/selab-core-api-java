package kr.ac.hs.selab.common.template;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(final MethodParameter returnType,
        final Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.nonNull(returnType.getMethodAnnotation(Response.class));
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
        final MediaType selectedContentType,
        final Class<? extends HttpMessageConverter<?>> selectedConverterType,
        final ServerHttpRequest request,
        final ServerHttpResponse response) {

        response.setStatusCode(((ResponseTemplate<?>) body).getStatus());
        return body;
    }
}