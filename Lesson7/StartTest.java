package Lesson7;

import org.testng.annotations.AfterSuite;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StartTest {

    public static void main(String[] args) {
        StartClass.start(StartClass.class);
    }
}

class StartClass {

    static Method afterMethod;
    static Method beforeMethod;

    public static void start(Class<?> testclass) {
        try {
            settingTest(testclass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void settingTest(Class<?> testclass) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StartClass startClass = (StartClass) testclass.getDeclaredConstructor().newInstance();
        Method[] methods = testclass.getMethods();
        int bef = 0;
        int aft = 0;
        List<Shell> testingMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                bef++;
                if (bef == 2) {
                    throw new RuntimeException("Методов Before больше двух");
                }
                StartClass.beforeMethod = method;

            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                aft++;
                if (aft == 2) {
                    throw new RuntimeException("Методов After больше двух");
                }
                StartClass.afterMethod = method;
            } else if (method.isAnnotationPresent(Test.class)) {
                Test anotTest = method.getAnnotation(Test.class);
                testingMethods.add(new Shell(method, anotTest.value()));
            }
            testingMethods.sort(Comparator.comparing(Shell::getPriority));
        }
        if (beforeMethod != null)
            beforeMethod.invoke(startClass);

        for (Shell shell : testingMethods) {
            shell.method.invoke(startClass);
            System.out.println("По объекту - оболочке, значение этого теста равно " + shell.priority);
        }

        if (afterMethod != null)
            afterMethod.invoke(startClass);
        }

    @Test(value = 5)
    public void Test5 () {
        System.out.println("Тест 5: активен");
    }
    @Test(value = 10)
    public void Test10 () {
        System.out.println("Тест 10: активен");
    }
    @Test(value = 1)
    public void Test1 () {
        System.out.println("Тест 1: активен");
    }
    @BeforeSuite
    public void BeforeAll () {
        System.out.println("Начало аннотаций");
    }
    @AfterSuite
    public void AfterAll() {
        System.out.println("Окончание аннотаций");
    }
}

    class Shell {
        public Method method;
        public Integer priority;

        public Shell(Method method, int priority) {
            this.method = method;
            this.priority = priority;
        }

        public Integer getPriority() {
            return priority;
        }
    }









