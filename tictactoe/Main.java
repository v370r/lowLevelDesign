import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("P1", Piece.O);
        Player p2 = new Player("P2", Piece.X);
        Game game = new Game(p1, p2);
        game.play();
    }

}

class Game {
    Board board;
    Player player1;
    Player player2;
    Player currPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currPlayer = player1;
        this.board = new Board();
    }

    void play() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Player " + currPlayer.name + " : Enter");
            int x = sc.nextInt();
            int y = sc.nextInt();
            Piece piece = currPlayer.piece;
            if (!board.isValidMove(x, y)) {
                System.out.println("Please enter valid move");
            }
            board.makeMove(x, y, piece);
            if (board.isFull()) {
                System.out.println("Game ends");
                break;
            }

            if (board.isWinner(currPlayer)) {
                System.out.println("Player " + currPlayer.name + " is winner");
                break;
            }

            switchPlayer();

        }
    }

    void switchPlayer() {
        currPlayer = currPlayer == player1 ? player2 : player1;
    }

}

class Cell {
    int x, y;
    Piece piece;

    public Cell(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    Piece getPiece() {
        return piece;
    }
}

class Board {
    Cell board[][];

    public Board() {
        board = new Cell[3][3];
        initializeBoard();
    }

    void initializeBoard() {
        // make every cell Piece.EMPTY
    }

    boolean isValidMove(int x, int y) {
        // check if user places on anything other than Piece.EMPTY
        return false;
    }

    boolean isFull() {
        // check if any cell has 0
        return false;
    }

    void makeMove(int x, int y, Piece piece) {
        board[x][y].setPiece(piece);
    }

    boolean isWinner(Player player) {
        // check x axis , y axis and cross same element
        return false;
    }

}

enum Piece {
    X,
    O,
    EMPTY
}

class Player {
    String name;
    Piece piece;

    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }
}
