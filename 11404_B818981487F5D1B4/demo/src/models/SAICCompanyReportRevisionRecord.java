package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-修改记录
 * -------------------------------------------------------
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportRevisionRecord {
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
     * 序号
     */
    public String rn = null;//对应工商RN
    /**
     * 修改事项
     */
    public String cin = null;//对应工商CHANGE_ITEM_NAME
    /**
     * 修改前
     */
    public String oc = null;//对应工商OLD_CONTENT
    /**
     * 修改后
     */
    public String nc = null;//对应工商NEW_CONTENT
    /**
     * 修改日期
     */
    public String cd = null;//对应工商CHANGE_DATE
    /**
     * 报告年份
     */
    public String ry = null;//对应工商REPORT_YEAR
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    public SAICCompanyReportRevisionRecord() {

    }
}
