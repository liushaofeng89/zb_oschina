package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;

/**
 * 企业变更信息 ------------------------------------------------------- 
 * 变更事项： Alteration 
 * 变更前内容：Before Alteration 
 * 变更后内容：Behind Alteration 
 * 变更日期：Alteration
 * Date -------------------------------------------------------
 */
@Entity
public class SAICCompanyChange
{
    /** 日志 */
    private static Logger log = Logger.getLogger(SAICCompanyChange.class);

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
     * 变更事项
     */
    public String alt = null;// ，对应工商C1
    /**
     * 变更前内容
     */
    public String bfa = null;// ，对应工商C2
    /**
     * 变更后内容
     */
    public String bha = null;// ，对应工商C3
    /**
     * 变更日期
     */
    public long adt;// ，对应工商C4
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
    public SAICCompanyChange()
    {

    }

    /**
     * constructor with parameter
     * @param alt 变更事项
     * @param bfa 变更前内容
     * @param bha 变更后内容
     * @param adt 变更日期
     */
    public SAICCompanyChange(String alt, String bfa, String bha, String adt)
    {
        this.alt = alt;
        this.bfa = bfa;
        this.bha = bha;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年m月d日");
            Date parse = sdf.parse(adt);
            this.adt = parse.getTime();
        } catch (ParseException e)
        {
            log.error("变更时间处理失败：" + adt, e);
            this.adt = 0;
        }
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

    public String getAlt()
    {
        return alt;
    }

    public void setAlt(String alt)
    {
        this.alt = alt;
    }

    public String getBfa()
    {
        return bfa;
    }

    public void setBfa(String bfa)
    {
        this.bfa = bfa;
    }

    public String getBha()
    {
        return bha;
    }

    public void setBha(String bha)
    {
        this.bha = bha;
    }

    public long getAdt()
    {
        return adt;
    }

    public void setAdt(long adt)
    {
        this.adt = adt;
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
