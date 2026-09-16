import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Day 3 练习：Lambda 与 Stream
 * 知识点：Lambda、方法引用、Stream 过滤/排序/分组、Optional、汇总统计
 * 编译运行：
 *   javac -encoding UTF-8 Student.java StreamLambdaDemo.java
 *   java StreamLambdaDemo
 * （Student.java 来自 day02，复制一份到本目录即可）
 */
public class StreamLambdaDemo {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("2023001", "张三", 20, 88.5),
                new Student("2023002", "李四", 21, 92.0),
                new Student("2023003", "王五", 19, 75.0),
                new Student("2023004", "赵六", 21, 92.0)
        );

        // 1. Lambda：替代匿名内部类
        Runnable hello = () -> System.out.println("Lambda 简化了匿名内部类");
        hello.run();

        // 2. Stream：过滤 + 排序 + 映射 + 收集
        List<String> names = students.stream()
                .filter(s -> s.getScore() >= 80)
                .sorted(Comparator.comparingDouble(Student::getScore).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("80 分以上(按成绩降序): " + names);

        // 3. Stream：分组统计
        Map<Integer, Long> byAge = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));
        System.out.println("按年龄分组: " + byAge);

        // 4. Optional：优雅处理可能为空的查找
        Optional<Student> top = students.stream()
                .max(Comparator.comparingDouble(Student::getScore));
        top.ifPresent(s -> System.out.println("最高分: " + s));

        // 5. 数值流汇总统计
        DoubleSummaryStatistics stats = students.stream()
                .mapToDouble(Student::getScore)
                .summaryStatistics();
        System.out.printf("成绩统计: 平均=%.1f 最高=%.1f 最低=%.1f 人数=%d%n",
                stats.getAverage(), stats.getMax(), stats.getMin(), stats.getCount());
    }
}