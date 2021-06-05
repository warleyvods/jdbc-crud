package model.dao;

import model.entity.Seller;

import java.util.List;

public interface SellerDao {

    List<Seller> findAll();
    void insert(Seller obj);
    void deleteById(Long id);
    Seller findById(Long id);
    void update(Seller obj);
}
