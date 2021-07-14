package service.provider;

import pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 11:25
 */
public interface ProviderService {

    /**
     * 通过供应商名称、编码获取供应商列表--模糊查询
     */
    public List<Provider> getProviderList(String proName, String proCode);

    /**
     * 添加供应商
     */
    public boolean add(Provider provider);

    /**
     * 通过Provider的Id来删除Provider
     */
    public int deleteProviderById(int delId);


    /**
     * 通过 proId查找 Provider
     */
    public Provider selectProviderByID(int proId);

    /**
     * 修改用户信息
     */
    public boolean modifyProvider(Provider provider);

}
