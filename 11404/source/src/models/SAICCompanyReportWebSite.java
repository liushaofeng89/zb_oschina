package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-网站或网店信息 -------------------------------------------------------
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportWebSite
{
    @Id
    public ObjectId id;
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;// 对应工商ID
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;// 对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    @Indexed
    public String rno = null;// 对应工商REG_NO
    /**
     * 类型
     */
    public String wt = null;// 对应工商WEB_TYPE
    /**
     * 名称
     */
    public String wn = null;// 对应工商WEB_NAME
    /**
     * 网址
     */
    public String url = null;// WEB_URL
    /**
     * 报告年份
     */
    public String ry = null;// 对应工商REPORT_YEAR
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//

    /**
     * default constructor
     */
    public SAICCompanyReportWebSite()
    {

    }

    /**
     * default constructor with parameter
     * @param wt 类型
     * @param wn 名称
     * @param url 网址
     */
    public SAICCompanyReportWebSite(String wt, String wn, String url)
    {
        this.wt = wt;
        this.wn = wn;
        this.url = url;
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

    public String getWt()
    {
        return wt;
    }

    public void setWt(String wt)
    {
        this.wt = wt;
    }

    public String getWn()
    {
        return wn;
    }

    public void setWn(String wn)
    {
        this.wn = wn;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
