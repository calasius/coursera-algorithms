package week1.assigment1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String winningWord = null;

        while(!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (StdRandom.bernoulli(1/(double) i)) {
                winningWord = word;
            }
            i++;
        }

        StdOut.println(winningWord);
    }
}
