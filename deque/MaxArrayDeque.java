package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator comparator;

    /* Creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /* Returns the maximum element in the deque as governed by the previously given Comparator.
    If the MaxArrayDeque is empty, simply return null. */
    public T max() {
        if(this.size() <= 0 || comparator == null)
            return null;
        T temp = this.get(0);
        for (T o : this) {
            int compInt = comparator.compare(o, temp);
            temp = compInt > 0 ? o : temp;
        }
        return temp;
    }

    /* Returns the maximum element in the deque as governed by the parameter Comparator c.
    If the MaxArrayDeque is empty, simply return null.*/
    public T max(Comparator<T> c){
        if(this.size() <= 0 || c == null)
            return null;
        T temp = this.get(0);
        for (T o : this) {
            int compInt = c.compare(o, temp);
            temp = compInt > 0 ? o : temp;
        }
        return temp;
    }


}
