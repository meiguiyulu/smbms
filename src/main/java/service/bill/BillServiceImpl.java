package service.bill;

import dao.BaseDao;
import dao.bill.BillDao;
import dao.bill.BillDaoImpl;
import pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 9:36
 */
public class BillServiceImpl implements BillService {

    BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    @Override
    public boolean add(Bill bill) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (billDao.add(connection, bill) > 0){
                flag = true;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("BillServiceImpl---->rollback");
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
        Connection connection = null;
        List<Bill> billList = new ArrayList<>();

        try {
            connection = BaseDao.getConnection();
            billList = billDao.getBillList(connection, bill);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return billList;
    }

    @Override
    public boolean deleteBillById(int delId) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if (billDao.deleteBillById(connection, delId)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public Bill getBillById(int id) {
        Connection connection = null;
        Bill bill = null;

        try {
            connection = BaseDao.getConnection();
            bill = billDao.getBillById(connection, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return bill;
    }

    @Override
    public boolean modify(Bill bill) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            if (billDao.modify(connection, bill) > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

//    @Override
//    public int getBillCountByProviderById(int providerId) {
//        int num = 0;
//        Connection connection = null;
//
//        try {
//            connection = BaseDao.getConnection();
//            num = billDao.getBillCountByProviderById(connection, providerId);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//
//        return num;
//    }
}
