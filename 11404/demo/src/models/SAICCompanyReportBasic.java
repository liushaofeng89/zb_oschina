package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-企业基本信息
 * -------------------------------------------------------
 * 企业法人:Legal/Artificial Person
 * 营业执照:B/L:Business License
 * 注册号:Registration No.
 * 成立日期:Establishment Date
 * 登记机关:Registration Institution
 * 法定代表人:Legal Representative/legal person
 * 注册资本:Registered Capital
 * 企业类型:Enterprise Type
 * 经营范围： business scope
 * 营业期限自： business term start
 * 营业期限至： business term end
 * 核准日期：Issue Date
 * 登记状态：registration status
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportBasic {
    @Id
    public ObjectId id;
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;//对应SAICCompany表ID
    /**
     * 企业ID
     */
    public String cid = null;//对应工商ID
    /**
     * 注册号/统一社会信用代码
     */
    @Indexed
    public String rno = null;//对应工商REG_NO
    /**
     * 企业名称
     */
    @Indexed
    public String cn = null;//对应工商CORP_NAME
    /**
     * 企业联系电话
     */
    public String tel = null;//对应工商TEL
    /**
     * 邮政编码
     */
    public String zip = null;//对应工商ZIP
    /**
     * 企业通信地址
     */
    public String addr = null;//对应工商ADDR
    /**
     * 企业电子邮箱
     */
    public String em = null;//对应工商E_MAIL
    /**
     * 有限责任公司本年度是否发生股东股权转让，0-否；1-是；-1-空
     */
    public int eq = -1;//对应工商IF_EQUITY
    /**
     * 企业经营状态
     */
    public String ps = null;//对应工商PRODUCE_STATUS
    /**
     * 是否有网站或网店，0-否；1-是；-1-空
     */
    public int ws = -1;//对应工商IF_WEBSITE
    /**
     * 企业是否有投资信息或购买其他公司股权，0-否；1-是；-1-空
     */
    public int in = -1; //对应工商IF_INVEST
    /**
     * 是否对外担保，0-否；1-是；-1-空
     */
    public int hes = -1;//has external security
    /**
     * 从业人数
     */
    public String ppn;//对应工商PRAC_PERSON_NUM
    /**
     * 隶属关系
     */
    public String msf = null;//membership fun
    /**
     * 报告发布日期（首次公示日期）
     */
    public long rd;//对应工商REPORT_DATE
    /**
     * 最后一次修改日期
     */
    public long lrd;//LAST_MODIFY_REPORT_DATE
    /**
     * 报告年份
     */
    public String ry = null;//对应工商REPORT_YEAR
    /**
     * 资产总额
     */
    public String na = null;//对应工商NET_AMOUNT
    /**
     * 所有者权益合计
     */
    public String te = null;//对应工商TOTAL_EQUITY
    /**
     * 营业总收入
     */
    public String si = null;//对应工商SALE_INCOME
    /**
     * 利润总额
     */
    public String pt = null;//对应工商PROFIT_TOTAL
    /**
     * 营业总收入中主营业务收入
     */
    public String sfi = null;//对应工商SERV_FARE_INCOME
    /**
     * 净利润
     */
    public String pr = null;//对应工商PROFIT_RETA
    /**
     * 纳税总额
     */
    public String tt = null;//对应工商TAX_TOTAL
    /**
     * 负债总额
     */
    public String da = null;//对应工商DEBT_AMOUNT
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//

    public SAICCompanyReportBasic() {

    }
}
