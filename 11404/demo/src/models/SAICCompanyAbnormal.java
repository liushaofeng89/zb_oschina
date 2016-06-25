package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业经营异常信息
 * -------------------------------------------------------
 * 序号：reference number
 * 列入经营异常名录原因：Included in the list of unusual business reasons
 * 列入日期：On the date
 * 移出经营异常名录原因：Out of business exception list reasons
 * 移出日期：Out of date
 * 作出决定机关：The decision authority
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyAbnormal {
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
    public int rn;//对应工商RN
    /**
     * 列入经营异常名录原因
     */
    public String inr = null;//对应工商C1
    /**
     * 列入日期
     */
    public String ond = null;//对应工商C2
    /**
     * 移出经营异常名录原因
     */
    public String our = null;//对应工商C3
    /**
     * 移出日期
     */
    public String oud = null;//对应工商C4
    /**
     * 作出决定机关
     */
    public String dec = null;//对应工商C5
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

    public SAICCompanyAbnormal() {

    }
}
