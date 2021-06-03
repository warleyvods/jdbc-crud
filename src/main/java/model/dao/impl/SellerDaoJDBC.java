package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entity.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT id, name, lastname FROM seller");
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();

            while (rs.next()) {
                Seller obj = instantiateSeller(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs) throws SQLException {
        return new Seller(rs.getLong("id"),
                          rs.getString("name"),
                          rs.getString("lastname"));
    }
}
