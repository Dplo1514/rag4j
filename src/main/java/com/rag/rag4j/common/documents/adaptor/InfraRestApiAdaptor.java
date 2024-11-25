package com.rag.rag4j.common.documents.adaptor;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InfraRestApiAdaptor {

  /**
   Infra Layer의 API Adaptor Class를 명시하기 위한 Annotation
   Infra Layer의 Rest API 호출 Component에 해당 Annotation을 사용하도록 한다.
   @author : PLO
   @return : String
   */
  @AliasFor(annotation = Component.class)
  String value() default "";

}