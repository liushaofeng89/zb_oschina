package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-股权变更信息
 * -------------------------------------------------------
 * 股东/发起人类型：Partner type
 * 股东/发起人：Partner
 * 出资方式：Investment type
 * 认缴出资额：SHOULD capital
 * 出资时间：SHOULD capital Date
 * 实缴出资额：REAL capital
 * 实缴时间：REAL capital DATE
 * <p/>
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportStockChange {
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
     * 股东/发起人
     */
    public String sn = null;//
    /**
     * 变更前股权比例
     */
    public String srb = null;//stock right before bl
    /**
     * 变更后股权比例
     */
    public String sra = null;//stock right after bl
    /**
     * 股权变更日期
     */
    public long cd;//stock right change date
    /**
     * 报告年份
     */
    public String ry = null;//对应工商REPORT_YEAR
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    public SAICCompanyReportStockChange() {

    }
}
