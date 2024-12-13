import java.util.Arrays;

public class NQueens {
    public static boolean isLegalPosition(int[] board, int n) {
        for (int i = 0; i < n; i++) {
            if (board[i] == 0) break; // end of partial soln
            
            for (int j = i + 1; j < n; j++) {
                if (board[j] == 0) continue;
                if (board[i] == board[j]) { // col check
                    return false;
                }
                if (Math.abs(board[i] - board[j]) == Math.abs(i - j)) { // diag check
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] Successor(int[] board, int n) {
        int[] next = Arrays.copyOf(board, board.length);
        for (int i = n - 1; i >= 0; i--) {
            if (next[i] < n) {
                next[i]++;
                for (int j = i+1; j < n; j++) {
                    next[j] = 0;
                }
                return next;
            } else {
                next[i] = 0;
            }
        }
        // wraps around
        return new int[n];
    }

    
    public static int[] NextLegalPosition(int[] board, int n) {
        int[] current = Arrays.copyOf(board, n);
        do {
            current = Successor(current, n);
            if (allZeros(current))
                return current; // no more legal pos
        } while (!isLegalPosition(current, n));
        return current;
    }
    

    // literally just checks the array for all 0s.
    private static boolean allZeros(int[] arr) {
        for (int val : arr) {
            if (val != 0) return false;
        }
        return true;
    }
    
    // makes sure there arent any 0s in the array.
    private static boolean isCompleteSolution(int[] board) {
        for (int b : board) {
            if (b == 0) return false;
        }
        return true;
    }

    private static int[] findFirstSolution(int n, int[] start) {
        int[] current = Arrays.copyOf(start, n);
        while (true) {
            current = NextLegalPosition(current, n);
            if (allZeros(current)) {
                break; // no more legal pos
            }
            if (isCompleteSolution(current)) {
                return current;
            }
        }
        return current; // if no soln, return all 0s whcih shouldnt really happen but like it might idk
    }

    public static void main(String[] args) {
        for (int n = 4; n <= 100; n++) {
            int[] board = new int[n]; // 0 vec of size n
            int[] solution = findFirstSolution(n, board);
            System.out.println(n + "-Queens first solution: " + Arrays.toString(solution));
        }
    }

    
}
