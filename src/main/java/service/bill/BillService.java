package service.bill;

import pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 9:35
 */
public interface BillService {

    /**
     * 添加订单
     * @param bill
     * @return
     */
    public boolean add(Bill bill);

    /**
     * 通过查询条件获取供应商列表-模糊查询-getBillList
     * @param bill
     * @return
     */
    public List<Bill> getBillList(Bill bill);

    /**
     * 删除订单
     */

    public boolean deleteBillById(int delId);

    /**
     * 根据Id查看订单
     */
    public Bill getBillById(int id);

    /**
     * 修改订单信息
     */
    public boolean modify(Bill bill);

    /**
     * 根据providerIdId查询某一家供应商的订单数量
     * @return
     */
//    public int getBillCountByProviderById(int providerId);

}
