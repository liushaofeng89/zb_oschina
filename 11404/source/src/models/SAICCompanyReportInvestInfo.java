package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-对外投资信息 -------------------------------------------------------
 * 股东/发起人类型：Partner type 股东/发起人：Partner 出资方式：Investment type 认缴出资额：SHOULD
 * capital 出资时间：SHOULD capital Date 实缴出资额：REAL capital 实缴时间：REAL capital DATE
 * <p/>
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportInvestInfo
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
     * 投资设立企业或购买股权企业名称
     */
    public String in = null;// 对应工商INVEST_NAME
    /**
     * 注册号/统一社会信用代码
     */
    public String irn = null;// 对应工商INVEST_REG_NO
    /**
     * 序号
     */
    public String rn = null;// 对应工商RN
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
    public SAICCompanyReportInvestInfo()
    {

    }

    /**
     * default constructor with parameter
     * @param in 投资设立企业或购买股权企业名称
     * @param irn 注册号/统一社会信用代码
     */
    public SAICCompanyReportInvestInfo(String in, String irn)
    {
        this.in = in;
        this.irn = irn;
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

    public String getIn()
    {
        return in;
    }

    public void setIn(String in)
    {
        this.in = in;
    }

    public String getIrn()
    {
        return irn;
    }

    public void setIrn(String irn)
    {
        this.irn = irn;
    }

    public String getRn()
    {
        return rn;
    }

    public void setRn(String rn)
    {
        this.rn = rn;
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
