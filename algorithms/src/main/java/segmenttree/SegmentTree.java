package segmenttree;


import java.util.ArrayList;
import java.util.List;

public class SegmentTree {
    private final Node root;
    private final List<Integer> a;

    public SegmentTree(List<Integer> a) {
        this.a = a;
        root = new Node(null, null, 0);
        root.setL(0);
        root.setR(a.size() - 1);

        build(0, a.size() - 1, root);
    }

    public Node getRoot() {
        return root;
    }

    public void build(int tl, int tr, Node n) {
        if (tl == tr) {
            n.setValue(a.get(tl));
            return;
        }
        int middle = tl + (tr - tl) / 2;
        n.setLeft(new Node(null, null, 0));
        n.getLeft().setL(tl);
        n.getLeft().setR(middle);
        n.setRight(new Node(null, null, 0));
        n.getRight().setL(middle + 1);
        n.getRight().setR(tr);
        build(n.getLeft().getL(), n.getLeft().getR(), n.getLeft());
        build(n.getRight().getL(), n.getRight().getR(), n.getRight());
        n.setValue(n.getLeft().getValue() + n.getRight().getValue());
    }

    public int sum(int l, int r) {
        return sum(l, r, root);
    }

    private int sum(int l, int r, Node n) {
        if (l > r) {
            return 0;
        }
        if (n.getL() > r || n.getR() < l || r < l) {
            return 0;
        }
        if (n.getL() == l && n.getR() == r) {
            return n.getValue();
        }
        return sum(l, r, n.getLeft()) + sum(l, r, n.getRight());
    }

    public void set(int i, int val) {
        set(root, i, val);

    }


    private void set(Node n, int i, int val) {
        if (n.getR() == n.getL()) {
            n.setValue(val);
            return;
        }
        int tm = n.getL() + (n.getR() - n.getL()) / 2;
        if (i <= tm) {
            set(n.getLeft(), i, val);
        } else {
            set(n.getRight(), i, val);
        }
        n.setValue(n.getLeft().getValue() + n.getRight().getValue());
    }

    // оно не работает

    /*private void push(Node n) {
        if (n.getColor() != -1) {
            n.getLeft().setColor(n.getValue());
            n.getRight().setColor(n.getValue());
            n.setColor(-1);
        }
    }

    public void set(int l, int r, int val) {
        set(root, l, r, val);
    }

    private void set(Node n, int l, int r, int val) {
        update(n, l, r, val);
        if (l > r) {
            return;
        }
        if (l == r && n.getColor() == val) {
            n.setValue(val);
            return;
        }
        int tm = (n.getL() + n.getR()) / 2;
        set(n.getLeft(), l, tm, val);
        set(n.getRight(), tm + 1, r, val);

    }

    private void update(Node n, int l, int r, int col) {
        if (l > r) {
            return;
        }
        if (l == r) {
            n.setColor(col);
        } else {
            push(n);
            int tm = (n.getL() + n.getR()) / 2;
            update(n.getLeft(), l, Math.min(r, tm), col);
            update(n.getRight(), Math.max(tm + 1, l), r, col);
        }
    }*/

    public static List<Integer> getAllValues(SegmentTree st) {
        List<Integer> values = new ArrayList<>();
        getAllValues(st.getRoot(), values);
        return values;
    }

    private static void getAllValues(segmenttree.Node node, List<Integer> values) {
        if (node == null) {
            return;
        }
        values.add(node.getValue());
        getAllValues(node.getLeft(), values);
        getAllValues(node.getRight(), values);
    }
}

