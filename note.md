# smbms

## 1、前期准备

### 1.1、新建项目

#### 1.1.1、新建Maven项目

![image-20210705092301920](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705092301920.png)

#### 1.1.2、添加web依赖

![image-20210705092336558](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705092336558.png)

![image-20210705092401667](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705092401667.png)

#### 1.1.3 配置Tomcat

![image-20210705095138033](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705095138033.png)

![image-20210705095204169](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705095204169.png)

#### 1.1.4 导入jar包

```xml
    <dependencies>
        <!-- https://mvnrepository.com/artifact/javax.servlet/servlet-api -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/jsp-api -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
            <scope>provided</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!--mysql驱动-->
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.16</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/taglibs/standard -->
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>
    </dependencies>
```

### 1.2、创建数据库

#### 1.2.1、新建数据库

```my
ccreate database `smbms`
```

#### 1.2.2、新建表

```mysql
DROP TABLE IF EXISTS `smbms_address`;
CREATE TABLE `smbms_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `contact` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  `addressDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货地址明细',
  `postCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮编',
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系人电话',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `smbms_bill`;
CREATE TABLE `smbms_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `billCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '账单编码',
  `productName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `productDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `productUnit` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品单位',
  `productCount` decimal(20,2) DEFAULT NULL COMMENT '商品数量',
  `totalPrice` decimal(20,2) DEFAULT NULL COMMENT '商品总额',
  `isPayment` int(10) DEFAULT NULL COMMENT '是否支付（1：未支付 2：已支付）',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `providerId` int(20) DEFAULT NULL COMMENT '供应商ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `smbms_provider`;
CREATE TABLE `smbms_provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `proCode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商编码',
  `proName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商名称',
  `proDesc` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商详细描述',
  `proContact` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '供应商联系人',
  `proPhone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `proAddress` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `proFax` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '传真',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `smbms_role`;
CREATE TABLE `smbms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `roleCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `roleName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `smbms_user`;
CREATE TABLE `smbms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户编码',
  `userName` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `userPassword` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `gender` int(10) DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `address` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `userRole` int(10) DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
  `createdBy` bigint(20) DEFAULT NULL COMMENT '创建者（userId）',
  `creationDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyBy` bigint(20) DEFAULT NULL COMMENT '更新者（userId）',
  `modifyDate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
```

#### 1.2.3、插入测试数据

