package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业分支机构信息 ------------------------------------------------------- 序号：reference
 * number 名称：personal name 职务：job
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyOffice
{
    @Id
    public ObjectId id;
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;// 对应SAICCompany表ID
    /**
     * 序号
     */
    public int rn;// 对应工商RN
    /**
     * 注册号/统一社会信用代码
     */
    public String rno = null;// 对应工商C1
    /**
     * 名称
     */
    public String cn = null;// 对应工商C2
    /**
     * 登记机关
     */
    public String ri = null;// 对应工商C3
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;// 对应工商CORP_ID
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//

    /**
     * default constructor
     */
    public SAICCompanyOffice()
    {

    }

    /**
     * constructor with parameter
     * @param rn 序号
     * @param rno 注册号/统一社会信用代码
     * @param cn 名称
     * @param ri 登记机关
     */
    public SAICCompanyOffice(String rn, String rno, String cn, String ri)
    {
        this.rno = rno;
        this.cn = cn;
        this.ri = ri;
        this.rn = Integer.parseInt(rn.trim());
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

    public int getRn()
    {
        return rn;
    }

    public void setRn(int rn)
    {
        this.rn = rn;
    }

    public String getRno()
    {
        return rno;
    }

    public void setRno(String rno)
    {
        this.rno = rno;
    }

    public String getCn()
    {
        return cn;
    }

    public void setCn(String cn)
    {
        this.cn = cn;
    }

    public String getRi()
    {
        return ri;
    }

    public void setRi(String ri)
    {
        this.ri = ri;
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
