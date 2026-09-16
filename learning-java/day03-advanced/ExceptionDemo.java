import java.util.Scanner;

/**
 * Day 3 练习：异常处理
 * 知识点：try/catch/finally、throws、受检异常 vs 运行时异常、自定义异常、try-with-resources
 * 编译运行：
 *   javac -encoding UTF-8 ExceptionDemo.java
 *   java ExceptionDemo
 */
public class ExceptionDemo {

    /** 自定义受检异常（继承 Exception，调用者必须处理） */
    static class ScoreOutOfBoundsException extends Exception {
        public ScoreOutOfBoundsException(String message) { super(message); }
    }

    /** 自定义运行时异常（继承 RuntimeException，调用者可不处理） */
    static class IllegalNameException extends RuntimeException {
        public IllegalNameException(String message) { super(message); }
    }

    /** throws：把受检异常抛给调用者处理 */
    static void validateScore(int score) throws ScoreOutOfBoundsException {
        if (score < 0 || score > 100) {
            throw new ScoreOutOfBoundsException("成绩越界: " + score);
        }
    }

    static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalNameException("姓名不能为空");
        }
    }

    public static void main(String[] args) {
        // 1. 捕获受检异常 + finally
        for (int score : new int[]{95, 120}) {
            try {
                validateScore(score);
                System.out.println(score + " 合法");
            } catch (ScoreOutOfBoundsException e) {
                System.out.println("捕获受检异常: " + e.getMessage());
            } finally {
                System.out.println("(score=" + score + " 校验结束)");
            }
        }

        // 2. 运行时异常
        try {
            validateName("   ");
        } catch (IllegalNameException e) {
            System.out.println("捕获运行时异常: " + e.getMessage());
        }

        // 3. try-with-resources：自动关闭资源
        try (Scanner sc = new Scanner("10 20 abc 40")) {
            while (sc.hasNext()) {
                try {
                    System.out.println("读到数字: " + sc.nextInt());
                } catch (java.util.InputMismatchException e) {
                    System.out.println("不是数字, 跳过: " + sc.next());
                }
            }
        }
    }
}