package chess;

public abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public boolean isWhite();
    public boolean isKilled();
    public abstract boolean canMove(Chessboard board, Box start, Box end);
}
