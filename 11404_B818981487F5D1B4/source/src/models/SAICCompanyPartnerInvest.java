package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业合伙人出资信息 -------------------------------------------------------
 * 股东/发起人类型：Partner type 股东/发起人：Partner 出资方式：Investment type 认缴出资额：SHOULD
 * capital 出资时间：SHOULD capital Date 实缴出资额：REAL capital 实缴时间：REAL capital DATE
 * <p/>
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyPartnerInvest
{
    @Id
    public ObjectId id;
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;// 对应工商CORP_ID
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;// 对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    @Indexed
    public String rno = null;//
    /**
     * 投资人类型
     */
    public String st = null;// 对应工商listValue.STOCK_TYPE
    /**
     * 投资人名称
     */
    public String sn = null;// 对应工商listValue.STOCK_NAME
    /**
     * 认缴出资方式
     */
    public String sit = null;// 对应工商listchuzi.SHOULD_INVEST_TYPE_NAME
    /**
     * 认缴出资额
     */
    public String sc = null;// 对应工商listrenjiao.SHOULD_CAPI
    /**
     * 认缴出资时间
     */
    public String scd = null;// 对应工商listrenjiao.SHOULD_CAPI_DATE
    /**
     * 实缴出资方式
     */
    public String it = null;// 对应工商listchuzi.INVEST_TYPE_NAME
    /**
     * 实缴出资额
     */
    public String rc = null;// 对应工商listshijiao.REAL_CAPI
    /**
     * 实缴出资时间
     */
    public String rcd = null;// 对应工商listshijiao.REAL_CAPI_DATE
    /**
     * 自身记录ID，用于关联股东详情
     */
    @Indexed
    public String gid = null;// 对应工商listValue.ID

    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    /**
     * default constructor
     */
    public SAICCompanyPartnerInvest()
    {

    }

    /**
     * constructor with parameter
     * @param sn 投资人名称
     * @param sit 认缴出资方式
     * @param sc 认缴出资额
     * @param scd 认缴出资时间
     * @param it 实缴出资方式
     * @param rc 实缴出资额
     * @param rcd 实缴出资时间
     */
    public SAICCompanyPartnerInvest(String sn, String sit, String sc, String scd, String it, String rc,
        String rcd)
    {
        this.sn = sn;
        this.sit = sit;
        this.sc = sc;
        this.scd = scd;
        this.rc = rc;
        this.it = it;
        this.rcd = rcd;
    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public String getsId()
    {
        return sId;
    }

    public void setsId(String sId)
    {
        this.sId = sId;
    }

    public String getRno()
    {
        return rno;
    }

    public void setRno(String rno)
    {
        this.rno = rno;
    }

    public String getSt()
    {
        return st;
    }

    public void setSt(String st)
    {
        this.st = st;
    }

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public String getSit()
    {
        return sit;
    }

    public void setSit(String sit)
    {
        this.sit = sit;
    }

    public String getSc()
    {
        return sc;
    }

    public void setSc(String sc)
    {
        this.sc = sc;
    }

    public String getScd()
    {
        return scd;
    }

    public void setScd(String scd)
    {
        this.scd = scd;
    }

    public String getIt()
    {
        return it;
    }

    public void setIt(String it)
    {
        this.it = it;
    }

    public String getRc()
    {
        return rc;
    }

    public void setRc(String rc)
    {
        this.rc = rc;
    }

    public String getRcd()
    {
        return rcd;
    }

    public void setRcd(String rcd)
    {
        this.rcd = rcd;
    }

    public String getGid()
    {
        return gid;
    }

    public void setGid(String gid)
    {
        this.gid = gid;
    }

    public long getUt()
    {
        return ut;
    }

    public void setUt(long ut)
    {
        this.ut = ut;
    }

}
