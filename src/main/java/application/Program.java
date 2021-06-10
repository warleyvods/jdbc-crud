package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entity.Seller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

   public static void main(String[] args) throws SQLException {

       SellerDao sellerDao = DaoFactory.createSellerDao();
       DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
       Scanner scanner = new Scanner(System.in);

       System.out.println("=== Find by id ===");
       Seller foundSeller = new Seller();
       Long idForSearch = 1L;
       foundSeller = sellerDao.findById(idForSearch);
       System.out.println(foundSeller);

       System.out.println("=== Find all ===");
       List<Seller> list = new ArrayList<>();
       list = sellerDao.findAll();
       for (Seller seller : list) {
           System.out.println(seller);
       }
    }
}
