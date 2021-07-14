package service.role;

import dao.BaseDao;
import dao.role.RoleDao;
import dao.role.RoleDaoImpl;
import pojo.Role;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-12 12:46
 */
public class RoleServiceImpl implements RoleService{

    RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = new ArrayList<>();
        Connection connection = null;


        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return roleList;
    }
}
