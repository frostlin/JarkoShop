package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.OrderDao;
import by.epam.tc.shop.model.entity.Order;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final OrderDaoImpl instance = new OrderDaoImpl();
    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final AddressDaoImpl addressDao = AddressDaoImpl.getInstance();

    private static final String GET_ALL =
            "SELECT orders.id,user_id,method,address_id,'status',sum_to_pay,payed_sum,date_ordered,date_shipping,'comment'" +
                    "FROM orders " +
                    "JOIN payment_method ON orders.payment_method_id=payment_method.id ";

    private static final String ADD = "INSERT INTO orders " +
            " (user_id,payment_method_id,address_id,sum_to_pay,comment) " +
            "VALUES (?,?,?,?,?)";

    private static final String GET_BY_ID = GET_ALL + "WHERE orders.id LIKE ?";
    private static final String GET_BY_USER = GET_ALL + "WHERE orders.user_id LIKE ? ORDER BY orders.id";
    private static final String GET_LAST = GET_ALL + "WHERE o.id LIKE ? ORDER BY o.id DESC LIMIT 1";

    private static final String GET_RANGE = GET_ALL + "ORDER BY orders.id LIMIT ?, ?";
    private static final String GET_RANGE_BY_USER = GET_BY_USER + " LIMIT ?, ?";

    private static final String GET_ORDER_COUNT = "SELECT COUNT(id) AS recordCount FROM orders";

    private OrderDaoImpl() {}
    public static OrderDaoImpl getInstance(){ return instance; }

    @Override
    public int add(int userId, int paymentMethodId, int addressId, float sumToPay, String comment) throws DaoException {
        int id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD,Statement.RETURN_GENERATED_KEYS);)
        {
            statement.setInt(1, userId);
            statement.setInt(2, paymentMethodId);
            statement.setInt(3, addressId);
            statement.setFloat(4, sumToPay);
            statement.setString(5, comment);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                id = resultSet.getInt(1);
        } catch(SQLException e){
            throw new DaoException("Error inserting order ", e);
        }
        return id;
    }

    @Override
    public List<Order> getRange(int start, int offset) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RANGE))
        {
            statement.setInt(1, start);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                orders.add(getOrderFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all orders data ", e);
        }
        return orders;
    }

    @Override
    public List<Order> getRangeByUser(int start, int offset, int userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RANGE_BY_USER))
        {
            statement.setInt(1, start);
            statement.setInt(2, offset);
            statement.setInt(3, userId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                orders.add(getOrderFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all orders data ", e);
        }
        return orders;
    }

    @Override
    public Order getById(int id) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                order = getOrderFromResultSet(resultSet);
        } catch(SQLException e){
            throw new DaoException("Error getting product by id=" + id, e);
        }
        return order;
    }

    @Override
    public List<Order> getByUser(int userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_USER);)
        {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                orders.add(getOrderFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting orders for user by id=" + userId, e);
        }
        return orders;
    }

    @Override
    public int getOrderCount() throws DaoException {
        int size = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ORDER_COUNT);
             ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
                size = resultSet.getInt("recordCount");
        } catch(SQLException e){
            throw new DaoException("Error getting order count ", e);
        }

        return size;
    }



    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        Order order = new Order();

        int id =             resultSet.getInt(ColumnNames.ORDER_ID);
        int userId =         resultSet.getInt(ColumnNames.ORDER_USER_ID);
        int addressId =      resultSet.getInt(ColumnNames.ORDER_ADDRESS_ID);
        String method =      resultSet.getString(ColumnNames.ORDER_PAYMENT_METHOD);
        String status =      resultSet.getString(ColumnNames.ORDER_STATUS);
        float sumToPay =     resultSet.getFloat(ColumnNames.ORDER_SUM_TO_PAY);
        float payedSum =     resultSet.getFloat(ColumnNames.ORDER_PAYED_SUM);
        Date dateOrdered =   resultSet.getDate(ColumnNames.ORDER_DATE_ORDERED);
        Date dateShipping =  resultSet.getDate(ColumnNames.ORDER_DATE_SHIPPING);
        String comment =     resultSet.getString(ColumnNames.ORDER_COMMENT);

        order.setId(id);
        order.setUser(userDao.getById(userId).get());
        order.setAddress(addressDao.getById(addressId));
        order.setPaymentMethod(method);
        order.setStatus(status);
        order.setSumToPay(sumToPay);
        order.setPayedSum(payedSum);
        order.setDateOrdered(dateOrdered);
        order.setDateShipping(dateShipping);
        order.setComment(comment);

        return order;
    }

}