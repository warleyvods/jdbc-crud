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

       System.out.println("=== Find by id ===");
       Seller foundSeller = new Seller();
       System.out.println("Insert id for seller search:");
       Long idForSearch = scanner.nextLong();
       foundSeller = sellerDao.findById(idForSearch);
       System.out.println(foundSeller);

       System.out.println("=== Update seller by id ===");
       System.out.println("Insert seller ID to update: ");
       Long sellerToUpdateId = scanner.nextLong();
       Seller sellerToUpdate = sellerDao.findById(sellerToUpdateId);
       System.out.println(sellerToUpdate);
       System.out.println("What you wanna update? \n 1 -> Name \n 2 -> Last name");
       int toUpdate = scanner.nextInt();
       if (toUpdate == 1) {
           System.out.println("Insert new name: ");
           String newName = scanner.next();
           sellerToUpdate.setName(newName);
       } else if (toUpdate == 2) {
           System.out.println("Insert new last name: ");
           String newLastName = scanner.next();
           sellerToUpdate.setLastname(newLastName);
       } else {
           System.out.println("Invalid entry");
       }
       sellerDao.update(sellerToUpdate);
       System.out.println(sellerToUpdate);

       System.out.println("=== Delete by id ===");
       System.out.println("Id to delete: ");
       Long id = scanner.nextLong();
       sellerDao.deleteById(id);
    }
}
