package com.rag.rag4j.common.documents;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

@Service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseCase {

  /**
   UseCase Class를 명시하기 위한 Annotation
   기존 @Service Annotation 대신 해당 Annotation을 사용하도록 한다.
   @author : PLO
   @return : String
   */
  @AliasFor(annotation = Service.class)
  String value() default "";

}
