package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业变更信息
 * -------------------------------------------------------
 * 变更事项： Alteration
 * 变更前内容：Before Alteration
 * 变更后内容：Behind Alteration
 * 变更日期：Alteration Date
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyChange {
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
     * 变更事项
     */
    public String alt = null;//，对应工商C1
    /**
     * 变更前内容
     */
    public String bfa = null;//，对应工商C2
    /**
     * 变更后内容
     */
    public String bha = null;//，对应工商C3
    /**
     * 变更日期
     */
    public long adt;//，对应工商C4
    /**
     * 企业ID
     */
    @Indexed
    public String cid = null;//对应工商CORP_ID
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//

    public SAICCompanyChange() {

    }
}
