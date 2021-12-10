package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entity.Seller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {

   public static void main(String[] args) throws SQLException {

       SellerDao sellerDao = DaoFactory.createSellerDao();
       Scanner scanner = new Scanner(System.in);

       System.out.println("=== Salvar um seller ===");
       sellerDao.insert(new Seller(1L, "Warley", "Vinicius", "warleyvods@gmail.com", 6400D));


//       System.out.println("=== Buscar um seller por id ===");
//       Long idForSearch = 1L;
//       Seller foundSeller  = sellerDao.findById(idForSearch);
//       System.out.println(foundSeller);

//
//       System.out.println("=== Buscar todo mundo ===");
//       List<Seller> list = sellerDao.findAll();
//       for (Seller seller : list) {
//           System.out.println(seller);
//       }

//       System.out.println("=== Deletar por id ===");
//       sellerDao.deleteById(10L);

    }
}
