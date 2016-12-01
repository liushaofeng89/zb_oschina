package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-网站或网店信息
 * -------------------------------------------------------
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportWebSite {
    @Id
    public ObjectId id;
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;//对应工商ID
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;//对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    @Indexed
    public String rno = null;//对应工商REG_NO
    /**
     * 类型
     */
    public String wt = null;//对应工商WEB_TYPE
    /**
     * 名称
     */
    public String wn = null;//对应工商WEB_NAME
    /**
     * 网址
     */
    public String url = null;//WEB_URL
    /**
     * 报告年份
     */
    public String ry = null;//对应工商REPORT_YEAR
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//

    public SAICCompanyReportWebSite() {

    }
}
