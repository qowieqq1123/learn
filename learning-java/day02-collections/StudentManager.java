import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Day 2 成果：学生管理控制台程序（第一阶段验收项目）
 * 知识点：ArrayList / HashMap / HashSet / Scanner 交互 / Comparator 排序 / 自定义异常
 * 编译运行：
 *   javac -encoding UTF-8 Student.java StudentManager.java
 *   java StudentManager
 */
public class StudentManager {

    /** 自定义异常：学生不存在 */
    static class StudentNotFoundException extends RuntimeException {
        public StudentNotFoundException(String message) { super(message); }
    }

    private final List<Student> students = new ArrayList<>();
    private final Map<String, Student> byId = new HashMap<>();   // 学号 -> 学生，O(1) 查找

    /** 添加学生：学号唯一性校验 */
    public void add(Student s) {
        if (byId.containsKey(s.getId())) {
            throw new IllegalStateException("学号已存在: " + s.getId());
        }
        students.add(s);
        byId.put(s.getId(), s);
    }

    /** 删除学生：不存在则抛出自定义异常 */
    public void remove(String id) {
        Student s = byId.remove(id);
        if (s == null) throw new StudentNotFoundException("找不到学生: " + id);
        students.remove(s);
    }

    /** 修改成绩 */
    public void updateScore(String id, double score) {
        find(id).setScore(score);
    }

    /** 按学号查询 */
    public Student find(String id) {
        Student s = byId.get(id);
        if (s == null) throw new StudentNotFoundException("找不到学生: " + id);
        return s;
    }

    /** 按姓名模糊搜索 */
    public List<Student> searchByName(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().contains(keyword)) result.add(s);
        }
        return result;
    }

    /** 按成绩从高到低排序（返回副本，不影响原顺序） */
    public List<Student> sortByScore() {
        List<Student> copy = new ArrayList<>(students);
        copy.sort(Comparator.comparingDouble(Student::getScore).reversed());
        return copy;
    }

    public void printAll() {
        if (students.isEmpty()) {
            System.out.println("(暂无学生)");
            return;
        }
        students.forEach(System.out::println);
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        // 预置演示数据
        manager.add(new Student("2023001", "张三", 20, 88.5));
        manager.add(new Student("2023002", "李四", 21, 92.0));
        manager.add(new Student("2023003", "王五", 19, 75.0));

        Scanner sc = new Scanner(System.in);
        System.out.println("=== 学生管理系统 ===");
        while (true) {
            System.out.println();
            System.out.println("1.添加  2.删除  3.改成绩  4.查询  5.按姓名搜索  6.按成绩排序  0.退出");
            System.out.print("请选择: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("输入 学号 姓名 年龄 成绩 (空格分隔): ");
                        String[] p = sc.nextLine().trim().split("\\s+");
                        manager.add(new Student(p[0], p[1], Integer.parseInt(p[2]), Double.parseDouble(p[3])));
                        System.out.println("添加成功");
                    }
                    case "2" -> {
                        System.out.print("要删除的学号: ");
                        manager.remove(sc.nextLine().trim());
                        System.out.println("删除成功");
                    }
                    case "3" -> {
                        System.out.print("输入 学号 新成绩: ");
                        String[] p = sc.nextLine().trim().split("\\s+");
                        manager.updateScore(p[0], Double.parseDouble(p[1]));
                        System.out.println("修改成功");
                    }
                    case "4" -> {
                        System.out.print("要查询的学号: ");
                        System.out.println(manager.find(sc.nextLine().trim()));
                    }
                    case "5" -> {
                        System.out.print("姓名关键字: ");
                        List<Student> list = manager.searchByName(sc.nextLine().trim());
                        if (list.isEmpty()) System.out.println("(无匹配)");
                        list.forEach(System.out::println);
                    }
                    case "6" -> {
                        System.out.println("--- 按成绩降序 ---");
                        manager.sortByScore().forEach(System.out::println);
                    }
                    case "0" -> {
                        System.out.println("再见！");
                        return;
                    }
                    default -> System.out.println("无效选项");
                }
            } catch (StudentNotFoundException | IllegalStateException | IllegalArgumentException
                    | ArrayIndexOutOfBoundsException e) {
                System.out.println("操作失败: " + e.getMessage());
            }
        }
    }
}