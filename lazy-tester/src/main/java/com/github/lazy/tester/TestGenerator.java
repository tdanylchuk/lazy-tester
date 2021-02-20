package com.github.lazy.tester;

import com.github.lazy.tester.model.TestMethod;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class TestGenerator {

    private final ClassParser classParser;
    private final Class<?> testeeClass;
    private final TestClassBuilder testClassBuilder;

    private TestGenerator(Class<?> testeeClass) {
        this.testeeClass = testeeClass;
        classParser = new ClassParser(testeeClass);
        this.testClassBuilder = new TestClassBuilder(testeeClass);
    }

    public static String generate(Class<?> testeeClass) {
        return new TestGenerator(testeeClass).generateTestClass();
    }

    @SneakyThrows
    private String generateTestClass() {
        generateMockFields();
        generateTesteeField();
        generateTestMethods();
        return testClassBuilder.build();
    }

    private void generateTestMethods() {
        classParser.getDeclaredPublicMethods().stream()
                .map(this::generateMethod)
                .forEach(this::generateTestMethod);
    }

    public TestMethod generateMethod(String methodName) {
        var mockCalls = classParser.getMockCalls(methodName);
        return TestMethod.builder()
                .methodCalls(mockCalls)
                .name(methodName)
                .build();
    }

    private void generateTestMethod(TestMethod method) {
        testClassBuilder.addTestMethod(method);
    }

    private void generateTesteeField() {
        testClassBuilder.addTesteeField(testeeClass);
    }

    private void generateMockFields() {
        for (Field field : testeeClass.getDeclaredFields()) {
            testClassBuilder.addMockField(field.getType(), StringUtils.uncapitalize(field.getType().getSimpleName()));
        }
    }
}
