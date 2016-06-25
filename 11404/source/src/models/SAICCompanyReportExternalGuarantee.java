package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-对外担保 -------------------------------------------------------
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportExternalGuarantee
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
     * 债权人
     */
    public String cr = null;// Creditors
    /**
     * 债务人
     */
    public String ob = null;// obligor
    /**
     * 主债权种类
     */
    public String mcr = null;// Types of main creditor's rights
    /**
     * 主债权数额
     */
    public String acr = null;// Amount of the principal creditor's rights
    /**
     * 履行债务的期限
     */
    public String ppd = null;// The period of performance of a debt
    /**
     * 保证的期间
     */
    public String gp = null;// Guaranteed period
    /**
     * 保证的方式
     */
    public String gw = null;// Guaranteed way
    /**
     * 保证担保的范围
     */
    public String gs = null;// Guarantee scope
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
     * default construtor
     */
    public SAICCompanyReportExternalGuarantee()
    {

    }

    /**
     * constructor with parameter
     * @param cr 债权人
     * @param ob 债务人
     * @param mcr 主债权种类
     * @param acr 主债权数额
     * @param ppd 履行债务的期限
     * @param gp 保证的期间
     * @param gw 保证的方式
     */
    public SAICCompanyReportExternalGuarantee(String cr, String ob, String mcr, String acr, String ppd,
        String gp, String gw)
    {
        this.cr = cr;
        this.ob = ob;
        this.mcr = mcr;
        this.acr = acr;
        this.ppd = ppd;
        this.gp = gp;
        this.gw = gw;
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

    public String getCr()
    {
        return cr;
    }

    public void setCr(String cr)
    {
        this.cr = cr;
    }

    public String getOb()
    {
        return ob;
    }

    public void setOb(String ob)
    {
        this.ob = ob;
    }

    public String getMcr()
    {
        return mcr;
    }

    public void setMcr(String mcr)
    {
        this.mcr = mcr;
    }

    public String getAcr()
    {
        return acr;
    }

    public void setAcr(String acr)
    {
        this.acr = acr;
    }

    public String getPpd()
    {
        return ppd;
    }

    public void setPpd(String ppd)
    {
        this.ppd = ppd;
    }

    public String getGp()
    {
        return gp;
    }

    public void setGp(String gp)
    {
        this.gp = gp;
    }

    public String getGw()
    {
        return gw;
    }

    public void setGw(String gw)
    {
        this.gw = gw;
    }

    public String getGs()
    {
        return gs;
    }

    public void setGs(String gs)
    {
        this.gs = gs;
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
