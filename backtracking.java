import java.util.*;
import java.io.*;
import java.lang.*;

class Sudoku{
    public static void start(){
        System.out.println("Sudoku solver using backtracking and recursion in JAVA");
        System.out.println();
        System.out.println("Enter the Sudoku problem to be solved:");
        System.out.println("Enter zero(0) for empty cells");
        int [][] matrix = new int [9][9];
        
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<9;i++)
        {
            for(int j = 0;j<9;j++)
            {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println();
        /* print solution if it exixts */
        System.out.println("Solved Sudoku");
        if(solvesudoku(matrix,9)){
            for(int i=0;i<9;i++)
            {
                for(int j=0;j<9;j++){
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.println();
            }
        }
        /* else print no solution */
        else
        {
            System.out.println("Cannot solve this Sudoku");
        }
    }
    public static boolean solvesudoku(int [][]matrix,int n){
        int rowIndex = -1;
        int columnIndex = -1;
        int i=0;
        int j=0;

        for(i=0;i<n;i++){
            for(j=0;j<n;j++)
            {
                if(matrix[i][j] == 0){ //to find the row and column index where value has to be placed.
                    rowIndex = i;
                    columnIndex = j;
                    break;
                }
            }
            if(rowIndex != -1){ //if there is no such row exit from the loop.
                break;
            }
        }
        if(i == n && j==n) //if it has reached the end of the matrix then solution exists so, return true 
        {
            return true;
        }
        else   //if it is not at the end of the matrix
        {
            for(int value = 1; value<10; value++){
                if(IsSafe(matrix, value, rowIndex, columnIndex, n)){ //checks whether that value can be placed at the cell or not
                    matrix[rowIndex][columnIndex] = value;
                    if(!solvesudoku(matrix,n)){ //recursive call
                        matrix[rowIndex][columnIndex] = 0;//set that particular cell to 0 and backtrack.
                    }
                    else
                    {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    public static boolean IsSafe(int matrix[][], int value, int rowIndex, int columnIndex, int n){
        //row check
        for(int j=0;j<9;j++)
        {
            if(matrix[rowIndex][j] == value){
                return false;
            }
        }
        //column check
        for(int i=0;i<9;i++)
        {
            if(matrix[i][columnIndex] == value)
            {
                return false;
            }
        }
        //grid check
        int baseRowIndex = rowIndex-(rowIndex % 3);
        int baseColumnIndex = columnIndex-(columnIndex % 3);
        for(int i=baseRowIndex;i<baseRowIndex+3;i++)
        {
            for(int j = baseColumnIndex;j<baseColumnIndex+3;j++)
            {
                if(matrix[i][j] == value){
                    return false;
                }
            }
        }
        return true;
    }
}

class Mazegame{
    public static void start(){
        System.out.println("Maze game using Backtracking and recursion in JAVA");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of Maze");
        int n = sc.nextInt();
        System.out.println("Enter the maze matrix 0-obstacles and 1-no obstacles");
        
        int maze[][] = new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                maze[i][j] = sc.nextInt();
            }
        }

        findPath(maze,n);
    }
     static boolean IsSafe(int[][] arr,int row,int col){
        //row and column should be within 2D matrix and row and col should not be negtive and place of row and column should be 1
        //if the above condition fails then it returns false and would not enter the block;
        return(row < arr.length && col < arr.length && col>=0 && row>=0 && arr[row][col]==1);
    }

    //function to return true or false
    static boolean findPathUtil(int[][] arr,int[][] solution,int row,int col){

            //base case whether it has reached to destination.
            if(row==arr.length-1 && col == arr.length-1 && arr[row][col] == 1){
                solution[row][col] = 1;
                return true;
            }

            if(IsSafe(arr,row,col)){//if it is safe then set the solution block to 1.
                solution[row][col] = 1; 

                if(findPathUtil(arr,solution,row+1,col)){//move downwards by incrementing row number by 1;
                    return true;
                }

                if(findPathUtil(arr,solution,row,col+1)){
                    return true;
                }

                //if rat cannot move downwards or to its right then backtracking starts here.
                solution[row][col] = 0;
                return false;
            }
            else{
            return false;
            }
    }
    

    static void printArray(int[][] arr){
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void findPath(int[][] arr,int n){
        //2D array represent the path with 1 and rest with 0.
        int[][] solution = new int[n][n];
        if(findPathUtil(arr,solution,0,0)){
            System.out.println();
            System.out.println("Path is:");
            printArray(solution);
        }
        else{
            System.out.println("No path");
        }
    }
}

public class backtracking{
    public static void main(String[] args)
    {
        System.out.println("\t\t\t\t\t\t\t\tBACKTRACKING\t\t\t\t");
        System.out.println();
        System.out.println("\tBacktracking is an algorithmic-technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point of time ");
        System.out.println("For example,Sudoku solver, Rat in a maze, Knight's tour problem, N queens problem etc,.");
        System.out.println();
        System.out.println("Implemention of some of the examples using Backtracking: ");
        int ch1 = 1;
        while(ch1 == 1){   
            int ch;
            System.out.println("[1] Sudoku Solver");
            System.out.println("[2] Path finding in a maze");
            System.out.println("[3] Quit");
            System.out.println("Enter your choice:");
            Scanner sc = new Scanner(System.in);
            ch = sc.nextInt();
            Sudoku s = new Sudoku();
            Mazegame m = new Mazegame();
            switch(ch)
            {
                case 1: s.start();
                        break;
                case 2:m.start();
                        break;
                case 3: System.exit(0);
                default: System.out.println("Enter the correct choice");
            }
            System.out.println("Do you want to continue: 1-Yes 0-No");
            ch1 = sc.nextInt();
        }
    }
}
