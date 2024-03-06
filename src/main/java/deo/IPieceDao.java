package deo;

import java.util.List;
import job.entities.Piece;

public interface IPieceDao {
    public Piece save(Piece piece);
    public List<Piece> piecesParMC(String mc);
    public Piece getPiece(Long id);
    public Piece updatePiece(Piece piece);
    public void deletePiece(Long id);
}
