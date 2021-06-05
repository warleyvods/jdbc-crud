package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entity.Department;
import model.entity.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private final Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*, department.name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.department_id = department.id " +
                    "ORDER BY name");
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Long, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getLong("department_id"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getLong("department_id"), dep);
                }

                Seller obj = instantiateSeller(rs, dep);
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

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO seller " +
                            "(name, lastname, email, salary, department_id) " +
                            "VALUES (?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getLastname());
            st.setString(3, obj.getEmail());
            st.setDouble(4, obj.getSalary());
            st.setLong(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    Long id = rs.getLong(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM seller WHERE id = ?");
            st.setLong(1, id);
            st.executeUpdate();
            System.out.println("Seller deleted with ID: " + id);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT seller.*, department.name " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.department_id = department.id " +
                    "WHERE seller.id = ? ");
            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return instantiateSeller(rs, dep);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE seller " +
                    "SET name = ?, lastname = ?," +
                    "email = ?, salary = ?, department_id = ? " +
                    " WHERE id = ? ");
            st.setString(1, obj.getName());
            st.setString(2, obj.getLastname());
            st.setString(3, obj.getEmail());
            st.setDouble(4, obj.getSalary());
            st.setLong(5, obj.getDepartment().getId());
            st.setLong(6, obj.getId());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Seller updated with ID: " + obj.getId());
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        return new Seller(rs.getLong("id"),
                          rs.getString("name"),
                          rs.getString("lastname"),
                          rs.getString("email"),
                          rs.getDouble("salary"),
                          dep);
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        return new Department(
                rs.getLong("id"),
                rs.getString("name"));
    }
}
