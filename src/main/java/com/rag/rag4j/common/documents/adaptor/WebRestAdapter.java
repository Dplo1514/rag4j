package com.rag.rag4j.common.documents.adaptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebRestAdapter {

  /**
   WebRest API Adaptor Class를 명시하기 위한 Annotation
   기존 @RestController Annotation 대신 해당 Annotation을 사용하도록 한다.
   @author : PLO
   @return : String
   */
  @AliasFor(annotation = RestController.class)
  String value() default "";

}
