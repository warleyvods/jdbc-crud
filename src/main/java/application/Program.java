package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entity.Seller;

import java.util.ArrayList;
import java.util.List;

public class Program {

   public static void main(String[] args) {

       SellerDao sellerDao = DaoFactory.createSellerDao();

       System.out.println("=== Find all ===");
       List<Seller> list = new ArrayList<>();
       list = sellerDao.findAll();
       for (Seller seller : list) {
           System.out.println(seller);
       }

    }
}
