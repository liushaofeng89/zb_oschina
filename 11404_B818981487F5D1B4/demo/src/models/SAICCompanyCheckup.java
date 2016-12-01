package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 工商公示信息-抽查检查信息
 * -------------------------------------------------------
 * 检查实施机关：Check the implementation organ
 * 抽查：selection check
 * -------------------------------------------------------
 */
@Entity
public class SAICCompanyCheckup {
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
     * 检查实施机关
     */
    public String cio = null;//对应工商C1
    /**
     * 类型
     */
    public String ty = null;//对应工商C2
    /**
     * 日期
     */
    public String dt = null;//对应工商C3
    /**
     * 结果
     */
    public String rs = null;//对应工商C4
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

    public SAICCompanyCheckup() {

    }
}
