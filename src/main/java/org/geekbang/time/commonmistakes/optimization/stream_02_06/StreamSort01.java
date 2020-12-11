package org.geekbang.time.commonmistakes.optimization.stream_02_06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://blog.csdn.net/lihua5419/article/details/86678860
 * @author Legal
 * @date 2020/11/14
 */
public class StreamSort01 {

    /**
     * stream 串行
     * @param stuList
     */
    public void sort(List<Student> stuList) {
        Map<String, List<Student>> stuMap = stuList.stream().filter((Student s) -> s.getHeight() > 160)
                .collect(Collectors.groupingBy(Student::getSex));
    }

    /**
     * stream 并行
     * @param stuList
     */
    public void parallelSort(List<Student> stuList) {
        Map<String, List<Student>> stuMap = stuList.parallelStream().filter((Student s) -> s.getHeight() > 160)
                .collect(Collectors.groupingBy(Student::getSex));
    }
}
