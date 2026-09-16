/**
 * Day 1 练习：面向对象复习 —— Student 类
 * 知识点：封装、构造方法、this、getter/setter、toString、equals/hashCode、参数校验
 */
public class Student {
    private final String id;      // 学号（final：创建后不可变，保证唯一标识）
    private String name;
    private int age;
    private double score;

    public Student(String id, String name, int age, double score) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("学号不能为空");
        }
        this.id = id;
        this.name = name;
        setAge(age);        // 复用 setter 的校验逻辑
        setScore(score);
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 0 || age > 150) throw new IllegalArgumentException("年龄不合法: " + age);
        this.age = age;
    }

    public double getScore() { return score; }
    public void setScore(double score) {
        if (score < 0 || score > 100) throw new IllegalArgumentException("分数不合法: " + score);
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', name='%s', age=%d, score=%.1f}", id, name, age, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}