package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.spider.util.DateTimeUtil;

import org.bson.types.ObjectId;

/**
 * 企业公示信息-股权变更信息 -------------------------------------------------------
 * 股东/发起人类型：Partner type 股东/发起人：Partner 出资方式：Investment type 认缴出资额：SHOULD
 * capital 出资时间：SHOULD capital Date 实缴出资额：REAL capital 实缴时间：REAL capital DATE
 * <p/>
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportStockChange
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
    public String sn = null;//
    /**
     * 变更前股权比例
     */
    public String srb = null;// stock right before bl
    /**
     * 变更后股权比例
     */
    public String sra = null;// stock right after bl
    /**
     * 股权变更日期
     */
    public long cd;// stock right change date
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
    public SAICCompanyReportStockChange()
    {

    }

    /**
     * constructor with parameter
     * @param sn 股东/发起人
     * @param srb 变更前股权比例
     * @param sra 变更后股权比例
     * @param cd 股权变更日期
     */
    public SAICCompanyReportStockChange(String sn, String srb, String sra, String cd)
    {
        this.sn = sn;
        this.srb = srb;
        this.sra = sra;
        this.cd = DateTimeUtil.convert2Long(cd, "yyyy年M月d日");
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

    public String getSrb()
    {
        return srb;
    }

    public void setSrb(String srb)
    {
        this.srb = srb;
    }

    public String getSra()
    {
        return sra;
    }

    public void setSra(String sra)
    {
        this.sra = sra;
    }

    public long getCd()
    {
        return cd;
    }

    public void setCd(long cd)
    {
        this.cd = cd;
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
