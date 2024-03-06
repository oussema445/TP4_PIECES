package deo;

import java.util.List;
import job.entities.Piece;

public class Testdao {
    public static void main(String[] args) {
        PieceDaoImpl pieceDao = new PieceDaoImpl();

        
        Piece piece = pieceDao.save(new Piece("Compteur", 360.0));
        System.out.println("Saved Piece: " + piece);

        
        List<Piece> pieces = pieceDao.piecesParMC("Com");
        System.out.println("Pieces with 'Com' in the name:");
        for (Piece p : pieces) {
            System.out.println(p);
        }
    }
}
