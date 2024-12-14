package com.rag.rag4j.common.documents.port;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InfraOutputPort {

  /**
   Infra Layer의 Port Class를 명시하기 위한 Annotation
   @author : PLO
   @return : String
   */
  String value() default "";

}