```mysql
INSERT INTO `smbms_address` VALUES ('1', '王丽', '北京市东城区东交民巷44号', '100010', '13678789999', '1', '2016-04-13 00:00:00', null, null, '1');
INSERT INTO `smbms_address` VALUES ('2', '张红丽', '北京市海淀区丹棱街3号', '100000', '18567672312', '1', '2016-04-13 00:00:00', null, null, '1');
INSERT INTO `smbms_address` VALUES ('3', '任志强', '北京市东城区美术馆后街23号', '100021', '13387906742', '1', '2016-04-13 00:00:00', null, null, '1');
INSERT INTO `smbms_address` VALUES ('4', '曹颖', '北京市朝阳区朝阳门南大街14号', '100053', '13568902323', '1', '2016-04-13 00:00:00', null, null, '2');
INSERT INTO `smbms_address` VALUES ('5', '李慧', '北京市西城区三里河路南三巷3号', '100032', '18032356666', '1', '2016-04-13 00:00:00', null, null, '3');
INSERT INTO `smbms_address` VALUES ('6', '王国强', '北京市顺义区高丽营镇金马工业区18号', '100061', '13787882222', '1', '2016-04-13 00:00:00', null, null, '3');


INSERT INTO `smbms_bill` VALUES ('1', 'BILL2016_001', '洗发水、护发素', '日用品-洗发、护发', '瓶', '500.00', '25000.00', '2', '1', '2014-12-14 13:02:03', '15', '2019-04-16 21:43:12', '13');
INSERT INTO `smbms_bill` VALUES ('2', 'BILL2016_002', '香皂、肥皂、药皂', '日用品-皂类', '块', '1000.00', '10000.00', '2', '1', '2016-03-23 04:20:40', null, null, '13');
INSERT INTO `smbms_bill` VALUES ('3', 'BILL2016_003', '大豆油', '食品-食用油', '斤', '300.00', '5890.00', '2', '1', '2014-12-14 13:02:03', null, null, '6');
INSERT INTO `smbms_bill` VALUES ('4', 'BILL2016_004', '橄榄油', '食品-进口食用油', '斤', '200.00', '9800.00', '2', '1', '2013-10-10 03:12:13', null, null, '7');
INSERT INTO `smbms_bill` VALUES ('5', 'BILL2016_005', '洗洁精', '日用品-厨房清洁', '瓶', '500.00', '7000.00', '2', '1', '2014-12-14 13:02:03', null, null, '9');
INSERT INTO `smbms_bill` VALUES ('6', 'BILL2016_006', '美国大杏仁', '食品-坚果', '袋', '300.00', '5000.00', '2', '1', '2016-04-14 06:08:09', null, null, '4');
INSERT INTO `smbms_bill` VALUES ('7', 'BILL2016_007', '沐浴液、精油', '日用品-沐浴类', '瓶', '500.00', '23000.00', '1', '1', '2016-07-22 10:10:22', null, null, '14');
INSERT INTO `smbms_bill` VALUES ('8', 'BILL2016_008', '不锈钢盘碗', '日用品-厨房用具', '个', '600.00', '6000.00', '2', '1', '2016-04-14 05:12:13', null, null, '14');
INSERT INTO `smbms_bill` VALUES ('9', 'BILL2016_009', '塑料杯', '日用品-杯子', '个', '350.00', '1750.00', '2', '1', '2016-02-04 11:40:20', null, null, '14');
INSERT INTO `smbms_bill` VALUES ('10', 'BILL2016_010', '豆瓣酱', '食品-调料', '瓶', '200.00', '2000.00', '2', '1', '2013-10-29 05:07:03', null, null, '8');
INSERT INTO `smbms_bill` VALUES ('11', 'BILL2016_011', '海之蓝', '饮料-国酒', '瓶', '50.00', '10000.00', '1', '1', '2016-04-14 16:16:00', null, null, '1');
INSERT INTO `smbms_bill` VALUES ('12', 'BILL2016_012', '芝华士', '饮料-洋酒', '瓶', '20.00', '6000.00', '1', '1', '2016-09-09 17:00:00', null, null, '1');
INSERT INTO `smbms_bill` VALUES ('13', 'BILL2016_013', '长城红葡萄酒', '饮料-红酒', '瓶', '60.00', '800.00', '2', '1', '2016-11-14 15:23:00', null, null, '1');
INSERT INTO `smbms_bill` VALUES ('14', 'BILL2016_014', '泰国香米', '食品-大米', '斤', '400.00', '5000.00', '2', '1', '2016-10-09 15:20:00', null, null, '3');
INSERT INTO `smbms_bill` VALUES ('15', 'BILL2016_015', '东北大米', '食品-大米', '斤', '600.00', '4000.00', '2', '1', '2016-11-14 14:00:00', null, null, '3');
INSERT INTO `smbms_bill` VALUES ('16', 'BILL2016_016', '可口可乐', '饮料', '瓶', '2000.00', '6000.00', '2', '1', '2012-03-27 13:03:01', null, null, '2');
INSERT INTO `smbms_bill` VALUES ('17', 'BILL2016_017', '脉动', '饮料', '瓶', '1500.00', '4500.00', '2', '1', '2016-05-10 12:00:00', null, null, '2');

INSERT INTO `smbms_provider` VALUES ('1', 'BJ_GYS001', '北京三木堂商贸有限公司', '长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等', '张国强', '13566669999', '北京市丰台区育芳园北路', '010-58858787', '1', '2013-03-21 16:52:07', '2019-04-12 16:44:03', '10');
INSERT INTO `smbms_provider` VALUES ('2', 'HB_GYS001', '石家庄帅益食品贸易有限公司', '长期合作伙伴，主营产品:饮料、水饮料、植物蛋白饮料、休闲食品、果汁饮料、功能饮料等', '王军', '13309094212', '河北省石家庄新华区', '0311-67738876', '1', '2016-04-13 04:20:40', null, null);
INSERT INTO `smbms_provider` VALUES ('3', 'GZ_GYS001', '深圳市泰香米业有限公司', '初次合作伙伴，主营产品：良记金轮米,龙轮香米等', '郑程瀚', '13402013312', '广东省深圳市福田区深南大道6006华丰大厦', '0755-67776212', '1', '2014-03-21 16:56:07', null, null);
INSERT INTO `smbms_provider` VALUES ('4', 'GZ_GYS002', '深圳市喜来客商贸有限公司', '长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉', '林妮', '18599897645', '广东省深圳市福龙工业区B2栋3楼西', '0755-67772341', '1', '2013-03-22 16:52:07', null, null);
INSERT INTO `smbms_provider` VALUES ('5', 'JS_GYS001', '兴化佳美调味品厂', '长期合作伙伴，主营产品：天然香辛料、鸡精、复合调味料', '徐国洋', '13754444221', '江苏省兴化市林湖工业区', '0523-21299098', '1', '2015-11-22 16:52:07', null, null);
INSERT INTO `smbms_provider` VALUES ('6', 'BJ_GYS002', '北京纳福尔食用油有限公司', '长期合作伙伴，主营产品：山茶油、大豆油、花生油、橄榄油等', '马莺', '13422235678', '北京市朝阳区珠江帝景1号楼', '010-588634233', '1', '2012-03-21 17:52:07', null, null);
INSERT INTO `smbms_provider` VALUES ('7', 'BJ_GYS003', '北京国粮食用油有限公司', '初次合作伙伴，主营产品：花生油、大豆油、小磨油等', '王驰', '13344441135', '北京大兴青云店开发区', '010-588134111', '1', '2016-04-13 00:00:00', null, null);
INSERT INTO `smbms_provider` VALUES ('8', 'ZJ_GYS001', '慈溪市广和绿色食品厂', '长期合作伙伴，主营产品：豆瓣酱、黄豆酱、甜面酱，辣椒，大蒜等农产品', '薛圣丹', '18099953223', '浙江省宁波市慈溪周巷小安村', '0574-34449090', '1', '2013-11-21 06:02:07', null, null);
INSERT INTO `smbms_provider` VALUES ('9', 'GX_GYS001', '优百商贸有限公司', '长期合作伙伴，主营产品：日化产品', '李立国', '13323566543', '广西南宁市秀厢大道42-1号', '0771-98861134', '1', '2013-03-21 19:52:07', null, null);
INSERT INTO `smbms_provider` VALUES ('10', 'JS_GYS002', '南京火头军信息技术有限公司', '长期合作伙伴，主营产品：不锈钢厨具等', '陈女士', '13098992113', '江苏省南京市浦口区浦口大道1号新城总部大厦A座903室', '025-86223345', '1', '2013-03-25 16:52:07', null, null);
INSERT INTO `smbms_provider` VALUES ('11', 'GZ_GYS003', '广州市白云区美星五金制品厂', '长期合作伙伴，主营产品：海绵床垫、坐垫、靠垫、海绵枕头、头枕等', '梁天', '13562276775', '广州市白云区钟落潭镇福龙路20号', '020-85542231', '1', '2016-12-21 06:12:17', null, null);
INSERT INTO `smbms_provider` VALUES ('12', 'BJ_GYS004', '北京隆盛日化科技', '长期合作伙伴，主营产品：日化环保清洗剂，家居洗涤专卖、洗涤用品网、墙体除霉剂、墙面霉菌清除剂等', '孙欣', '13689865678', '北京市大兴区旧宫', '010-35576786', '1', '2014-11-21 12:51:11', null, null);
INSERT INTO `smbms_provider` VALUES ('13', 'SD_GYS001', '山东豪克华光联合发展有限公司', '长期合作伙伴，主营产品：洗衣皂、洗衣粉、洗衣液、洗洁精、消杀类、香皂等', '吴洪转', '13245468787', '山东济阳济北工业区仁和街21号', '0531-53362445', '1', '2015-01-28 10:52:07', null, null);


INSERT INTO `smbms_role` VALUES ('1', 'SMBMS_ADMIN', '系统管理员', '1', '2016-04-13 00:00:00', null, null);
INSERT INTO `smbms_role` VALUES ('2', 'SMBMS_MANAGER', '经理', '1', '2016-04-13 00:00:00', null, null);
INSERT INTO `smbms_role` VALUES ('3', 'SMBMS_EMPLOYEE', '普通员工', '1', '2016-04-13 00:00:00', null, null);

INSERT INTO `smbms_user` VALUES ('1', 'wen', '系统管理员', '123', '1', '1997-01-01', '15200981234', '湖南省衡阳市蒸湘区南华大学', '1', '1', '2019-04-07 10:15:55', null, null);
INSERT INTO `smbms_user` VALUES ('5', 'hanlubiao', '韩路彪', '0000000', '2', '1984-06-05', '18567542321', '北京市朝阳区北辰中心12号', '2', '1', '2014-12-31 19:52:09', null, null);
INSERT INTO `smbms_user` VALUES ('6', 'zhanghua', '张华', '0000000', '1', '1983-06-15', '13544561111', '北京市海淀区学院路61号', '3', '1', '2013-02-11 10:51:17', null, null);
INSERT INTO `smbms_user` VALUES ('7', 'wangyang', '王洋', '0000000', '2', '1982-12-31', '13444561124', '北京市海淀区西二旗辉煌国际16层', '3', '1', '2014-06-11 19:09:07', null, null);
INSERT INTO `smbms_user` VALUES ('8', 'zhaoyan', '赵燕', '0000000', '1', '1986-03-07', '18098764545', '北京市海淀区回龙观小区10号楼', '3', '1', '2016-04-21 13:54:07', null, null);
INSERT INTO `smbms_user` VALUES ('10', 'sunlei', '孙磊', '0000000', '2', '1981-01-04', '13387676765', '北京市朝阳区管庄新月小区12楼', '3', '1', '2015-05-06 10:52:07', null, null);
INSERT INTO `smbms_user` VALUES ('11', 'sunxing', '孙兴', '0000000', '2', '1978-03-12', '13367890900', '北京市朝阳区建国门南大街10号', '3', '1', '2016-11-09 16:51:17', null, null);
INSERT INTO `smbms_user` VALUES ('12', 'zhangchen', '张晨', '0000000', '1', '1986-03-28', '18098765434', '朝阳区管庄路口北柏林爱乐三期13号楼', '3', '1', '2016-08-09 05:52:37', '1', '2016-04-14 14:15:36');
INSERT INTO `smbms_user` VALUES ('13', 'dengchao', '邓超', '0000000', '2', '1981-11-04', '13689674534', '北京市海淀区北航家属院10号楼', '3', '1', '2016-07-11 08:02:47', null, null);
INSERT INTO `smbms_user` VALUES ('14', 'yangguo', '杨过', '0000000', '2', '1980-01-01', '13388886623', '北京市朝阳区北苑家园茉莉园20号楼', '3', '1', '2015-02-01 03:52:07', null, null);
INSERT INTO `smbms_user` VALUES ('15', 'test', 'test', '111', '1', '2019-04-16', '123456789', '南华大学', '1', '1', '2019-04-16 19:52:37', null, null);
```

