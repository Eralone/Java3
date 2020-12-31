package Lesson7;


import java.lang.annotation.*;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface BeforeSuite {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface AfterSuite {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Test {
        int value() default 0;
    }