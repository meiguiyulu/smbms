package servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import pojo.Bill;
import pojo.Provider;
import pojo.User;
import service.bill.BillService;
import service.bill.BillServiceImpl;
import service.provider.ProviderService;
import service.provider.ProviderServiceImpl;
import service.user.UserService;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author LYJ
 * @create 2021-07-13 9:42
 */
public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method!=null && "add".equals(method)){
            this.add(req, resp);
        } else if (method!=null && "query".equals(method)){
            this.query(req, resp);
        } else if (method!=null && "billid".equals(method)){
            this.delBill(req, resp);
        } else if (method!=null && "toBillAdd".equals(method)){
            this.toBillAdd(req, resp);
        } else if (method!=null && "delBill".equals(method)){
            this.delBill(req, resp);
        } else if (method!=null && "view".equals(method)){
            this.getBillById(req, resp, "billview.jsp");
        } else if (method!=null && "modify".equals(method)){
            this.getBillById(req, resp, "billmodify.jsp");
        } else if (method!=null && "modifysave".equals(method)){
            this.modify(req, resp);
        }
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");

        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());

        boolean flag;
        BillService billService = new BillServiceImpl();
        flag = billService.add(bill);
        if (flag){
            resp.sendRedirect(req.getContextPath() + "/jsp/bill.do?method=query");
        } else {
            req.getRequestDispatcher("billadd.jsp").forward(req, resp);
        }
    }

    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Provider> providerList;
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");
            req.setAttribute("providerList", providerList);

        String queryProductName = req.getParameter("queryProductName");
        String queryProviderId = req.getParameter("queryProviderId");
        String queryIsPayment = req.getParameter("queryIsPayment");
        if (StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
        }

        List<Bill> billList;
        BillService billService = new BillServiceImpl();
        Bill bill = new Bill();
        if (StringUtils.isNullOrEmpty(queryIsPayment)){
            bill.setIsPayment(0);
        } else {
            bill.setIsPayment(Integer.parseInt(queryIsPayment));
        }

        if (StringUtils.isNullOrEmpty(queryProviderId)){
            bill.setProviderId(0);
        } else {
            bill.setProviderId(Integer.parseInt(queryProviderId));
        }
        bill.setProductName(queryProductName);
        billList = billService.getBillList(bill);
        req.setAttribute("billList", billList);
        req.setAttribute("queryProductName", queryProductName);
        req.setAttribute("queryProviderId", queryProviderId);
        req.setAttribute("queryIsPayment", queryIsPayment);
        req.getRequestDispatcher("billlist.jsp").forward(req, resp);

    }

    public void delBill(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String billid = req.getParameter("billid");
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isNullOrEmpty(billid)){
            BillService service = new BillServiceImpl();
            if (service.deleteBillById(Integer.parseInt(billid))){
                map.put("delResult", "true");
            } else {
                map.put("delResult", "false");
            }
        } else {
            map.put("delResult", "notexist");
        }

        // 把resultMap转换成JSON输出
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }

    public void toBillAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Provider> providerList = new ArrayList<>();

        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");

        req.setAttribute("providerList", providerList);
        req.getRequestDispatcher("billadd.jsp").forward(req, resp);
    }

    public void getBillById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String id = req.getParameter("billid");
        if (!StringUtils.isNullOrEmpty(id)){
            BillService service = new BillServiceImpl();
            Bill bill = service.getBillById(Integer.valueOf(id));
            req.setAttribute("bill", bill);

            List<Provider> providerList = new ArrayList<>();
            ProviderService providerService = new ProviderServiceImpl();
            providerList = providerService.getProviderList("", "");
            req.setAttribute("providerList", providerList);
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    public void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setId(Integer.valueOf(id));
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));

        bill.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.modify(bill);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
        }else{
            req.getRequestDispatcher("billmodify.jsp").forward(req, resp);
        }
    }
}
