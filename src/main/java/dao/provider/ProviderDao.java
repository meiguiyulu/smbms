package dao.provider;

import pojo.Provider;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 11:11
 */
public interface ProviderDao {

    /**
     * 通过供应商名称、编码获取供应商列表--模糊查询
     */
    public List<Provider> getProviderList(Connection connection, String proName, String proCode) throws SQLException;

    /**
     * 添加供应商
     */
    public int add(Connection connection, Provider provider) throws SQLException;

    /**
     * 通过Provider的Id来删除Provider
     */
    public int deleteProviderById(Connection connection, int delId) throws SQLException;

    /**
     * 通过 proId查找 Provider
     */
    public Provider selectProviderByID(Connection connection, int proId) throws SQLException;

    /**
     * 修改用户信息
     */
    public int modifyProvider(Connection connection, Provider provider) throws SQLException;

}
