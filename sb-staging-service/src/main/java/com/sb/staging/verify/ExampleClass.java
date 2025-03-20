package com.sb.staging.verify;

public class ExampleClass {

    // 未使用的变量
    private int unusedVariable;

    // 未使用的方法
    private void unusedMethod() {
        System.out.println("This method is not used.");
    }

    // 空的 catch 块
    public void methodWithEmptyCatch() {
        try {
            int[] array = new int[10];
            array[10] = 1; // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            // Empty catch block
        }
    }

    // 不必要的对象创建
    public void methodWithUnnecessaryObjectCreation() {
        String str = new String("Hello, World"); // 使用 String str = "Hello,World"; 更好
        System.out.println(str);
    }

//    // 未关闭的资源
//    public void methodWithUnclosedResource() {
//        java.io.FileInputStream fis = new java.io.FileInputStream("example.txt");
//        int data = fis.read();
//        while (data != -1) {
//            System.out.print((char) data);
//            data = fis.read();
//        }
//        // fis.close(); // 资源未关闭
//    }

    // 未处理的异常
    public void methodWithUncheckedException() {
        throw new RuntimeException("Unchecked exception");
    }

    // 重复的代码
    public void methodWithDuplicateCode() {
        int a = 10;
        int b = 20;
        int sum = a + b;
        System.out.println("Sum: " + sum);

        int c = 30;
        int d = 40;
        int sum2 = c + d;
        System.out.println("Sum: " + sum2);
    }

    // 魔法数字
    public void methodWithMagicNumbers() {
        int result = 10 * 20 + 30;
        System.out.println("Result: " + result);
    }

    // 不必要的复杂条件
    public void methodWithComplexCondition() {
        boolean condition = true;
        if (condition == true) {
            System.out.println("Condition is true");
        }
    }

    public static void main(String[] args) {
        ExampleClass example = new ExampleClass();
        example.methodWithEmptyCatch();
        example.methodWithUnnecessaryObjectCreation();
        //example.methodWithUnclosedResource();
        example.methodWithUncheckedException();
        example.methodWithDuplicateCode();
        example.methodWithMagicNumbers();
        example.methodWithComplexCondition();
    }
}
