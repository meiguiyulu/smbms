package dao.role;

import pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-12 11:10
 */
public interface RoleDao {

    // 获取角色列表
    public List<Role> getRoleList(Connection connection)throws Exception;
}
