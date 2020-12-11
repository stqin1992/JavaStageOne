package com.stqin.study.java.test;

public class MaximumTest {

    /**
     *  比较三个值并返回最大值
     *  如果想调用这个方法，最关键的是传入的T类型必须已经是实现了Comparable接口中compareTo()这个方法
     */
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x; // 假设x是初始最大值
        if (y.compareTo(max) > 0) {
            max = y; //y 更大
        }
        if (z.compareTo(max) > 0) {
            max = z; // 现在 z 更大
        }
        return max; // 返回最大对象
    }

    public static void main(String args[]) {
        System.out.printf("%d, %d 和 %d 中最大的数为 %d\n\n",
                3, 4, 5, maximum(3, 4, 5));

        System.out.printf("%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n",
                6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));

        System.out.printf("%s, %s 和 %s 中最大的数为 %s\n", "pear",
                "apple", "orange", maximum("pear", "apple", "orange"));
        Student s1 = new Student("stqin", 27);
        Student s2 = new Student("lily",28);
        Student s3 = new Student("lil1",29);
        maximum(s1, s2, s3);


    }
}
