package deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import job.SingletonConnection;
import job.entities.Piece;

public class PieceDaoImpl implements IPieceDao {

    @Override
    public Piece save(Piece piece) {
        try (Connection conn = SingletonConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO PIECES(NOM_PIECE, PRIX_PIECE) VALUES(?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, piece.getNomPiece());
            ps.setDouble(2, piece.getPrixPiece());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    piece.setIdPiece(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            // Gérer ou journaliser l'exception
            e.printStackTrace();
        }
        return piece;
    }

    @Override
    public List<Piece> piecesParMC(String mc) {
        List<Piece> pieces = new ArrayList<>();
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PIECES WHERE NOM_PIECE LIKE ?");
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Piece piece = new Piece();
                piece.setIdPiece(rs.getLong("ID_PIECE"));
                piece.setNomPiece(rs.getString("NOM_PIECE"));
                piece.setPrixPiece(rs.getDouble("PRIX_PIECE"));
                pieces.add(piece);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    @Override
    public Piece getPiece(Long id) {
        Piece piece = new Piece();

        try (Connection conn = SingletonConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM PIECES WHERE ID_PIECE = ?")) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    piece.setIdPiece(rs.getLong("ID_PIECE"));
                    piece.setNomPiece(rs.getString("NOM_PIECE"));
                    piece.setPrixPiece(rs.getDouble("PRIX_PIECE"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return piece;
    }


    @Override
    public Piece updatePiece(Piece piece) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE PIECES SET NOM_PIECE=?, PRIX_PIECE=? WHERE ID_PIECE=?");
            ps.setString(1, piece.getNomPiece());
            ps.setDouble(2, piece.getPrixPiece());
            ps.setLong(3, piece.getIdPiece());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piece;
    }

    @Override
    public void deletePiece(Long id) {
        Connection conn = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM PIECES WHERE ID_PIECE = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