### 1.3、创建项目包结构

![image-20210705095705365](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705095705365.png)

### 1.4、新建实体类

在 pojo下面新建 `Bill`、`Provider`、`Role`、`User`四个实体类

`Bill.java`

```java
package pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LYJ
 * @create 2021-07-05 10:41
 */
public class Bill {
    private int id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private BigDecimal productCount;
    private BigDecimal totalPrice;
    private int isPayment;
    private int createdBy;
    private Date creationDate;
    private int modifyBy;
    private Date modifyDate;
    private int providerId;

    private String providerName; // 供应商名称

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(int isPayment) {
        this.isPayment = isPayment;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```

`Provider.java`

```java
package pojo;

import java.util.Date;

/**
 * @author LYJ
 * @create 2021-07-05 10:50
 */
public class Provider {
    private int id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String proPhone;
    private String proAddress;
    private String proFax;
    private String createdBy;
    private Date creationDate;
    private Date modifyDate;
    private int modifyBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }
}
```

`Role.java`

```java
package pojo;

import java.util.Date;

/**
 * @author LYJ
 * @create 2021-07-05 10:33
 */
public class Role {
    private int id;
    private String roleCode;
    private String roleName;
    private int createdBy;
    private Date creationDate;
    private int modifyBy;
    private Date modifyDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

```

`User.java`

```java
package pojo;

import java.util.Date;

/**
 * @author LYJ
 * @create 2021-07-05 10:15
 */
public class User {
    private int id;
    private String userCode;
    private String userName;
    private String userPassword;
    private int gender;
    private Date birthday;
    private String phone;
    private String address;
    private int userRole;
    private int createdBy;
    private Date creationDate;
    private int modifyBy;
    private Date modifyDate;

    private int age; // 年龄
    private String userRoleName; // 用户角色名称

    public String getUserRoleName(){
        return userRoleName;
    }
    public void setUserRoleName(String userRoleName){
        this.userRoleName = userRoleName;
    }
    public int getAge(){
        Date date = new Date();
        int age = date.getYear() - birthday.getYear();
        return age;
    }

    public User() {
    }

    public User(int id, String userCode, String userName, String userPassword, int gender, Date birthday, String phone, String address, int userRole, int createdBy, Date creationDate, int modifyBy, Date modifyDate) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```

### 1.5、编写基础公共类

#### 1.5.1、数据库配置文件

在resources文件夹下新建 `db.properties` 文件：

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
username=root
password=7012+2
```

#### 1.5.2、操作数据库的公共类

在dao文件夹下新建 `BaseDao` 类，用于操作数据库

```java
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author LYJ
 * @create 2021-07-05 10:57
 * 操作数据库的公共类
 */
public class BaseDao {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 静态代码块。类加载的时候便会初始化
    static {
        Properties properties = new Properties();
        // 通过类加载器获取对应的资源
        InputStream stream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    // 获取数据库的连接
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

         return connection;
    }

