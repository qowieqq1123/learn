/**
 * Day 1 练习：对象创建、方法调用、异常处理
 * 编译运行：
 *   javac -encoding UTF-8 Student.java Main.java
 *   java Main
 */
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("2023001", "张三", 20, 88.5);
        Student s2 = new Student("2023002", "李四", 21, 92.0);
        Student s3 = new Student("2023001", "张三重复", 20, 60.0);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println("s1 和 s3 学号相同吗？" + s1.equals(s3));

        try {
            s1.setScore(150);   // 触发参数校验异常
        } catch (IllegalArgumentException e) {
            System.out.println("捕获异常：" + e.getMessage());
        }
    }
}