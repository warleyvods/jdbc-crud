package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entity.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

   public static void main(String[] args) {

       SellerDao sellerDao = DaoFactory.createSellerDao();
       DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
       Scanner scanner = new Scanner(System.in);

       System.out.println("=== Find all ===");
       List<Seller> list = new ArrayList<>();
       list = sellerDao.findAll();
       for (Seller seller : list) {
           System.out.println(seller);
       }

//       HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
//       server.createContext("/", new MyHandler());
//       server.setExecutor(null); // creates a default executor
//       server.start();
//   }
//
//    static class MyHandler implements HttpHandler {
//        public void handle(HttpExchange t) throws IOException {
//            String response = "This is the response";
//            t.sendResponseHeaders(200, response.length());
//            OutputStream os = t.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }
    }
}
