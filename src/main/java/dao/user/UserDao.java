package dao.user;

import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-11 18:43
 */
public interface UserDao {
    public User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException;

    /**
     * 修改用户密码
     */
    public int updatePwd(Connection connection, int id, String userPassword) throws SQLException;

    /**
     * 通过条件查询-用户表记录数
     * @param connection
     * @param userName
     * @param userRole
     * @return
     * @throws Exception
     */
    public int getUserCount(Connection connection, String userName, int userRole)throws Exception;

    /**
     * 通过条件查询-userList
     * @param connection
     * @param userName
     * @param userRole
     * @return
     * @throws Exception
     */
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;

    /**
     * 添加用户
     * @param connection
     * @param user
     * @return
     */
    public int add(Connection connection, User user) throws SQLException;

    /**
     * 删除用户
     * @param connection
     * @param id
     * @return
     */
    public int deleteUserById(Connection connection, int id) throws SQLException;

    //通过userId查看当前用户信息
    public User selectUserById(Connection connection, int id) throws SQLException;

    //修改用户信息
    public int modify(Connection connection, User user) throws SQLException;
}
