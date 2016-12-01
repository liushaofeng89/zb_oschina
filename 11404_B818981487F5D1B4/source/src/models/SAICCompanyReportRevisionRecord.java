package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业公示信息-企业年报-修改记录 -------------------------------------------------------
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyReportRevisionRecord
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
     * 序号
     */
    public String rn = null;// 对应工商RN
    /**
     * 修改事项
     */
    public String cin = null;// 对应工商CHANGE_ITEM_NAME
    /**
     * 修改前
     */
    public String oc = null;// 对应工商OLD_CONTENT
    /**
     * 修改后
     */
    public String nc = null;// 对应工商NEW_CONTENT
    /**
     * 修改日期
     */
    public String cd = null;// 对应工商CHANGE_DATE
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
    public SAICCompanyReportRevisionRecord()
    {

    }

    /**
     * constructor with parameter
     * @param rn 序号
     * @param cin 修改事项
     * @param oc 修改前
     * @param nc 修改后
     * @param cd 修改日期
     */
    public SAICCompanyReportRevisionRecord(String rn, String cin, String oc, String nc, String cd)
    {
        this.rn = rn;
        this.cin = cin;
        this.oc = oc;
        this.nc = nc;
        this.cd = cd;
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

    public String getRn()
    {
        return rn;
    }

    public void setRn(String rn)
    {
        this.rn = rn;
    }

    public String getCin()
    {
        return cin;
    }

    public void setCin(String cin)
    {
        this.cin = cin;
    }

    public String getOc()
    {
        return oc;
    }

    public void setOc(String oc)
    {
        this.oc = oc;
    }

    public String getNc()
    {
        return nc;
    }

    public void setNc(String nc)
    {
        this.nc = nc;
    }

    public String getCd()
    {
        return cd;
    }

    public void setCd(String cd)
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
