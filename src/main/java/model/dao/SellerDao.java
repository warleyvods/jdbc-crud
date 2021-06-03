package model.dao;

import model.entity.Seller;

import java.util.List;

public interface SellerDao {

    List<Seller> findAll();
}
