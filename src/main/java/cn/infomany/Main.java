package cn.infomany;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 学习java8流式处理
 * @author: zhanjinbing
 * @data: 2020-01-10 16:50
 */
public class Main {
    // 初始化


    public static void main(String[] args) {
        // 初始化数组
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };

        // 过滤filter
        System.out.println("============ 过滤得到所有的武汉大学 ===============");
        List<Student> filterStudent = students.stream()
                .filter(student -> "武汉大学".equals(student.getSchool()))
                .collect(Collectors.toList());
        System.out.println(filterStudent);

        // distinct
        List<String> distinctStudent = students.stream()
                .filter(student -> student.getAge() % 2 == 0).map(Student::getSchool).distinct()
                .collect(Collectors.toList());
        System.out.println(distinctStudent);

        // limit
        List<Student> limitStudent = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).limit(2)
                .collect(Collectors.toList());
        System.out.println(limitStudent);

        // sort
        List<Student> sortedCivilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).sorted(Comparator.comparingInt(Student::getAge))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(sortedCivilStudents);


        // skip
        List<Student> civilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(civilStudents);

        // map
        List<String> names = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getName).collect(Collectors.toList());
        System.out.println(names);

        // 三. 终端操作
        // allMatch
        boolean isAdult = students.stream().allMatch(student -> student.getAge() >= 18);
        System.out.println(isAdult);

        // anyMatch
        boolean hasWhu = students.stream().anyMatch(student -> "武汉大学".equals(student.getSchool()));
        System.out.println(hasWhu);

        // noneMatch
        boolean noneCs = students.stream().noneMatch(student -> "计算机科学".equals(student.getMajor()));
        System.out.println(noneCs);

        // findFirst
        Optional<Student> optStu = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findFirst();
        System.out.println("optStu = " + optStu);

        // findAny
        Optional<Student> optStu1 = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findAny();
        System.out.println("optStu1 = " + optStu1);


        // 前面例子中的方法
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();
        // 归约操作
        int totalAgeInt = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, (a, b) -> a + b);

        // 进一步简化
        int totalAge2 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, Integer::sum);

        // 采用无初始值的重载版本，需要注意返回Optional
        Optional<Integer> totalAgeOpt = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(Integer::sum);  // 去掉初始值


    }
}
