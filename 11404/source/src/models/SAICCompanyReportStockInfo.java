package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-股东（发起人）及出资信息
 * ------------------------------------------------------- 股东/发起人类型：Partner type
 * 股东/发起人：Partner 出资方式：Investment type 认缴出资额：SHOULD capital 出资时间：SHOULD capital
 * Date 实缴出资额：REAL capital 实缴时间：REAL capital DATE
 * <p/>
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportStockInfo
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
     * 股东/发起人
     */
    public String sn = null;// 对应工商STOCK_NAME
    /**
     * 认缴出资额(万元)
     */
    public String sc = null;// 对应工商SHOULD_CAPI
    /**
     * 认缴出资时间
     */
    public String scd = null;// 对应工商SHOULD_CAPI_DATE
    /**
     * 认缴出资方式
     */
    public String sct = null;// 对应工商SHOULD_CAPI_TYPE_NAME
    /**
     * 实缴出资额(万元)
     */
    public String rc = null;// 对应工商REAL_CAPI
    /**
     * 实缴出资时间
     */
    public String rcd = null;// 对应工商REAL_CAPI_DATE
    /**
     * 实缴出资方式
     */
    public String rct = null;// 对应工商REAL_CAPI_TYPE_NAME
    /**
     * 报告年份
     */
    public String ry = null;// 对应工商REPORT_YEAR
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    /**
     * default constructor
     */
    public SAICCompanyReportStockInfo()
    {

    }

    /**
     * @param sn 股东/发起人
     * @param sc 认缴出资额(万元)
     * @param scd 认缴出资时间
     * @param sct 认缴出资方式
     * @param rc 实缴出资额(万元)
     * @param rcd 实缴出资时间
     * @param rct 实缴出资方式
     */
    public SAICCompanyReportStockInfo(String sn, String sc, String scd, String sct, String rc, String rcd,
        String rct)
    {
        this.sn = sn;
        this.sc = sc;
        this.scd = scd;
        this.sct = sct;
        this.rc = rc;
        this.rcd = rcd;
        this.rct = rct;
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

    public String getSn()
    {
        return sn;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
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

    public String getSct()
    {
        return sct;
    }

    public void setSct(String sct)
    {
        this.sct = sct;
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

    public String getRct()
    {
        return rct;
    }

    public void setRct(String rct)
    {
        this.rct = rct;
    }

    public String getRy()
    {
        return ry;
    }

    public void setRy(String ry)
    {
        this.ry = ry;
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
