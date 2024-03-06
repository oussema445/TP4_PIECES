package job.entities;
import java.io.Serializable;

public class Piece implements Serializable {
    private Long idPiece;
    private String nomPiece;
    private double prixPiece;

    public Piece() {
        super();
    }

    public Piece(String nomPiece, double prixPiece) {
        super();
        this.nomPiece = nomPiece;
        this.prixPiece = prixPiece;
    }

    public Long getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(Long idPiece) {
        this.idPiece = idPiece;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public double getPrixPiece() {
        return prixPiece;
    }

    public void setPrixPiece(double prixPiece) {
        this.prixPiece = prixPiece;
    }

    @Override
    public String toString() {
        return "Piece [idPiece=" + idPiece + ", nomPiece=" + nomPiece + ", prixPiece=" + prixPiece + "]";
    }
}
