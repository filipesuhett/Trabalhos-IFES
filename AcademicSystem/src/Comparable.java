import java.util.List;

public interface Comparable<T extends java.lang.Comparable<T>> {

    int compareTo(T t, T other);
    
    void ord(List<T> list);

    void mergeSort(List<T> list);

    void merge(List<T> list, List<T> left, List<T> right);

}