package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.DiscountDao;
import by.epam.tc.shop.model.entity.Discount;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDaoImpl implements DiscountDao {
    private static final DiscountDaoImpl instance = new DiscountDaoImpl();

    private static final String ADD = "INSERT INTO discount (value,date_start,date_end) VALUES (?,?,?)";
    private static final String GET_ALL = "SELECT discount.id, discount.value, date_start, date_end, product.id" +
            "FROM discount JOIN product ON product.discount_id = discount.id ";
    private static final String GET_BY_PRODUCT_ID = GET_ALL + "WHERE discount.id = ?";

    @Override
    public int add(int value, Date start, Date end) throws DaoException {
        return 0;
    }

    @Override
    public List<Discount> getAll() throws DaoException {
        List<Discount> discounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL);
             ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next())
                discounts.add(getDiscountFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return discounts;
    }

    @Override
    public Discount getByProductId(int productId) throws DaoException {
        Discount discounts = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_PRODUCT_ID))
        {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
                return getDiscountFromResultSet(resultSet);
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return discounts;
    }

    private Discount getDiscountFromResultSet(ResultSet resultSet) throws SQLException{
        Discount discount = new Discount();

        int id = resultSet.getInt("id");
        int value = resultSet.getInt("discount.value");
        Date start = resultSet.getDate("date_start");
        Date end = resultSet.getDate("date_end");

        discount.setId(id);
        discount.setValue(value);
        discount.setStart(start);
        discount.setEnd(end);

        return discount;
    }
}
