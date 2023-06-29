package heap;

import java.util.List;

public class Heap {
    private final List<Integer> a;
    private int size;

    public Heap(List<Integer> a) {
        this.a = a;
        size = a.size();
    }

    public void siftDown(int i) {
        while (2 * i + 1 < a.size()) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < a.size() && a.get(right) < a.get(left)) {
                j = right;
            }
            if (a.get(i) <= a.get(j)) {
                break;
            }
            int k = a.get(i);
            a.set(i, a.get(j));
            a.set(j, k);
            i = j;
        }
    }


    public void siftUp(int i) {
        while (a.get(i) < a.get((i - 1) / 2)) {
            int k = a.get(i);
            a.set(i, a.get((i - 1) / 2));
            a.set((i - 1) / 2, k);
            i = (i - 1) / 2;
        }
    }

    public int getMin() {
        int min = a.get(0);
        a.set(0, a.get(size - 1));
        size--;
        siftDown(0);
        return min;
    }

    public void инсерт(int i) {
        size++;
        a.add(i);
        siftUp(size - 1);
    }

    public void build() {
        for (int i = size / 2; i >= 0; i--) {
            siftDown(i);
        }
    }
}
