package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entity.Seller;


import java.util.Scanner;

public class Program {

   public static void main(String[] args) {

       SellerDao sellerDao = DaoFactory.createSellerDao();
       DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
       Scanner scanner = new Scanner(System.in);
    }
}
