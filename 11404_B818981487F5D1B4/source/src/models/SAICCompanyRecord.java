package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业备案信息-主要人员信息 -------------------------------------------------------
 * 序号：reference number 
 * 姓名：personal name 
 * 职务：job
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyRecord
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
    public int rn;// 对应工商VALUES{n}RN
    /**
     * 姓名
     */
    public String pn = null;// 对应工商PERSON_NAME{n}
    /**
     * 职务
     */
    public String job = null;// 对应工商POSITION_NAME{n}
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
    public SAICCompanyRecord()
    {

    }

    /**
     * constructor with parameter
     * @param rn 序号
     * @param pn 姓名
     * @param job 职务
     */
    public SAICCompanyRecord(String rn, String pn, String job)
    {
        this.rn = Integer.parseInt(rn.trim());
        this.pn = pn;
        this.job = job;
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

    public String getPn()
    {
        return pn;
    }

    public void setPn(String pn)
    {
        this.pn = pn;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
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
