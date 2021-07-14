package service.provider;

import dao.BaseDao;
import dao.bill.BillDao;
import dao.bill.BillDaoImpl;
import dao.provider.ProviderDao;
import dao.provider.ProviderDaoImpl;
import pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 11:26
 */
public class ProviderServiceImpl implements ProviderService{

    ProviderDao providerDao;
    BillDao billDao;

    public ProviderServiceImpl() {
        providerDao = new ProviderDaoImpl();
        billDao = new BillDaoImpl();
    }

    @Override
    public List<Provider> getProviderList(String proName, String proCode) {
        List<Provider> providers = new ArrayList<>();
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            providers = providerDao.getProviderList(connection, proName, proCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return providers;
    }

    @Override
    public boolean add(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (providerDao.add(connection, provider)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    /**
     * 业务：根据ID删除供应商的数据之前，需要先去订单表进行查询
     * 若订单中无该供应商的订单数据，则可以删除
     * 若有该供应商的订单数据，则不可以删除
     */
    @Override
    public int deleteProviderById(int delId) {
        Connection connection = null;
        int billCount = -1;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            billCount = billDao.getBillCountByProviderById(connection, delId);
            if (billCount == 0){
                providerDao.deleteProviderById(connection, delId);
            }
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            billCount = -1;
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return billCount;
    }

    @Override
    public Provider selectProviderByID(int proId) {
        Provider provider = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            provider = providerDao.selectProviderByID(connection, proId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return provider;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            if (providerDao.modifyProvider(connection, provider) > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }
}
