import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class Fundamentals {

    // int 默认值0
    int intValue;
    // Integer 默认值null
    Integer intergerValue;

    public static void main(String[] args) {
        // 1.输出Hello, World!
        printHelloWorld();

        // 2.变量
        declareVariables();

        // 3.函数+泛型
        printVariables(10);
        printVariables(20.5);
        printVariables('A');
        printVariables("Hello");
        printVariables(true);
        // 4.反射+异常处理
        try {
            Reflection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 5.序列化
        Serialization();
        // 6.ArrayList和LinkedList
        ArrayListAndLinkedList();
        // 7.Collection和Collections
        CollectionAndCollections();
        // 8.HashMap和HashTable
        HashMapAndHashTable();
        // 9.Integer和Int
        IntegerAndInt();
        // 10.StringBuffer和StringBuilder
        StringBufferAndStringBuilder();
    }

    // 1.输出Hello, World!
    public static void printHelloWorld() {
        System.out.println("Hello, World!");
    }

    // 2.变量
    public static void declareVariables() {
        int a = 10; // 整数
        double b = 20.5; // 浮点数
        char c = 'A'; // 字符
        String d = "Hello"; // 字符串
        boolean e = true; // 布尔值
    }

    // 3.函数+泛型
    public static <T> void printVariables(T variable) {
        System.out.println(variable);
    }

    // 4.类型擦除
    public static void TypeEraser() {
        List<String> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        /*
         * - 由于类型擦除，list和list2的实际类型都是ArrayList<>
         * - 因此它们的类类型是相同的,这在运行时是不可区分的
         */
        System.out.println(list.getClass() == list2.getClass()); // true
    }

    // 5.反射（Reflection）是指在运行时动态地获取类的信息，并且可以动态地调用类的方法、访问和修改类的属性等。
    public static void Reflection() throws ClassNotFoundException {
        // 获取Class对象
        // 使用Class.forName()方法获取Fundamentals类的Class对象
        Class<?> clazz1 = Class.forName("Fundamentals");
        // 使用类名.class获取Person类的Class对象
        Class<?> clazz2 = Person.class;
        // 使用对象的getClass()方法
        Person person = new Person("Alice");
        Class<? extends Person> clazz3 = person.getClass();
        System.out.println(clazz1.getName());
        System.out.println(clazz2.getName());
        System.out.println(clazz3.getName());
        // 接收一个String参数的构造函数,需要声明一个带String参数的构造函数,否则抛出NoSuchMethodException异常
        try {
            // 获取构造方法
            Constructor<?> constructor = clazz2.getConstructor(String.class);
            // 创建对象
            Person person1 = (Person) constructor.newInstance("Alice");
            System.out.println(person1.getName());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            Person person2 = new Person("Charlie");
            // 获取Class对象
            Class<? extends Person> clazz = person.getClass();
            // 获取方法
            Method method = clazz.getMethod("setName", String.class);
            method.invoke(person2, "David");
            System.out.println(person2.getName());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 6.序列化
    public static void Serialization() {
        // 序列化是将对象转换为字节流的过程
        // 反序列化是将字节流转换为对象的过程
        // 实现Serializable接口
        // transient关键字修饰的变量不会被序列化
        User user = new User("Alice", 25);
        // 序列化
        try {
            // 会在根目录输出user.ser文件
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"));
            oos.writeObject(user);
            oos.close();
            System.out.println("对象已序列化");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 反序列化
        try {
            // 读取user.ser文件
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"));
            User deserializedUser = (User) ois.readObject();
            ois.close();
            System.out.println("反序列化后的对象: " + deserializedUser.getName() + ", " + deserializedUser.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 7.ArrayList和LinkedList
    public static void ArrayListAndLinkedList() {
        // ArrayList基于动态数组实现,扩容时会创建一个更大数组,将原数组拷贝到新数组中
        // 频繁在列表末尾添加或删除元素，ArrayList 性能较好。
        // 索引访问元素直接计算元素在内存中的位置,时间复杂度O(1)
        // 需要预分配容量,可能造成空间浪费

        // 使用无参构造函数创建ArrayList对象,不会立即分配容量,而是在第一次添加元素时才会分配容量
        // 使用钻石运算符，编译器会自动推断泛型类型
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        System.out.println(arrayList);
        // 要想获取数组存入的长度,需要调用size()方法
        System.out.println(arrayList.size());

        // LinkedList基于双向链表实现,扩容时不会创建新数组,而是在原数组中添加新元素
        // 频繁在列表中间或开头插入或删除元素，LinkedList 性能较好。
        // 索引访问元素需要遍历链表,时间复杂度O(n)
        // 额外引用指向前后节点,空间开销较大,但不需要预先分配容量,空间利用率高
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.println(linkedList);
    }

    // 8.Collection和Collections
    public static void CollectionAndCollections() {
        // Collection是Java集合框架的根接口,定义了对象的基本操作

        /*
         * java.util
         * ├── Co
         * │ ├── List
         * │ │ ├── ArrayL
         * │ │ ├── Li
         * │ │ └
         * │ ├── Set
         * │ │ ├── HashSet
         * │ │ ├── Lin
         * │ │ └──
         * │ └── Queue
         * │ ├── LinkedLis
         * │ └── PriorityQueue
         */
        // 创建一个实现Collection接口的ArrayList对象
        // 注意:Collection是一个接口,不能直接实例化,需要使用实现类来创建对象
        // 这里使用ArrayList作为实现类,因为它是List接口的一个实现类
        // 可以使用Collection接口的方法来操作ArrayList对象
        // 例如:add(),remove(),size(),isEmpty()等
        Collection<String> c = new ArrayList<>();
        c.add("hello");
        c.add("world");
        for (String s : c) {
            System.out.println(s);
        }
        // Collections类是一个工具类,提供了一些静态方法来操作集合
        // 例如:sort(),reverse(),shuffle()等
        // 注意:Collections类是一个工具类,不能直接实例化,需要使用静态方法来调用
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Collections.sort(list);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }

    // 9.HashMap和HashTable
    public static void HashMapAndHashTable() {
        // HashMap采用数组+链表+红黑树的结构
        // 数组是HashMap的主题,链表和红黑树是为了解决哈希冲突而存在的
        // 链表长度超过8时,链表会转化为红黑树,以提高查找效率
        // HashMap 中的元素数量超过负载因子（默认为 0.75）与数组长度的乘积时，会触发扩容操作。
        // HashMap：线程不安全，适用于单线程环境或并发度较低的场景。
        HashMap<String, Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put("one", 1);
        map.put("two", null);
        System.out.println("HashMap: " + map);
        // Hashtable 采用数组 + 链表的结构
        // Hashtable：线程安全，适用于多线程环境或需要线程安全的场景。
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("one", 1);
        table.put("two", 2);
        // 以下代码会抛出 NullPointerException
        // Hashtable是java早期类,设计时遵循“不允许存储 null 键和 null 值”的原则
        // null可能引发歧义, get(key)方法返回null无法判断键对应null还是该键不存在
        // 为了避免这种情况, Hashtable的 put() 和 get() 方法都进行了 null 检查

        // 下面这两个代码会抛出 NullPointerException
        // table.put(null, 0);
        // table.put("three", null);
        System.out.println("Hashtable: " + table);
    }

    // 10.Integer和Int
    public static void IntegerAndInt() {
        // 输出默认值
        System.out.println("int Default Value: " + new Fundamentals().intValue);
        System.out.println("Interger Default Value: " + new Fundamentals().intergerValue);
        // Integer引用没有指向某个对象
        // instanceof, 如果obj为bull, 将返回false
        Integer i = null;
        boolean bool = i instanceof Integer;
        System.out.println("i: " + i);
        System.out.println("b: " + bool);
        // Integer指向了某个对象, b为true
        i = Integer.valueOf(0);
        bool = i instanceof Integer;
        System.out.println("i: " + i);
        System.out.println("b: " + bool);

        // == 运算符 和 equals方法

        // == 运算符:
        // 对于基本数据类型,比较值
        // 对于引用数据类型,比较引用(内存地址)
        Integer a = Integer.valueOf(100);
        Integer b = Integer.valueOf(100);
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));

        // equals方法:
        // Object类的默认实现比较引用, 很多类重写方法后比较值
        // 对于Integer类, 重写了equals方法, 比较值

        // 对于Integer类, 有一个缓存机制, 缓存范围是-128到127
        // 如果值在这个范围内, 会从缓存中获取对象, 否则会创建新的对象
        // 所以, 对于值在-128到127范围内的Integer对象, 使用==运算符比较时, 结果为true
        // 对于值不在这个范围内的Integer对象, 使用==运算符比较时, 结果为false
        Integer c = Integer.valueOf(200);
        Integer d = Integer.valueOf(200);
        System.out.println("c: " + c);
        System.out.println("d: " + d);
        // 输出结果为false, 因为c和d的值不在-128到127范围内
        System.out.println("c == d: " + (c == d));
        System.out.println("c.equals(d): " + c.equals(d));
        // Double没有缓存,100.0和100.0是两个不同的对象
        Double e = Double.valueOf(100.0);
        Double f = Double.valueOf(100.0);
        System.out.println("e: " + e);
        System.out.println("f: " + f);
        System.out.println("e == f: " + (e == f));
        System.out.println("e.equals(f): " + e.equals(f));
        // Byte、Short、Integer、Long 和 Character 这些包装类有缓存机制，而 Float 和 Double 没有
    }
    // 11.StringBuffer和StringBuilder
    public static void StringBufferAndStringBuilder() {
        // StringBuffer是线程安全的,效率低,会自动扩容
        // 多线程环境下进行字符串的修改操作，建议使用 StringBuffer
        StringBuffer sb = new StringBuffer();
        sb.append("a");
        sb.append("b");
        sb.append("c");
        System.out.println(sb);
        // StringBuilder是线程不安全的,效率高,会自动扩容
        // 单线程环境下进行字符串的修改操作，建议使用 StringBuilder
        StringBuilder sb2 = new StringBuilder();
        sb2.append("a");
        sb2.append("b");
        sb2.append("c");
        System.out.println(sb2);
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}