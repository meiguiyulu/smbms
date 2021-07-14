package dao.bill;

import com.mysql.cj.util.StringUtils;
import dao.BaseDao;
import pojo.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * @author LYJ
 * @create 2021-07-13 9:28
 */
public class BillDaoImpl implements BillDao {
    @Override
    public int add(Connection connection, Bill bill) throws SQLException {
        int num = 0;
        PreparedStatement statement = null;

        if (connection != null){
            String sql = "insert into smbms_bill (billCode,productName,productDesc, " +
                    "productUnit,productCount,totalPrice,isPayment,providerId,createdBy,creationDate) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";

            Object[] params = {bill.getBillCode(), bill.getProductName(), bill.getProductDesc(),
            bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(),
            bill.getProviderId(), bill.getCreatedBy(), bill.getCreationDate()};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws SQLException {
        List<Bill> billList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection!=null){
            StringBuilder sql = new StringBuilder();
            sql.append("select b.*, p.proName as providerName\n" +
                    "from smbms_bill as b, smbms_provider as p\n" +
                    "where b.providerId = p.id");
            List<Object> list = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(bill.getProductName())){
                sql.append(" and b.productName like ?");
                list.add("%" + bill.getProductName() + "%");
            }
            if (bill.getProviderId() > 0){
                sql.append(" and b.providerId = ?");
                list.add(bill.getProviderId());
            }
            if (bill.getIsPayment() > 0){
                sql.append(" and b.isPayment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            resultSet = BaseDao.execute(connection, sql.toString(), params, resultSet, statement);
            while (resultSet.next()){
                Bill bill1 = new Bill();

                bill1.setId(resultSet.getInt("id"));
                bill1.setBillCode(resultSet.getString("billCode"));
                bill1.setProductName(resultSet.getString("productName"));
                bill1.setProductDesc(resultSet.getString("productDesc"));
                bill1.setProductUnit(resultSet.getString("productUnit"));
                bill1.setProductCount(resultSet.getBigDecimal("productCount"));
                bill1.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
                bill1.setIsPayment(resultSet.getInt("isPayment"));
                bill1.setProviderId(resultSet.getInt("providerId"));
                bill1.setProviderName(resultSet.getString("providerName"));
                bill1.setCreationDate(resultSet.getTimestamp("creationDate"));
                bill1.setCreatedBy(resultSet.getInt("createdBy"));

                billList.add(bill1);
            }
            BaseDao.closeResource(null, statement, resultSet);
        }

        return billList;
    }

    @Override
    public int deleteBillById(Connection connection, int delId) throws SQLException {
        PreparedStatement statement = null;
        int num = 0;
        if (connection != null){
            String sql = "delete form smbms_bill where id = ?";
            Object[] params = {delId};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public Bill getBillById(Connection connection, int id) throws SQLException {
        Bill bill = new Bill();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null){
            String sql = "select b.*, p.proName as providerName from smbms_bill as b, smbms_provider as p\n" +
                    "where b.providerId = p.id and b.id = ?";
            Object[] params = {id};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                bill.setId(resultSet.getInt("id"));
                bill.setBillCode(resultSet.getString("billCode"));
                bill.setProductName(resultSet.getString("productName"));
                bill.setProductDesc(resultSet.getString("productDesc"));
                bill.setProductUnit(resultSet.getString("productUnit"));
                bill.setProductCount(resultSet.getBigDecimal("productCount"));
                bill.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
                bill.setIsPayment(resultSet.getInt("isPayment"));
                bill.setProviderId(resultSet.getInt("providerId"));
                bill.setProviderName(resultSet.getString("providerName"));
                bill.setModifyBy(resultSet.getInt("modifyBy"));
                bill.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return bill;
    }

    @Override
    public int modify(Connection connection, Bill bill) throws SQLException {
        int num = 0;
        PreparedStatement statement = null;

        if (connection != null){
            String sql = "update smbms_bill set productName=?," +
                    "productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
                    "isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ?";
            Object[] params = {bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public int getBillCountByProviderById(Connection connection, int providerId) throws SQLException {

        ResultSet resultSet = null;
        PreparedStatement statement = null;
        int num = 0;
        if (connection != null){
            String sql = "select COUNT(1) as billCount " +
                    "from smbms_bill where providerId = ?";
            Object[] params = {providerId};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                num = resultSet.getInt("billCount");
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return num;
    }
}
