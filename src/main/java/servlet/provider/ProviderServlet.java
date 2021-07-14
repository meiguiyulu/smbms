package servlet.provider;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import pojo.Provider;
import pojo.User;
import service.provider.ProviderService;
import service.provider.ProviderServiceImpl;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author LYJ
 * @create 2021-07-13 11:29
 */
public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method!=null && "query".equals(method)){
            this.query(req, resp);
        } else if (method!=null && "add".equals(method)){
            this.add(req, resp);
        } else if (method!=null && "delprovider".equals(method)){
            this.delProvider(req, resp);
        } else if (method!=null && "view".equals(method)){
            this.selectProviderById(req, resp, "providerview.jsp");
        } else if (method!=null && "modify".equals(method)){
            this.selectProviderById(req, resp, "providermodify.jsp");
        } else if (method!=null && "modifysave".equals(method)){
            this.modifyProvider(req, resp);
        }

    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String queryProCode = req.getParameter("queryProCode");
        String queryProName = req.getParameter("queryProName");
        if (StringUtils.isNullOrEmpty(queryProCode)){
            queryProCode = "";
        }
        if (StringUtils.isNullOrEmpty(queryProName)){
            queryProName = "";
        }
        List<Provider> providerList = new ArrayList<>();

        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList(queryProName, queryProCode);
        req.setAttribute("providerList", providerList);
        req.setAttribute("queryProName", queryProName);
        req.setAttribute("queryProCode", queryProCode);
        req.getRequestDispatcher("providerlist.jsp").forward(req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setCreationDate(new Date());

        ProviderService service = new ProviderServiceImpl();
        if (service.add(provider)){
            resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
        } else {
            req.getRequestDispatcher("provideradd.jsp").forward(req, resp);
        }

    }

    public void delProvider(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String proid = req.getParameter("proid");
        Map<String, String> map = new HashMap<>();

        if (StringUtils.isNullOrEmpty(proid)){
            map.put("delResult", "notexist");
        } else {
            ProviderService service = new ProviderServiceImpl();
            int num = service.deleteProviderById(Integer.parseInt(proid));
            if (num == 0){ // 删除成功
                map.put("delResult", "true");
            } else if (num==-1){ // 删除失败
                map.put("delResult", "false");
            } else if (num>0){ // 该订单商下有订单, 不能删除, 返回订单数
                map.put("delResult", String.valueOf(num));
            }
        }

        // 转换成JSON输出
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }

    public void selectProviderById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String proid = req.getParameter("proid");
        Provider provider = new Provider();
        ProviderService service = new ProviderServiceImpl();
        if (!StringUtils.isNullOrEmpty(proid)){
            provider = service.selectProviderByID(Integer.parseInt(proid));
            req.setAttribute("provider", provider);
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    public void modifyProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");

        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");
        String id = req.getParameter("id");
        Provider provider = new Provider();
        provider.setId(Integer.valueOf(id));
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);
        provider.setProDesc(proDesc);
        provider.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        boolean flag = false;
        ProviderService providerService = new ProviderServiceImpl();
        flag = providerService.modifyProvider(provider);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
        }else{
            req.getRequestDispatcher("providermodify.jsp").forward(req, resp);
        }
    }

}
