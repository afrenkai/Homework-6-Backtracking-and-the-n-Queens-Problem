import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
public class nQueens {
    public static boolean isLegalPosition (int [] board, int n) {
        for (int i = 0; i < board.length; i++) {
            for (int j = i+1; j < board.length; j++) {
                if (board[i] == board[j]) {
                    return false;
                }
                if (Math.abs(board[i] - board[j]) == Math.abs(i-j)) {
                    return false;

                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] board = { 6, 8, 3, 5, 0, 0, 0};
        int n = 8;
        
        boolean result = isLegalPosition(board, n);
        System.out.println(result);
    }
}