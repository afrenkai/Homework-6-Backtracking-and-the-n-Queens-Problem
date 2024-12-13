import java.util.Arrays;

public class NQueens {
   
    public static boolean isLegalPosition(int[] board, int placed) {
        int currentRow = placed - 1;
        for (int i = 0; i < currentRow; i++) {
            if (board[i] == board[currentRow] || 
                Math.abs(board[i] - board[currentRow]) == Math.abs(i - currentRow)) {
                return false;
            }
        }
        return true;
    }

    public static int[] NextLegalPosition(int[] board, int n) {
        int placed = 0;
        while (placed >= 0) {
            board[placed]++;
            if (board[placed] > n) {
                board[placed--] = 0;
                continue;
            }
            if (isLegalPosition(board, placed + 1)) {
                if (placed == n - 1) return board;
                placed++;
            }
        }
        return new int[n];
    }

    public static int[] findFirstSolution(int n) {
        int[] board = new int[n];
        return NextLegalPosition(board, n); 
    }

    public static void countSolutions(int[] board, int row, int n, int[] count) {
        if (row == n) {
            count[0]++;
            return;
        }

        for (int col = 1; col <= n; col++) {
            board[row] = col;
            if (isLegalPosition(board, row + 1)) {
                countSolutions(board, row + 1, n, count);
            }
            board[row] = 0;
        }
    }

    public static int calculateTotalSolutions(int n) {
        int[] board = new int[n];
        int[] count = {0};
        countSolutions(board, 0, n, count);
        return count[0];
    }

    public static void main(String[] args) {
        for (int n = 4; n <= 14; n++) {
            int[] firstSolution = findFirstSolution(n);
            int totalSolutions = calculateTotalSolutions(n);

            System.out.println(n + "-Queens first solution: " + Arrays.toString(firstSolution));
            System.out.println("There are " + totalSolutions + " solutions to the " + n + "-Queens Problem.");
        }
    }
}