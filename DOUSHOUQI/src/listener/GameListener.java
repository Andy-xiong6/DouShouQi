package listener;

import model.ChessboardPoint;
import view.CellComponent;
import view.JChessComponent;

public interface GameListener {

    public void onPlayerClickCell(ChessboardPoint point, CellComponent component);
    public void onPlayerClickChessPiece(ChessboardPoint point, JChessComponent component);


}
