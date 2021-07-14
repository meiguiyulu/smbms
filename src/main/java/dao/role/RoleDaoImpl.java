package dao.role;

import dao.BaseDao;
import pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-12 11:11
 */
public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Role> roleList = new ArrayList<>();

        if (connection != null){
            String sql = "select * from smbms_role";
            Object[] params = {};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            while (resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleCode(resultSet.getString("roleCode"));
                role.setRoleName(resultSet.getString("roleName"));
                roleList.add(role);
            }
        }
        BaseDao.closeResource(null, statement, resultSet);

        return roleList;
    }
}
