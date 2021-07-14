package dao.provider;

import com.mysql.cj.util.StringUtils;
import dao.BaseDao;
import pojo.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-13 11:18
 */
public class ProviderDaoImpl implements ProviderDao{

    @Override
    public List<Provider> getProviderList(Connection connection, String proName, String proCode) throws SQLException {
        List<Provider> providers = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        if (connection!=null){
            StringBuilder builder = new StringBuilder();
            builder.append("select * from smbms_provider where 1=1");
            List<Object> list = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(proName)){
                builder.append(" and proName like ?");
                list.add("%" + proName + "%");
            }
            if (!StringUtils.isNullOrEmpty(proCode)){
                builder.append(" and proCode like ?");
                list.add("%" + proCode + "%");
            }
            Object[] params = list.toArray();
            resultSet = BaseDao.execute(connection, builder.toString(), params, resultSet, statement);
            while (resultSet.next()){
                Provider provider = new Provider();

                provider.setId(resultSet.getInt("id"));
                provider.setProCode(resultSet.getString("proCode"));
                provider.setProName(resultSet.getString("proName"));
                provider.setProDesc(resultSet.getString("proDesc"));
                provider.setProContact(resultSet.getString("proContact"));
                provider.setProPhone(resultSet.getString("proPhone"));
                provider.setProAddress(resultSet.getString("proAddress"));
                provider.setProFax(resultSet.getString("proFax"));
                provider.setCreationDate(resultSet.getTimestamp("creationDate"));

                providers.add(provider);
            }
            BaseDao.closeResource(null, statement, resultSet);
        }

        return providers;
    }

    @Override
    public int add(Connection connection, Provider provider) throws SQLException {
        int num = 0;
        PreparedStatement statement = null;

        if (connection != null){
            String sql = "insert into smbms_provider(proCode,proName,proDesc," +
                    "proContact,proPhone,proAddress,proFax,createdBy,creationDate)" +
                    "values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {provider.getProCode(), provider.getProName(), provider.getProDesc(),
            provider.getProContact(), provider.getProPhone(), provider.getProAddress(), provider.getProFax(),
            provider.getCreatedBy(), provider.getCreationDate()};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }

        return num;
    }

    @Override
    public int deleteProviderById(Connection connection, int delId) throws SQLException {
        PreparedStatement statement = null;
        int num = 0;

        if (connection!=null){
            String sql = "delete from smbms_provider where id = ?";
            Object[] params = {};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public Provider selectProviderByID(Connection connection, int proId) throws SQLException {
        int num = 0;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Provider provider = null;

        if (connection!=null){
            String sql = "select * from smbms_provider where id = ?";
            Object[] params = {proId};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                provider = new Provider();
                provider.setId(resultSet.getInt("id"));
                provider.setProCode(resultSet.getString("proCode"));
                provider.setProName(resultSet.getString("proName"));
                provider.setProDesc(resultSet.getString("proDesc"));
                provider.setProContact(resultSet.getString("proContact"));
                provider.setProPhone(resultSet.getString("proPhone"));
                provider.setProAddress(resultSet.getString("proAddress"));
                provider.setProFax(resultSet.getString("proFax"));
                provider.setCreatedBy(resultSet.getInt("createdBy"));
                provider.setCreationDate(resultSet.getTimestamp("creationDate"));
                provider.setModifyBy(resultSet.getInt("modifyBy"));
                provider.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return provider;
    }

    @Override
    public int modifyProvider(Connection connection, Provider provider) throws SQLException {
        int num = 0;
        PreparedStatement statement = null;
        if (connection!=null){
            String sql = "update smbms_provider set proName=?,proDesc=?,proContact=?," +
                    "proPhone=?,proAddress=?,proFax=?,modifyBy=?,modifyDate=? where id = ?";
            Object[] params = {provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
                    provider.getProFax(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()};
            num = BaseDao.execute(connection, sql, params, statement);

            System.out.println("==========================");
            System.out.println("modifyProvider====> num: " + num);
            System.out.println("==========================");

            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }
}
