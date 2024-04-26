package com.Java.TicTacToe;

class TicTacToeApp{
    public static void main(String[] args) {
        new TicTacToe();//No need of storing the Object as all the methods are Static, this action is only to execute the Constructor of TicTac class.
        TicTacToe.dispBoard(); //Displays the structure of board with space values
        System.out.println("So..Before you Play kindly read the set of Rules :\n");
        System.out.println("1)There are totally 9 boxes with respective numbers");
        System.out.println("2)For One player either 'X' or 'O' is the MARK");
        System.out.println("3)You Can't enter the Box number Beyond what is allocated to you");
        System.out.println("4)Players are not allowed to Enter the Box Number or MARK which is already taken by Another player\n");

        while(true){ //This loop will be alive until any one of the player wins where the program get terminated by exit code( System.exit(0) )
            TicTacToe.player1();
            TicTacToe.player2();
        }
    }
}
