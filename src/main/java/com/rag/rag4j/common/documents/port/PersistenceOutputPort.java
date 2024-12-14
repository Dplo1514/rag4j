package com.rag.rag4j.common.documents.port;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Repository;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersistenceOutputPort {

  /**
   Persistence Port Class를 명시하기 위한 Annotation
   @author : PLO
   @return : String
   */
  String value() default "";

}
