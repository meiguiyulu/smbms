package dao.bill;

import pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 9:27
 */
public interface BillDao {

    /**
     * 添加订单
     * @param connection
     * @param bill
     * @return
     */
    public int add(Connection connection, Bill bill) throws SQLException;

    /**
     * 通过查询条件获取供应商列表-模糊查询-getBillList
     * @param connection
     * @param bill
     * @return
     */
    public List<Bill> getBillList(Connection connection, Bill bill) throws SQLException;

    /**
     * 删除订单
     * @param connection
     * @param delId
     * @return
     */

    public int deleteBillById(Connection connection, int delId) throws SQLException;

    /**
     * 根据Id查看订单
     */
    public Bill getBillById(Connection connection, int id) throws SQLException;

    /**
     * 修改订单信息
     */
    public int modify(Connection connection, Bill bill) throws SQLException;

    /**
     * 根据providerIdId查询某一家供应商的订单数量
     * @return
     */
    public int getBillCountByProviderById(Connection connection, int providerId) throws SQLException;

}
