package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 工商公示信息-抽查检查信息 -------------------------------------------------------
 * 检查实施机关：Check the implementation organ 抽查：selection check
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyCheckup
{
    @Id
    public ObjectId id;
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;// 对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    public String rno = null;//
    /**
     * 序号
     */
    public int rn;// 对应工商RN
    /**
     * 检查实施机关
     */
    public String cio = null;// 对应工商C1
    /**
     * 类型
     */
    public String ty = null;// 对应工商C2
    /**
     * 日期
     */
    public String dt = null;// 对应工商C3
    /**
     * 结果
     */
    public String rs = null;// 对应工商C4
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;// 对应工商CORP_ID
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    /**
     * default constructor
     */
    public SAICCompanyCheckup()
    {

    }

    /**
     * constructor with parameter
     * @param rn 序号
     * @param cio 检查实施机关
     * @param ty 类型
     * @param dt 日期
     * @param rs 结果
     */
    public SAICCompanyCheckup(String rn, String cio, String ty, String dt, String rs)
    {
        this.cio = cio;
        this.ty = ty;
        this.dt = dt;
        this.rs = rs;
        this.rn = Integer.parseInt(rn);
    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
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

    public int getRn()
    {
        return rn;
    }

    public void setRn(int rn)
    {
        this.rn = rn;
    }

    public String getCio()
    {
        return cio;
    }

    public void setCio(String cio)
    {
        this.cio = cio;
    }

    public String getTy()
    {
        return ty;
    }

    public void setTy(String ty)
    {
        this.ty = ty;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }

    public String getRs()
    {
        return rs;
    }

    public void setRs(String rs)
    {
        this.rs = rs;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
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
