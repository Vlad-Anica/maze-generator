import java.util.ArrayList;

public class DisjointSet {
    private int n;
    private ArrayList<Integer> father;


    public DisjointSet(int n) {
        this.n = n;
        father = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            father.add(i);
        }
    }

    /**
     * finds the group element e is part of
     * @param e the element we are interested in
     * @return group of element e
     */
    public int find(int e) {
        if (father.get(e) == e)
            return e;
        father.set(e, find(father.get(e)));
        return father.get(e);
    }

    /**
     * unites the group of two elements
     * @param e1 first element
     * @param e2 second element
     * @return false if they are already in the same group, true otherwise.
     */
    public boolean union(int e1, int e2) {
        int father1 = find(e1);
        int father2 = find(e2);

        if (father1 == father2) {
            return false;
        }
        father.set(father1, father2);
        return true;
    }
}
