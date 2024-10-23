import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class SudokuSolver {

    public static void main(String[] args)
    {
        System.out.println("Hello user, please input a file name for the sudoku puzzle you would like me to solve.");
        Scanner scnr = new Scanner(System.in);
        String fileName = scnr.next();
        File dataFile = new File(fileName);
        while (!dataFile.exists()) {

                System.out.println("");        
        
               System.out.println("File does not exist!");
        
               System.out.print("Please enter the name of a valid file: ");
        
               fileName = scnr.nextLine();
        
               dataFile = new File(fileName);

              

        }    

        SudokuSolver solver = new SudokuSolver();
        solver.solve(processData(dataFile));
        
    }

    private static char[][] processData(File dataFile) {
        char[][] board = new char[9][9]; 
    
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            int row = 0;
    
            // Read each line of the file
            while ((line = br.readLine()) != null && row < 9) {
                // Removes brackets and quotes
                line = line.replaceAll("[\\[\\]\"]", "");
                String[] cells = line.split(",");
    
                
                for (int col = 0; col < 9; col++) {
                    board[row][col] = cells[col].charAt(0);
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    
        return board;
    }

    public void printBoard(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println(); 
        }
    }


    public void solveSudoku(char[][] board) {
        solve(board); 
     }
 
     public boolean solve(char[][] board) {
         int n = board.length;
         for (int row = 0; row < n; row++) {  
             for (int col = 0; col < n; col++) { 
                 if (board[row][col] == '.') {  // Checks if the cell is empty
                     for (int tryNum = 1; tryNum <= 9; tryNum++) { 
                         if (isConflicts(board, tryNum, row, col)) {  
                             board[row][col] = (char)(tryNum + '0');  
                             if (solve(board)) {  // Recursively call to fill out the board
                                 return true;  
                             }
                             board[row][col] = '.';  // If not solvable reset the cell
                         }
                     }
                     return false;  // in the case no number is valid return false to backtrack
                 }
             }
         }
         printBoard(board);
         return true;  // If all cells are filled return true
     }
 
     public boolean isConflicts(char[][] board, int tryNum, int row, int col) {
         return !rowCheck(board, tryNum, row) && 
                !colCheck(board, tryNum, col) && 
                !boxCheck(board, tryNum, row, col);  
     }
 
     // Checks for conflicts in the row
     public boolean rowCheck(char[][] board, int tryNum, int row) {
         for (int i = 0; i < board.length; i++) {
             if (board[row][i] == (char)(tryNum + '0')) {
                 return true;
             }
         }
         return false;
     }
 
     // Checks for conflicts in the column
     public boolean colCheck(char[][] board, int tryNum, int col) {
         for (int i = 0; i < board[0].length; i++) {
             if (board[i][col] == (char)(tryNum + '0')) {
                 return true;
             }
         }
         return false;
     }
 
     // Checks for conflicts in 3x3 cell box
     public boolean boxCheck(char[][] board, int tryNum, int row, int col) {
         int localRow = row - row % 3;
         int localCol = col - col % 3;
         for (int i = localRow; i < localRow + 3; i++) {
             for (int j = localCol; j < localCol + 3; j++) {
                 if (board[i][j] == (char)(tryNum + '0')) {
                     return true;
                 }
             }
         }
         return false;
     }
 }