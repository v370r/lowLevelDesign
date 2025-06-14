package chess;

import java.util.Date;

public class Chessboard {
    private Box[][] boxes;
    private Date creationDate;


    public List<Piece> getPieces();
    public void resetBoard();
    public void updateBoard();
}
