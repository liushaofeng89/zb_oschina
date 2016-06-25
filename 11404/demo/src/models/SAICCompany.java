package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业“登记信息-基本信息”
 * -------------------------------------------------------
 * 企业法人:Legal/Artificial Person
 * 营业执照:B/L:Business License
 * 注册号:Registration No.
 * 成立日期:Establishment Date
 * 登记机关:Registration Institution
 * 法定代表人:Legal Representative/legal person
 * 注册资本:Registered Capital
 * 企业类型:Enterprise Type
 * 经营范围： business scope
 * 营业期限自： business term start
 * 营业期限至： business term end
 * 核准日期：Issue Date
 * 登记状态：registration status
 * -------------------------------------------------------
 */
@Entity
public class SAICCompany {
    @Id
    public ObjectId id;
    /**
     * 注册号
     */
    @Indexed
    public String rno = null;//对应工商C1
    /**
     * 组织编码江苏工商
     */
    public String org = null;//对应工商ORG
    /**
     * 企业名称
     */
    @Indexed
    public String cn = null;//对应工商C2
    /**
     * 企业曾用名称，多个曾用名中间用“;”分隔
     */
    public String cns = null;//
    /**
     * 企业ID
     */
    public String cid = null;//对应工商ID
    /**
     * 企业类型
     */
    public String tp = null;//对应工商C3 有限责任公司(自然人独资)
    /**
     * 组成形式
     */
    public String cf = null;//company form/组成形式
    /**
     * 所属行业
     */
    public String tc = null;//trade Code
    /**
     * 负责人
     */
    public String pri = null;//
    /**
     * 法定代表人/法人/经营者
     */
    public String lp = null;//对应工商C5
    /**
     * 企业联系电话
     */
    public String tel = null;//对应工商TEL
    /**
     * 企业联系手机
     */
    public String mob = null;//
    /**
     * 企业电子邮箱
     */
    public String em = null;//对应工商E_MAIL
    /**
     * 注册资本RC为registered capital缩写
     */
    public String rc = null;//对应工商C6
    /**
     * 货币单位：1-人民币；2-港元；3-美元；4-欧元；5-日元；6-韩元；7-英镑；8-新加坡元；9-澳大利亚元；10-加拿大元；11-瑞典克郎；12-德国马克；
     */
    public int un = 1;//对应工商CAPI_TYPE_NAME
    /**
     * 成立日期/注册日期
     */
    public String ed = null;//对应工商C4
    /**
     * 邮政编码
     */
    public String zip = null;//
    /**
     * 行政区划
     */
    public String dc = null;//district Code
    /**
     * 住所/营业场所
     */
    public String add = null;//对应工商C7
    /**
     * 营业期限自
     */
    public String bts = null; //对应工商C9
    /**
     * 营业期限至
     */
    public String bte = null; //对应工商C10
    /**
     * 经营范围
     */
    public String bs = null; //对应工商C8
    /**
     * 登记机关
     */
    public String ri = null; //对应工商C11
    /**
     * 核准日期
     */
    public String isd = null; //对应工商C12
    /**
     * 登记状态：0-注销；1-在业/开业；2-存续（在营、开业、在册）;3-迁出；4-吊销；5-清算；
     */
    public String rs = null; //对应工商C13
    /**
     * 吊销日期
     */
    public String rd = null;//revoke date
    /**
     * 所属省份
     */
    public int pr;//
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//
    /**
     * 浏览次数
     */
    public long pv = 0;

    public SAICCompany() {

    }
}
