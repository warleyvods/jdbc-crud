package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entity.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

   public static void main(String[] args) {

       SellerDao sellerDao = DaoFactory.createSellerDao();
       Scanner scanner = new Scanner(System.in);

       System.out.println("=== Insert seller ===");
       System.out.println("Insert seller name: ");
       String name = scanner.next();
       System.out.println("Insert seller last name: ");
       String lastname = scanner.next();
       Seller newSeller = new Seller(name, lastname);
       sellerDao.insert(newSeller);

       System.out.println("=== Find all ===");
       List<Seller> list = new ArrayList<>();
       list = sellerDao.findAll();
       for (Seller seller : list) {
           System.out.println(seller);
       }

       System.out.println("=== Delete by id ===");
       System.out.println("Id to delete: ");
       Long id = scanner.nextLong();
       sellerDao.deleteById(id);
    }
}
