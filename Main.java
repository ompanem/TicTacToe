import java.util.Scanner;
public class Main {

    public static boolean checkRightDiag(char[][]board)
    {
        char symbol = board[0][board[0].length-1];
        if(symbol!='X'&& symbol!='O')
        {
            return false;
        }

        for(int row = 0; row<board.length; row++)
        {
            int col = (board.length-1)-row ;
            if(symbol!=board[row][col])
            {
                return false;
            }
        }
        return true;
    }
    public static boolean checkLeftDiag(char[][]board)
    {
        char symbol = board[0][0];
        if(symbol!='X'&& symbol!='O')
        {
            return false;
        }
        for(int row = 0; row<board.length; row++)
        {
            int col = row;
            if(symbol!=board[row][col])
            {
                return false;
            }
        }
        return true;
    }
    public static boolean checkRowWin(char[][]board)
    {
        for(int row = 0 ; row<board.length; row++)
        {
            char symbol = board[row][0];
            if (!(symbol=='X'|| symbol=='O'))
            {
               continue;
            }
            boolean rowWin=true;
            for(int col = 0; col <board[row].length; col++)
            {

                if(board[row][col]!= symbol)
                {
                   rowWin=false;
                   break;
                }
            }
            if(rowWin)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkColumnWin(char[][]board)
    {
        for(int col = 0 ; col<board[0].length; col++)
        {
            char symbol = board[0][col];
            if (!(symbol=='X'|| symbol=='O'))
            {
                continue;
            }
            boolean colWin=true;
            for(int row = 0; row <board.length; row++)
            {

                if(board[row][col]!= symbol)
                {
                    colWin=false;
                    break;
                }
            }
            if(colWin)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkWin(char[][]board)
    {
        //todo logic to check when the game ends
        //check rows win
        if (checkRowWin(board)){
            return true;
        }

        // check cols win
        if(checkColumnWin(board))
        {
            return true;
        }
        //check diagonal win
        if(checkLeftDiag(board))
        {
            return true;
        }
        if(checkRightDiag(board))
        {
            return true;
        }
        return false;
    }

    public static void printBoard(char[][]board)
    {
        for(int i =0; i<board.length; i++){
            System.out.print("\n");
            for(int j=0; j<board[0].length; j++) {

                   if(board[i][j]=='X'|| board[i][j]=='O')
                   {
                       System.out.print(" "  + board[i][j] +" ");
                   }
                   else{
                       System.out.print(" _ ");
                   }
                 }
            }
            System.out.println();
    }
    public static boolean gameIncomplete(char[][]board) {
        for(int row = 0; row<board.length; row++) {
            for(int col = 0; col<board[0].length; col++){
                if(board[row][col]!='X' && board[row][col]!='O')
                {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][]board = new char[3][3];
        char symbol = 'a';
        while (symbol != 'X' && symbol != 'O'){
            System.out.println("which symbol do you want X or O?");
            symbol = input.next().charAt(0);
            if(symbol!='X'&& symbol!='O')
            {
                System.out.println("Invalid symbol");
            } else {
                break;
            }
        }
        
        
        while(gameIncomplete(board)) {
            // accept coordinates from user
            System.out.println("Which row do you want " + symbol + " to be in?");
            int row = input.nextInt();
            System.out.println("Which column do you want " + symbol + " to be in?");
            int column = input.nextInt();
            //replace underscore with symbol given the position
            if(board[row-1][column-1]=='X' ||board[row-1][column-1]=='O')
            {
                System.out.println("Position already has " +board[row-1][column-1]);
                continue;
            }
            else{
                board[row-1][column-1] = symbol;
                checkWin(board);
                printBoard(board);

                if(checkWin(board))
                {
                    System.out.println(symbol + " won");
                    return;
                }

            }
            // switch players
            if(symbol =='X'){
                symbol = 'O';
            }
            else{
                symbol='X';
            }
        }
    }
}