    // 编写查询公共类
    public static ResultSet execute(Connection connection, String sql, Object[] params, PreparedStatement statement, ResultSet resultSet) throws SQLException {

        // 预编译的SQL在后面直接执行就可以
        statement = connection.prepareStatement(sql);

        for (int i=0;i<params.length;++i){
            // setObject的占位符从1开始, 但是数组从0开始
            statement.setObject(i+1, params[i]);
        }
        resultSet = statement.executeQuery();
        return resultSet;
    }

    // 编写增删改公共方法
    public static int execute(Connection connection, String sql, Object[] params, PreparedStatement statement) throws SQLException {

        statement = connection.prepareStatement(sql);

        for (int i=0;i<params.length;++i){
            // setObject的占位符从1开始, 但是数组从0开始
            statement.setObject(i+1, params[i]);
        }
        int rows = statement.executeUpdate();
        return rows;
    }

    // 关闭连接
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        boolean flag = true;
        if (resultSet!=null){
            try {
                resultSet.close();
                // GC回收
                resultSet = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if (connection!=null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }
}
```

#### 1.5.3、字符编码过滤器

编写字符过滤器，对字符进行过滤：

```java
package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author LYJ
 * @create 2021-07-05 13:10
 */
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
```

在 `web.xml` 中配置该过滤器：

```xml
    <!--字符编码过滤-->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>filter.CharacterEncodingFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

### 1.6、导入静态资源

![image-20210705140443928](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705140443928.png)

## 2、登录功能

![image-20210705142127724](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/image-20210705142127724.png)

### 2.1、登录功能实现

#### 2.1.1、编写前端登录页面

`login.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!@DOCTYPE html>
<html>
<head lang="en">
    <title>系统登录 - 超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript"></script>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市订单管理系统</h1>
        </header>
        <section class="loginCont">
            <form class="loginForm" action="${pageContext.request.contextPath}/login.do" name="actionForm" method="post">
                <div class="info"></div>
                <div class="inputbox">
                    <label>用户名: </label>
                    <input type="text" class="input-text" id="userCode" name="userCode" placeholder="请输入用户名" required>
                </div>
                <div class="inputbox">
                    <label>密码: </label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required>
                </div>
                <div class="subBtn">
                    <input type="submit" value="登录">
                    <input type="reset" value="重置">
                </div>
            </form>
        </section>
    </section>
</body>
</html>
```

#### 2.2.2、将登录页面设置为首页

`web.xml`

```xml
    <!--欢迎页-->
    <welcome-file-list>
        <welcome-file>login.jsp</welcome-file>
    </welcome-file-list>
```

### 2.2、登录功能优化

#### 2.2.1、注销功能

方法：移除session，返回登录页面；

在 `servlet.user` 下新建 `LogoutServlet.java`

```java
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 移除用户的session
        req.getSession().removeAttribute(Constants.USER_SESSION);
        // 返回登陆页面
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

在 `web.xml` 中注册：

```xml
    <!--注销的Servlet-->
    <servlet>
        <servlet-name>logout</servlet-name>
        <servlet-class>servlet.user.LogoutServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>logout</servlet-name>
        <url-pattern>/jsp/logout.do</url-pattern>
    </servlet-mapping>
```

#### 2.2.2、登陆拦截器

在 `Filter` 文件夹下新建 `SysFilter` 用于登录拦截

```java
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 从Session中获取用户
        User attribute = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (attribute == null){
            // 用户未登录或者注销登录或者已经被移除
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
```

在 `web.xml` 中进行配置

```xml
    <!--注销的Servlet-->
    <servlet>
        <servlet-name>logout</servlet-name>
        <servlet-class>servlet.user.LogoutServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>logout</servlet-name>
        <url-pattern>/jsp/logout.do</url-pattern>
    </servlet-mapping>
```

#### 2.2.3、密码修改

**自顶向下设计，自底向上实现**

先写基本逻辑，前端在测试后端的路上完善的

1. 为了实这个功能需要自底向上逐一实现功能，修改密码需要更新数据库的相关行，所以这就需要dao层去进行增删改查操作数据

2. dao层的需要的当前的一些信息，比如用户名，当前的密码，要修改的密码，所以这些就要dao层去从Service层获取这些参数

3. service需要获取从Servlet层传过来的数据进行相应的处理，验证，核算，然后将最终的信息传递给dao层

4. 而servlet直接与前端接触，返回当前页面上传递而来的用户输入触发的参数，转发到不同的页面，交给不同的service来处理这些请求

5. 意味着先从dao层开始写，分模块，先写接口，再写接口的实现类，依次写service和servlet，最后注册这个servlet，最后测试并完善前端页面

- 在 `dao.user.UserDao` 的接口下增加 `updatepwd` 方法

  - ```java
        /**
         * 修改用户密码
         */
        public int updatePwd(Connection connection, int id, String userPassword) throws SQLException;
    ```

- 在 `UserDaoImpl` 类下实现该方法：

  - ```java
        @Override
        public int updatePwd(Connection connection, int id, String userPassword) throws SQLException {
    
            int rows = 0;
            PreparedStatement statement = null;
            if (connection != null){
                String sql = "update smbms.smbms_user set userPassword = ? where id = ?";
                Object[] params = {userPassword, id};
                rows  = BaseDao.execute(connection, sql, params, statement);
            }
            BaseDao.closeResource(null, statement, null);
            return rows;
        }
    ```

- 在 `UserService` 下新增 `updatePwd` 方法：

  - ```java
        // 根据id修改密码
        public boolean updatePwd(int id, String userPassword);
    ```

- 在 `UserServiceImpl` 类下实现该方法：

  - ```java
    @Override
    public boolean updatePwd(int id, String userPassword) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, userPassword)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }
    ```

- 在 `servlet.user` 下新增 `UserServlet` 类

  - ```java
    // 实现Servlet复用
    public class UserServlet extends HttpServlet {
    
        public UserServlet() {
            super();
        }
    
        @Override
        public void destroy() {
            super.destroy();
        }
    
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doPost(request, response);
        }    
    @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
    
            String method = request.getParameter("method");
    
            System.out.println("method----> " + method);
    
