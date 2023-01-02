package week1.union_find;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.StdIn;

public class QuickFindUF {

    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid) id[i] = qid;
        }
    }

    public Collection<Set<Integer>> components() {
        Map<Integer, Set<Integer>> components = new HashMap<>();
        for (int i = 0; i < id.length; i++){
            if (components.containsKey(id[i])){
                components.get(id[i]).add(i);
            } else {
                components.put(id[i], new HashSet<>(Set.of(i)));
            }
        }
        return  components.values();
    }

    public static void main(String[] args) {

        int N = StdIn.readInt();
        int ops = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
        for (int i = 0; i < ops; i++){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
        }

        System.out.println(uf.components());
    }
    
}
