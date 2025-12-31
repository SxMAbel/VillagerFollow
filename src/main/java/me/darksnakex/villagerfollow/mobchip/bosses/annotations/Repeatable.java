package me.darksnakex.villagerfollow.mobchip.bosses.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Repeatable {
   long delay() default 0L;

   String plugin();

   long interval();
}
