package com.github._1c_syntax.mdclasses.mdoreader;


import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MDClass {
  String namespaceEDT() default "";

  String namespaceDesigner() default "http://v8.1c.ru/8.3/MDClasses";

  String nameEDT() default "";

  String nameDesigner() default "";
}