            if(method != null && "savepwd".equals(method)){
                this.updatePwd(request, response);
            }
        }
    private void updatePwd(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        Object o = request.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = request.getParameter("newpassword");
        boolean flag = false;
        if(o != null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)o).getId(),newpassword);
            if(flag){
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                request.getSession().removeAttribute(Constants.USER_SESSION);//session注销
            }else{
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        }else{
            request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        request.getRequestDispatcher("pwdmodify.jsp").forward(request, response);
    }
    ```

- 在 `web.xml` 配置该类：

  - ```xml
        <!--注册UserServlet页面-->
        <servlet>
            <servlet-name>UserServlet</servlet-name>
            <servlet-class>servlet.user.UserServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>UserServlet</servlet-name>
            <url-pattern>/jsp/user.do</url-pattern>
        </servlet-mapping>
    ```



#### 2.2.4、使用Ajax优化密码修改

阿里巴巴的 `fastjson.jar` 包

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.76</version>
</dependency>
```

编写验证旧密码的方法：

```java
public class UserServlet extends HttpServlet {

    public UserServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        System.out.println("method----> " + method);

        if(method != null && "savepwd".equals(method)){
            this.updatePwd(request, response);
        }else if(method != null && method.equals("pwdmodify")) {
            this.getPwdByUserId(request, response);
        }
    }

private void getPwdByUserId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object o = request.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = request.getParameter("oldpassword");
        // 万能的Map
        Map<String, String> resultMap = new HashMap<String, String>();

        if (null == o) {//session失效或者过期
            resultMap.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {//旧密码输入为空
            resultMap.put("result", "error");
        } else {
            String sessionPwd = ((User) o).getUserPassword();

            System.out.println("Old Password: " + oldpassword);
            System.out.println("Session Password:" + sessionPwd);

            if (oldpassword.equals(sessionPwd)) {
                resultMap.put("result", "true");
            } else {//旧密码输入不正确
                resultMap.put("result", "false");
            }
        }

        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        // JSONArray 阿里巴巴的JSON工具类  转换格式
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
```





## 3、用户管理模块实现

结构：

