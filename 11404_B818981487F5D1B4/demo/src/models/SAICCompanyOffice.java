package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业分支机构信息
 * -------------------------------------------------------
 * 序号：reference number
 * 名称：personal name
 * 职务：job
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyOffice {
    @Id
    public ObjectId id;
    /**
     * 系统内部企业编码
     */
    @Indexed
    public String sId;//对应SAICCompany表ID
    /**
     * 序号
     */
    public int rn;//对应工商RN
    /**
     * 注册号/统一社会信用代码
     */
    public String rno = null;//对应工商C1
    /**
     * 名称
     */
    public String cn = null;//对应工商C2
    /**
     * 登记机关
     */
    public String ri = null;//对应工商C3
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

    public SAICCompanyOffice() {

    }
}
