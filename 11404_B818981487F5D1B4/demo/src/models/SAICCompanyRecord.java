package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业备案信息-主要人员信息
 * -------------------------------------------------------
 * 序号：reference number
 * 姓名：personal name
 * 职务：job
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyRecord {
    @Id
    public ObjectId id;
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;//对应SAICCompany表ID
    /**
     * 注册号/统一社会信用代码
     */
    public String rno = null;//
    /**
     * 序号
     */
    public int rn;//对应工商VALUES{n}RN
    /**
     * 姓名
     */
    public String pn = null;//对应工商PERSON_NAME{n}
    /**
     * 职务
     */
    public String job = null;//对应工商POSITION_NAME{n}
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;//对应工商CORP_ID
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();

    public SAICCompanyRecord() {

    }
}