![img](https://gitee.com/yun-xiaojie/blog-image/raw/master/img/cece5afd8c9df1214d995095ba80dd82.png)



### 3.1、获取用户数量

- 首先在 `UserDao` 接口下添加一个方法

  - ```java
        /**
         * 通过条件查询-用户表记录数
         * @param connection
         * @param userName
         * @param userRole
         * @return
         * @throws Exception
         */
        public int getUserCount(Connection connection, String userName, int userRole)throws Exception;
    ```

- 在 `UserDaoImpl` 中实现该方法

  - ```java
    @Override
        //根据用户输入的名字或者角色id来查询计算用户数量
        public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
            int count = 0;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            if (connection != null){
                StringBuilder builder = new StringBuilder();
                builder.append("select count(1) as number\n" +
                        "from smbms_user as u, smbms_role as r\n" +
                        "where u.userRole=r.id");
                ArrayList<Object> list = new ArrayList<Object>();//存放可能会放进sql里的参数，就是用来替代?的params
    
                if (!StringUtils.isNullOrEmpty(userName)){
                    builder.append(" and u.userName like ?");
                    list.add("%" + userName + "%" );
                }
                if (userRole > 0){
                    builder.append(" and u.userRole = ?");
                    list.add(userRole);
                }
                Object[] params = list.toArray();
                System.out.println("当前的SQL命令---->" + builder.toString());
                resultSet = BaseDao.execute(connection, builder.toString(), params, resultSet, statement);
                if (resultSet.next()){
                    count = resultSet.getInt("number");
                }
                BaseDao.closeResource(null, statement, resultSet);
            }
            return count;
        }
    ```

- 在 `UserService` 接口中添加方法

  - ```java
        /**
         * 根据条件查询用户表记录数
         * @param queryUserName
         * @param queryUserRole
         * @return
         */
        public int getUserCount(String queryUserName, int queryUserRole);
    ```

- 在 `UserServiceImpl` 中实现该方法

  - ```java
        @Override
        // 根据用户的输入查询用户记录数
        public int getUserCount(String queryUserName, int queryUserRole) {
            Connection connection = null;
            int count = 0;
    
            try {
                connection = BaseDao.getConnection();
                count  = userDao.getUserCount(connection, queryUserName, queryUserRole);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, null);
            }
            return count;
        }
    ```



### 3.2、根据输入的条件获取当前的用户列表

- 首先在 `UserDao` 接口下添加一个方法

  - ```java
    /**
         * 通过条件查询-userList
         * @param connection
         * @param userName
         * @param userRole
         * @return
         * @throws Exception
         */
        public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;
    ```

- 在 `UserDaoImpl` 中实现该方法

  - ```java
    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null){
            StringBuilder builder = new StringBuilder();
            builder.append("select u.*, r.roleName as userRoleName\n" +
                           "from smbms_user as u, smbms_role as r\n" +
                           "where u.userRole=r.id");
            ArrayList<Object> list = new ArrayList<Object>();//存放可能会放进sql里的参数，就是用来替代?的params
    
            if (!StringUtils.isNullOrEmpty(userName)){
                builder.append(" and u.userName like ?");
                list.add("%" + userName + "%" );
            }
            if (userRole > 0){
                builder.append(" and u.userRole = ?");
                list.add(userRole);
            }
    
            // 实现分页展示
            builder.append(" order by creationDate DESC limit ?, ?");
            list.add(currentPageNo);
            list.add(pageSize);
    
    
            Object[] params = list.toArray();
            System.out.println("当前的SQL命令---->" + builder.toString());
            resultSet = BaseDao.execute(connection, builder.toString(), params, resultSet, statement);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
            }
    
            BaseDao.closeResource(null, statement, resultSet);
        }
        return userList;
    }
    ```

- 在 `UserService` 接口下添加一个方法

  - ```java
    /**
         * 根据条件查询用户列表
         * @param queryUserName
         * @param queryUserRole
         * @return
         */
        public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    
    ```

- 在 `UserServiceImpl` 中实现该方法

  - ```java
    @Override
        public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
            Connection connection = null;
            List<User> userList = new ArrayList<>();
    
            try {
                connection = BaseDao.getConnection();
                userList = userDao.getUserList(connection, queryUserName, queryUserRole, currentPageNo, pageSize);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, null);
            }
            return userList;
        }
    ```



### 3.3、根据输入的条件获取用户的角色列表

- 在 `dao` 文件下新建 `role` 文件夹，新建 `RoleDao` 接口

  - ```java
    public interface RoleDao {
    
        // 获取角色列表
        public List<Role> getRoleList(Connection connection)throws Exception;
    }
    ```

- 新建 `RoleDaoImpl` ，实现 `getRoleList` 方法

  - ```java
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
    ```

- 在 `Service` 文件下新建 `role` 文件夹，新建 `RoleService` 接口

  - ```java
    public interface RoleService {
    
        public List<Role> getRoleList();
    
    }
    ```

- 新建 `RoleServiceImppl` 实现 `getRoleList ` 方法

  - ```java
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
    ```

### 3.4、用户管理query模块的Servlet编写

```java
public class UserServlet extends HttpServlet {
    public UserServlet() {
        super();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("method");

        System.out.println("method----> " + method);

        if(method != null && "savepwd".equals(method)){
            this.updatePwd(request, response);
        }else if(method != null && "pwdmodify".equals(method)) {
            this.getPwdByUserId(request, response);
        } else if (method != null && "query".equals(method)){
            this.query(request, response);
        }    
	public void query(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // 从前端获取数据
        String queryUserName = request.getParameter("queryUserName");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        int queryUserRole = 0;

        // 获取用户列表
        UserService userService = new UserServiceImpl();

        List<User> userList = null;
        // 设置页面容量
        int pageSize = Constants.pageSize;
        // 当前页码
        int currentPageNo = 1;
        System.out.println("queryUserName   UserServlet---->" + queryUserName);
        System.out.println("queryUserRole  UserServlet----->" + queryUserRole);
        System.out.println("pageIndex  UserServlet---->" + pageIndex);

        if (queryUserName == null){
            queryUserName = "";
        }
        if (temp!=null && !("").equals(temp)){
            // 查询的角色
            queryUserRole = Integer.parseInt(temp);
        }
        if (pageIndex != null){
            try {
                currentPageNo = Integer.parseInt(pageIndex);
            } catch (NumberFormatException e){
                response.sendRedirect(request.getContextPath() + "error.jsp");
            }
        }
        // 总数量
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        // 总页数
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();

        // 控制首页和尾页
        if ( currentPageNo < 1){
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }

        // 用户列表
        userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        request.setAttribute("userList", userList);

        System.out.println("=========================================");
        System.out.println("query: " + userList);
        System.out.println("=========================================");

        // 角色列表
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        request.setAttribute("roleList", roleList);
        request.setAttribute("queryUserName", queryUserName);
        request.setAttribute("queryUserRole", queryUserRole);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);

    }
}
```

### 3.5、用户管理模块的增删改查

- 在 `UserDao` 接口中新增相应的方法

  - ```java
        /**
         * 添加用户
         */
        public int add(Connection connection, User user) throws SQLException;
    
        /**
         * 删除用户
         */
        public int deleteUserById(Connection connection, int id) throws SQLException;
    
        //通过userId查看当前用户信息
        public User selectUserById(Connection connection, int id) throws SQLException;
    
        //修改用户信息
        public int modify(Connection connection, User user) throws SQLException;
    ```

- 在 `UserDaoImpl` 中实现这些方法

  - ```java
    // 添加用户
    @Override
    public int add(Connection connection, User user) throws SQLException {
    
        PreparedStatement statement = null;
        int ans = 0;
    
        if (connection != null){
            String sql = "insert into " +
                "smbms_user(userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate)" +
                "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(), user.getGender(),
                               user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserRole(), user.getCreatedBy(),user.getCreationDate()};
            ans =  BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return ans;
    }
    
    // 删除用户
    @Override
    public int deleteUserById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = null;
        int ans = 0;
    
        if (connection != null){
            String sql = "delete from smbms_user where id = ?";
            Object[] params = {id};
            ans = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return ans;
    }
    
    // 查找某个用户
    @Override
    public User selectUserById(Connection connection, int id) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        User user = new User();
        if (connection != null){
            String sql = "select u.*, r.roleName as userRoleName\n" +
                "from smbms_user as u, smbms_role as r\n" +
                "where u.userRole=r.id and u.id = ?";
            Object[] params = {id};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return user;
    }
    
    // 修改用户信息
    @Override
    public int modify(Connection connection, User user) throws SQLException {
    
        int num = 0;
        PreparedStatement statement = null;
    
        if (connection != null){
            String sql = "update smbms_user set userName=?," +
                "gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? " +
                "where id = ? ";
    
            Object[] params = {user.getUserName(), user.getGender(), user.getBirthday(), user.getPhone(),
                               user.getAddress(), user.getUserRole(), user.getModifyBy(), user.getModifyDate(),
                               user.getId()};
    
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
    
        return num;
    }
    ```

- 在 `UserService` 接口中新增相应的方法

  - ```java
        /**
         * 添加用户
         */
        public boolean add(User user);
    
        /**
         * 删除用户
         */
        public boolean deleteUserById(int id);
    
        //通过userId查看当前用户信息
        public User selectUserById(int id);
    
        //修改用户信息
        public boolean modify(User user);
    ```

- 在 `UserServiceImpl` 中实现这些方法

  - ```java
        @Override
        public boolean add(User user) {
    
            boolean flag = false;
            Connection connection = null;
            int rows;
    
            try {
                connection = BaseDao.getConnection();
                connection.setAutoCommit(false); // 关闭事务
                rows = userDao.add(connection, user);
                connection.commit();
    
                if (rows>0){
                    flag = true;
                    System.out.println("UserServiceImpl add---->success");
                } else {
                    System.out.println("UserServiceImpl add---->failed");
                }
    
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                try {
                    System.out.println("rollback==================");
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }finally {
                BaseDao.closeResource(connection, null, null);
            }
            return flag;
        }
    
        @Override
        public boolean deleteUserById(int id) {
            Connection connection = null;
            boolean flag = false;
    
            try {
                connection = BaseDao.getConnection();
                int num = userDao.deleteUserById(connection, id);
                if (num > 0){
                    flag = true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, null);
            }
            return flag;
        }
    
        @Override
        public User selectUserById(int id) {
            Connection connection = null;
            User user = null;
    
            try {
                connection = BaseDao.getConnection();
                user = userDao.selectUserById(connection, id);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                BaseDao.closeResource(connection, null, null);
            }
            return user;
        }
    
        @Override
        public boolean modify(User user) {
            Connection connection = null;
            boolean flag = false;
    
            try {
                connection = BaseDao.getConnection();
                int num = userDao.modify(connection, user);
                if (num > 0){
                    flag = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                BaseDao.closeResource(connection, null, null);
            }
            return flag;
        }
    ```

- 在 `UserServlet` 中编写与前端交互的方法

  - ```java
        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
    
            String method = request.getParameter("method");
    
            System.out.println("method----> " + method);
    
            if(method != null && "savepwd".equals(method)){
                this.updatePwd(request, response);
            }else if(method != null && "pwdmodify".equals(method)) {
                this.getPwdByUserId(request, response);
            } else if (method != null && "query".equals(method)){
                this.query(request, response);
            } else if (method != null && "add".equals(method)){
                this.add(request, response);
            } else if (method != null && "deluser".equals(method)){
                this.deleteUserById(request, response);
            }else if(method != null && method.equals("view")){
                this.selectUserById(request, response, "userview.jsp");
            }else if(method != null && method.equals("modify")){
                this.selectUserById(request, response,"usermodify.jsp");
            }else if(method != null && method.equals("modifyexe")) {
                this.modify(request, response);
            }
        }
    // 添加用户
        public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String userCode = request.getParameter("userCode");
            String userName = request.getParameter("userName");
            String userPassword = request.getParameter("userPassword");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String userRole = request.getParameter("userRole");
    
            User user = new User();
            user.setUserCode(userCode);
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            user.setGender(Integer.parseInt(gender));
            try {
                user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setPhone(phone);
            user.setAddress(address);
            user.setUserRole(Integer.parseInt(userRole));
            user.setUserRole(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
            user.setCreationDate(new Date());
    
            UserService userService = new UserServiceImpl();
            if (userService.add(user)){
                response.sendRedirect(request.getContextPath() + "/jsp/user.do?method=query");
            } else {
                request.getRequestDispatcher("/useradd.jsp").forward(request, response);
            }
        }
    
        public void deleteUserById(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
            String userid = request.getParameter("uid");
            int oldId = 0;
            try {
                oldId = Integer.valueOf(userid);
            } catch (Exception e){
                oldId = 0;
            }
    
            Map<String, String> map = new HashMap<>();
            if (oldId <= 0){
                map.put("delResult", "notexist");
            } else {
                UserService userService = new UserServiceImpl();
                if (userService.deleteUserById(oldId)){
                    map.put("delResult", "true");
                } else {
                    map.put("delResult", "false");
                }
            }
            // 把ResultMap转换成json对象输出
            response.setContentType("application/type");
            PrintWriter writer = response.getWriter();
            writer.write(JSONArray.toJSONString(map));
            writer.flush();
            writer.close();
        }
    
        public void selectUserById(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
            String uid = request.getParameter("uid");
            int id = Integer.parseInt(uid);
    
            UserService userService = new UserServiceImpl();
            User user = userService.selectUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher(url).forward(request, response);
        }
    
    
        public void modify(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String uid = request.getParameter("uid");
            String userName = request.getParameter("userName");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String userRole = request.getParameter("userRole");
    
            User user = new User();
            user.setId(Integer.valueOf(uid));
            user.setUserName(userName);
            user.setGender(Integer.valueOf(gender));
            try {
                user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setPhone(phone);
            user.setAddress(address);
            user.setUserRole(Integer.valueOf(userRole));
            user.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
            user.setModifyDate(new Date());
    
            UserService userService = new UserServiceImpl();
            if (userService.modify(user)){
                response.sendRedirect(request.getContextPath() + "/jsp/user.do?method=query");
            } else {
                request.getRequestDispatcher("usermodify.jsp").forward(request, response);
            }
        }
    ```



## 4、订单管理模块

### 4.1、`BillDao`

```java
public interface BillDao {

    /**
     * 添加订单
     * @param connection
     * @param bill
     * @return
     */
    public int add(Connection connection, Bill bill) throws SQLException;

    /**
     * 通过查询条件获取供应商列表-模糊查询-getBillList
     * @param connection
     * @param bill
     * @return
     */
    public List<Bill> getBillList(Connection connection, Bill bill) throws SQLException;

    /**
     * 删除订单
     * @param connection
     * @param delId
     * @return
     */

    public int deleteBillById(Connection connection, int delId) throws SQLException;

    /**
     * 根据Id查看订单
     */
    public Bill getBillById(Connection connection, int id) throws SQLException;

    /**
     * 修改订单信息
     */
    public int modify(Connection connection, Bill bill) throws SQLException;

    /**
     * 根据providerIdId查询某一家供应商的订单数量
     * @return
     */
    public int getBillCountByProviderById(Connection connection, int providerId) throws SQLException;
}
```

### 4.2、`BillDaoImpl`

```java
public class BillDaoImpl implements BillDao {
    @Override
    public int add(Connection connection, Bill bill) throws SQLException {
        int num = 0;
        PreparedStatement statement = null;

        if (connection != null){
            String sql = "insert into smbms_bill (billCode,productName,productDesc, " +
                    "productUnit,productCount,totalPrice,isPayment,providerId,createdBy,creationDate) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";

            Object[] params = {bill.getBillCode(), bill.getProductName(), bill.getProductDesc(),
            bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(),
            bill.getProviderId(), bill.getCreatedBy(), bill.getCreationDate()};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws SQLException {
        List<Bill> billList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection!=null){
            StringBuilder sql = new StringBuilder();
            sql.append("select b.*, p.proName as providerName\n" +
                    "from smbms_bill as b, smbms_provider as p\n" +
                    "where b.providerId = p.id");
            List<Object> list = new ArrayList<>();

            if (!StringUtils.isNullOrEmpty(bill.getProductName())){
                sql.append(" and b.productName like ?");
                list.add("%" + bill.getProductName() + "%");
            }
            if (bill.getProviderId() > 0){
                sql.append(" and b.providerId = ?");
                list.add(bill.getProviderId());
            }
            if (bill.getIsPayment() > 0){
                sql.append(" and b.isPayment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            resultSet = BaseDao.execute(connection, sql.toString(), params, resultSet, statement);
            while (resultSet.next()){
                Bill bill1 = new Bill();

                bill1.setId(resultSet.getInt("id"));
                bill1.setBillCode(resultSet.getString("billCode"));
                bill1.setProductName(resultSet.getString("productName"));
                bill1.setProductDesc(resultSet.getString("productDesc"));
                bill1.setProductUnit(resultSet.getString("productUnit"));
                bill1.setProductCount(resultSet.getBigDecimal("productCount"));
                bill1.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
                bill1.setIsPayment(resultSet.getInt("isPayment"));
                bill1.setProviderId(resultSet.getInt("providerId"));
                bill1.setProviderName(resultSet.getString("providerName"));
                bill1.setCreationDate(resultSet.getTimestamp("creationDate"));
                bill1.setCreatedBy(resultSet.getInt("createdBy"));

                billList.add(bill1);
            }
            BaseDao.closeResource(null, statement, resultSet);
        }

        return billList;
    }

    @Override
    public int deleteBillById(Connection connection, int delId) throws SQLException {
        PreparedStatement statement = null;
        int num = 0;
        if (connection != null){
            String sql = "delete form smbms_bill where id = ?";
            Object[] params = {delId};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public Bill getBillById(Connection connection, int id) throws SQLException {
        Bill bill = new Bill();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null){
            String sql = "select b.*, p.proName as providerName from smbms_bill as b, smbms_provider as p\n" +
                    "where b.providerId = p.id and b.id = ?";
            Object[] params = {id};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                bill.setId(resultSet.getInt("id"));
                bill.setBillCode(resultSet.getString("billCode"));
                bill.setProductName(resultSet.getString("productName"));
                bill.setProductDesc(resultSet.getString("productDesc"));
                bill.setProductUnit(resultSet.getString("productUnit"));
                bill.setProductCount(resultSet.getBigDecimal("productCount"));
                bill.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
                bill.setIsPayment(resultSet.getInt("isPayment"));
                bill.setProviderId(resultSet.getInt("providerId"));
                bill.setProviderName(resultSet.getString("providerName"));
                bill.setModifyBy(resultSet.getInt("modifyBy"));
                bill.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return bill;
    }

    @Override
    public int modify(Connection connection, Bill bill) throws SQLException {
        int num = 0;
        PreparedStatement statement = null;

        if (connection != null){
            String sql = "update smbms_bill set productName=?," +
                    "productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
                    "isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ?";
            Object[] params = {bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }

    @Override
    public int getBillCountByProviderById(Connection connection, int providerId) throws SQLException {

        ResultSet resultSet = null;
        PreparedStatement statement = null;
        int num = 0;
        if (connection != null){
            String sql = "select COUNT(1) as billCount " +
                    "from smbms_bill where providerId = ?";
            Object[] params = {providerId};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                num = resultSet.getInt("billCount");
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return num;
    }
}

```

### 4.3、`BillService`

```java
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
```

### 4.4、`BillServiceImpl`

```java
public class BillServiceImpl implements BillService {

    BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    @Override
    public boolean add(Bill bill) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (billDao.add(connection, bill) > 0){
                flag = true;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.out.println("BillServiceImpl---->rollback");
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
        Connection connection = null;
        List<Bill> billList = new ArrayList<>();

        try {
            connection = BaseDao.getConnection();
            billList = billDao.getBillList(connection, bill);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return billList;
    }

    @Override
    public boolean deleteBillById(int delId) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            if (billDao.deleteBillById(connection, delId)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public Bill getBillById(int id) {
        Connection connection = null;
        Bill bill = null;

        try {
            connection = BaseDao.getConnection();
            bill = billDao.getBillById(connection, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return bill;
    }

    @Override
    public boolean modify(Bill bill) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            if (billDao.modify(connection, bill) > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

//    @Override
//    public int getBillCountByProviderById(int providerId) {
//        int num = 0;
//        Connection connection = null;
//
//        try {
//            connection = BaseDao.getConnection();
//            num = billDao.getBillCountByProviderById(connection, providerId);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//
//        return num;
//    }
}
```

4.5、`BillServlet`

```java
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
```

## 5、供应商管理模块

### 5.1、`ProviderDao`

```java
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
```

### 5.2、`ProviderDaoImpl`

```java
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
            BaseDao.closeResource(null, statement, null);
        }
        return num;
    }
}
```

### 5.3、`ProviderService`

```java
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
```

### 5.4、`ProviderServiceImpl`

```java
public class ProviderServiceImpl implements ProviderService{

    ProviderDao providerDao;
    BillDao billDao;

    public ProviderServiceImpl() {
        providerDao = new ProviderDaoImpl();
        billDao = new BillDaoImpl();
    }

    @Override
    public List<Provider> getProviderList(String proName, String proCode) {
        List<Provider> providers = new ArrayList<>();
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            providers = providerDao.getProviderList(connection, proName, proCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return providers;
    }

    @Override
    public boolean add(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (providerDao.add(connection, provider)>0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    /**
     * 业务：根据ID删除供应商的数据之前，需要先去订单表进行查询
     * 若订单中无该供应商的订单数据，则可以删除
     * 若有该供应商的订单数据，则不可以删除
     */
    @Override
    public int deleteProviderById(int delId) {
        Connection connection = null;
        int billCount = -1;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            billCount = billDao.getBillCountByProviderById(connection, delId);
            if (billCount == 0){
                providerDao.deleteProviderById(connection, delId);
            }
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            billCount = -1;
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return billCount;
    }

    @Override
    public Provider selectProviderByID(int proId) {
        Provider provider = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            provider = providerDao.selectProviderByID(connection, proId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return provider;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            if (providerDao.modifyProvider(connection, provider) > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }
}
```

### 5.5、`ProviderServlet`

```java
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
```

