package com.github._1c_syntax.mdclasses.mdoreader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MDField {
  String namespaceEDT() default "";

  String namespaceDesigner() default "";

  String nameEDT() default "";

  String nameDesigner() default "";

  boolean isAttributeEDT() default false;

  boolean isAttributeDesigner() default false;

  Class<?> converterEDT() default Object.class;

  Class<?> converterDesigner() default Object.class;
}
