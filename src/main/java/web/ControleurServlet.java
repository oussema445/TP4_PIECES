package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import deo.IPieceDao;
import deo.PieceDaoImpl;
import job.entities.Piece;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
    private IPieceDao metier;

    @Override
    public void init() throws ServletException {
        metier = new PieceDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/index.do")) {
            request.getRequestDispatcher("pieces.jsp").forward(request, response);
        } else if (path.equals("/chercher.do")) {
            String motCle = request.getParameter("motCle");
            PieceModele model = new PieceModele();
            model.setMotCle(motCle);
            List<Piece> pieces = metier.piecesParMC(motCle);
            model.setPieces(pieces);
            request.setAttribute("model", model);
            request.getRequestDispatcher("pieces.jsp").forward(request, response);
        } else if (path.equals("/saisie.do")) {
            request.getRequestDispatcher("saisiePiece.jsp").forward(request, response);
        } else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
            String nomPiece = request.getParameter("nomPiece");
            String prixPieceString = request.getParameter("prixPiece");

            double prixPiece = 0.0; // Valeur par défaut

            if (prixPieceString != null && !prixPieceString.isEmpty()) {
                prixPiece = Double.parseDouble(prixPieceString);
            }

            Piece piece = metier.save(new Piece(nomPiece, prixPiece));
            request.setAttribute("piece", piece);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);

        } else if (path.equals("/supprimer.do")) {
        	String idPieceParameter = request.getParameter("idPiece");
        	Long idPiece = (idPieceParameter != null && !idPieceParameter.isEmpty()) ? Long.parseLong(idPieceParameter) : null;
        	metier.deletePiece(idPiece);

            response.sendRedirect("chercher.do?motCle=");
        } else if (path.equals("/editer.do")) {
            Long idPiece = Long.parseLong(request.getParameter("idPiece"));
            Piece p = metier.getPiece(idPiece);
            request.setAttribute("piece", p);
            request.getRequestDispatcher("editerPiece.jsp").forward(request, response);
        } else if (path.equals("/update.do")) {
            Long idPiece = Long.parseLong(request.getParameter("idPiece"));
            String nomPiece = request.getParameter("nomPiece");
            double prixPiece = Double.parseDouble(request.getParameter("prixPiece"));
            Piece p = new Piece();
            p.setIdPiece(idPiece);
            p.setNomPiece(nomPiece);
            p.setPrixPiece(prixPiece);
            metier.updatePiece(p);
            request.setAttribute("piece", p);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        } else {
            response.sendError(Response.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
