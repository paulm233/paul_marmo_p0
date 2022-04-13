package dev.marmo.utilities;


// list should dynamically resize
// allow me to insert elements and have the order of insertion maintained
// does allow duplicates
public interface List<T> {

    void add(T element);

    T get(int index);

    int size();
}
