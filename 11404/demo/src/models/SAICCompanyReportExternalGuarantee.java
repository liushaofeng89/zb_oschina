package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-对外担保
 * -------------------------------------------------------
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportExternalGuarantee {
    @Id
    public ObjectId id;
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;//对应工商CORP_ID
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;//对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    @Indexed
    public String rno = null;//
    /**
     * 债权人
     */
    public String cr = null;//Creditors
    /**
     * 债务人
     */
    public String ob = null;//obligor
    /**
     * 主债权种类
     */
    public String mcr = null;//Types of main creditor's rights
    /**
     * 主债权数额
     */
    public String acr = null;//Amount of the principal creditor's rights
    /**
     * 履行债务的期限
     */
    public String ppd = null;//The period of performance of a debt
    /**
     * 保证的期间
     */
    public String gp = null;//Guaranteed period
    /**
     * 保证的方式
     */
    public String gw = null;//Guaranteed way
    /**
     * 保证担保的范围
     */
    public String gs = null;//Guarantee scope
    /**
     * 报告年份
     */
    public String ry = null;//对应工商REPORT_YEAR
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    public SAICCompanyReportExternalGuarantee() {

    }
}
