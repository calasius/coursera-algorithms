package week1.union_find;

import edu.princeton.cs.algs4.StdIn;

public class QuickUnionUF {

    private int[] parent;
    private int[] size;

    public QuickUnionUF(int N){
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int root(int i){
        while (i != parent[i]){
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (size[i] < size[j]){
            parent[i] = j;
            size[j] += size[i];
        } else {
            parent[j] = i;
            size[i] += size[j];
        }
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
