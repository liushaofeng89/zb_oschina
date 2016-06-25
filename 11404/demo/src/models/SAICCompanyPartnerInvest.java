package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业合伙人出资信息
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
public class SAICCompanyPartnerInvest {
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
     * 投资人类型
     */
    public String st = null;//对应工商listValue.STOCK_TYPE
    /**
     * 投资人名称
     */
    public String sn = null;//对应工商listValue.STOCK_NAME
    /**
     * 出资方式
     */
    public String it = null;//对应工商listchuzi.INVEST_TYPE_NAME
    /**
     * 认缴出资额
     */
    public String sc = null;//对应工商listrenjiao.SHOULD_CAPI
    /**
     * 出资时间
     */
    public String scd = null;//对应工商listrenjiao.SHOULD_CAPI_DATE
    /**
     * 实缴出资额
     */
    public String rc = null;//对应工商listshijiao.REAL_CAPI
    /**
     * 实缴时间
     */
    public String rcd = null;//对应工商listshijiao.REAL_CAPI_DATE
    /**
     * 自身记录ID，用于关联股东详情
     */
    @Indexed
    public String gid = null;//对应工商listValue.ID

    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    public SAICCompanyPartnerInvest() {

    }
}
