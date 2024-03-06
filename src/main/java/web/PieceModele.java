package web;

import java.util.ArrayList;
import java.util.List;
import job.entities.Piece;

public class PieceModele {
    private String motCle;
    List<Piece> pieces = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
}
