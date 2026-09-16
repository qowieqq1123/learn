import java.util.List;

/**
 * Day 3 练习：泛型
 * 知识点：泛型类、泛型方法、上界限定（extends Comparable）
 * 编译运行：
 *   javac -encoding UTF-8 GenericBox.java
 *   java GenericBox
 */
public class GenericBox<T> {
    private T value;

    public void put(T value) { this.value = value; }

    public T get() { return value; }

    /** 泛型方法：统计列表中小于 max 的元素个数 */
    public static <U extends Comparable<U>> int countLessThan(List<U> list, U max) {
        int count = 0;
        for (U item : list) {
            if (item.compareTo(max) < 0) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        GenericBox<String> box = new GenericBox<>();
        box.put("hello");
        System.out.println(box.get().toUpperCase());   // 取出即为 String，无需强转

        List<Integer> nums = List.of(3, 8, 1, 9, 5);
        System.out.println("小于 6 的个数: " + countLessThan(nums, 6));
    }
}