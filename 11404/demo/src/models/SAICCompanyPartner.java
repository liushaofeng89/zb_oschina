package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业合伙人信息
 * -------------------------------------------------------
 * 股东/发起人类型：Partner type
 * 股东/发起人：Partner
 * 证照/证件类型：Document type
 * 证照/证件号码：Document No
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyPartner {
    @Id
    public ObjectId id;
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId = null;//对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    public String rno = null;//
    /**
     * 股东/发起人类型
     */
    public String ptt = null;//对应工商C1
    /**
     * 股东/发起人
     */
    public String pt = null;//对应工商C2
    /**
     * 证照/证件类型
     */
    public String dt = null;//对应工商C3
    /**
     * 证照/证件号码
     */
    public String dn = null;//对应工商C4
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;//对应工商CORP_ID
    /**
     * 自身记录ID，用于关联股东详情(SAICCompanyPartnerInvest.gid)
     */
    @Indexed
    public String gid = null;//对应工商C6

    @Indexed
    public long ut = System.currentTimeMillis();//创建/更新时间

    public SAICCompanyPartner() {

    }
}
