package org.njust.framework.datastructure.heap;

import lombok.Getter;

import java.util.Comparator;
import java.util.PriorityQueue;

@Getter
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class MyPriorityQueue {
    public static void main(String[] args) {
        /**
         * 创建一个最小堆
         */
        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>();
        minPriorityQueue.add(1);
        minPriorityQueue.add(2);
        minPriorityQueue.add(3);
        System.out.println("最小堆顶部元素 = " + minPriorityQueue.peek());
        while (!minPriorityQueue.isEmpty()) {
            System.out.println("弹出元素 = " + minPriorityQueue.poll());
        }
        /**
         * 创建一个最大堆
         */
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        maxPriorityQueue.add(1);
        maxPriorityQueue.add(2);
        maxPriorityQueue.add(3);
        System.out.println("最大堆顶部元素 = " + maxPriorityQueue.peek());
        while (!maxPriorityQueue.isEmpty()) {
            System.out.println("弹出元素 = " + maxPriorityQueue.poll());
        }
        /**
         * 自定义堆排序
         */
        PriorityQueue<Person> personPriorityQueue = new PriorityQueue<>(new Comparator<Person>() {
            // 提供比较器，让类实现Comparable接口
            // 否则，会报错：
            // class org.njust.framework.datastructure.heap.Person cannot be cast to class java.lang.Comparable
            @Override
            public int compare(Person o1, Person o2) {
                // 按年龄升序排列
                return o1.getAge() - o2.getAge();
            }
        });
        personPriorityQueue.add(new Person("张三", 18));
        personPriorityQueue.add(new Person("李四", 19));
        personPriorityQueue.add(new Person("王五", 20));
        System.out.println("最大堆顶部元素 = " + personPriorityQueue.peek());
        while (!personPriorityQueue.isEmpty()) {
            System.out.println("弹出元素 = " + personPriorityQueue.poll());
        }
    }
}
