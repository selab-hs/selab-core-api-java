package kr.ac.hs.selab.common.template;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kr.ac.hs.selab.common.template.ResponseMessage;
import org.springframework.http.HttpStatus;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Response {

    HttpStatus status();

    ResponseMessage message();
}