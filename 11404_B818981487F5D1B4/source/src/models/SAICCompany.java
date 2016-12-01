package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 企业“登记信息-基本信息” -------------------------------------------------------
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
public class SAICCompany
{
    @Id
    public ObjectId id;
    /**
     * 注册号
     */
    @Indexed
    public String rno = null;// 对应工商C1
    /**
     * 统一社会信用代码
     */
    @Indexed
    public String usc = null;
    /**
     * 组织编码江苏工商
     */
    public String org = null;// 对应工商ORG
    /**
     * 企业名称
     */
    @Indexed
    public String cn = null;// 对应工商C2
    /**
     * 企业曾用名称，多个曾用名中间用“;”分隔
     */
    public String cns = null;//
    /**
     * 企业ID
     */
    public String cid = null;// 对应工商ID
    /**
     * 企业类型
     */
    public String tp = null;// 对应工商C3 有限责任公司(自然人独资)
    /**
     * 组成形式
     */
    public String cf = null;// company form/组成形式
    /**
     * 所属行业
     */
    public String tc = null;// trade Code
    /**
     * 负责人
     */
    public String pri = null;//
    /**
     * 法定代表人/法人/经营者/投资人
     */
    public String lp = null;// 对应工商C5
    /**
     * 企业联系电话
     */
    public String tel = null;// 对应工商TEL
    /**
     * 企业联系手机
     */
    public String mob = null;//
    /**
     * 企业电子邮箱
     */
    public String em = null;// 对应工商E_MAIL
    /**
     * 注册资本RC为registered capital缩写
     */
    public String rc = null;// 对应工商C6
    /**
     * 货币单位：1-人民币；2-港元；3-美元；4-欧元；5-日元；6-韩元；7-英镑；8-新加坡元；9-澳大利亚元；10-加拿大元；11-瑞典克郎；
     * 12-德国马克；
     */
    public int un = 1;// 对应工商CAPI_TYPE_NAME
    /**
     * 成立日期/注册日期
     */
    public String ed = null;// 对应工商C4
    /**
     * 邮政编码
     */
    public String zip = null;//
    /**
     * 行政区划
     */
    public String dc = null;// district Code
    /**
     * 住所/营业场所
     */
    public String add = null;// 对应工商C7
    /**
     * 营业期限自
     */
    public String bts = null; // 对应工商C9
    /**
     * 营业期限至
     */
    public String bte = null; // 对应工商C10
    /**
     * 经营范围
     */
    public String bs = null; // 对应工商C8
    /**
     * 登记机关
     */
    public String ri = null; // 对应工商C11
    /**
     * 核准日期
     */
    public String isd = null; // 对应工商C12
    /**
     * 登记状态：0-注销；1-在业/开业；2-存续（在营、开业、在册）;3-迁出；4-吊销；5-清算；
     */
    public String rs = null; // 对应工商C13
    /**
     * 吊销日期
     */
    public String rd = null;// revoke date
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

    public SAICCompany()
    {

    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public String getRno()
    {
        return rno;
    }

    public void setRno(String rno)
    {
        this.rno = rno;
    }

    public String getOrg()
    {
        return org;
    }

    public void setOrg(String org)
    {
        this.org = org;
    }

    public String getCn()
    {
        return cn;
    }

    public void setCn(String cn)
    {
        this.cn = cn;
    }

    public String getCns()
    {
        return cns;
    }

    public void setCns(String cns)
    {
        this.cns = cns;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public String getTp()
    {
        return tp;
    }

    public void setTp(String tp)
    {
        this.tp = tp;
    }

    public String getCf()
    {
        return cf;
    }

    public void setCf(String cf)
    {
        this.cf = cf;
    }

    public String getTc()
    {
        return tc;
    }

    public void setTc(String tc)
    {
        this.tc = tc;
    }

    public String getPri()
    {
        return pri;
    }

    public void setPri(String pri)
    {
        this.pri = pri;
    }

    public String getLp()
    {
        return lp;
    }

    public void setLp(String lp)
    {
        this.lp = lp;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getMob()
    {
        return mob;
    }

    public void setMob(String mob)
    {
        this.mob = mob;
    }

    public String getEm()
    {
        return em;
    }

    public void setEm(String em)
    {
        this.em = em;
    }

    public String getRc()
    {
        return rc;
    }

    public void setRc(String rc)
    {
        this.rc = rc;
    }

    public int getUn()
    {
        return un;
    }

    public void setUn(int un)
    {
        this.un = un;
    }

    public String getEd()
    {
        return ed;
    }

    public void setEd(String ed)
    {
        this.ed = ed;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getDc()
    {
        return dc;
    }

    public void setDc(String dc)
    {
        this.dc = dc;
    }

    public String getAdd()
    {
        return add;
    }

    public void setAdd(String add)
    {
        this.add = add;
    }

    public String getBts()
    {
        return bts;
    }

    public void setBts(String bts)
    {
        this.bts = bts;
    }

    public String getBte()
    {
        return bte;
    }

    public void setBte(String bte)
    {
        this.bte = bte;
    }

    public String getBs()
    {
        return bs;
    }

    public void setBs(String bs)
    {
        this.bs = bs;
    }

    public String getRi()
    {
        return ri;
    }

    public void setRi(String ri)
    {
        this.ri = ri;
    }

    public String getIsd()
    {
        return isd;
    }

    public void setIsd(String isd)
    {
        this.isd = isd;
    }

    public String getRs()
    {
        return rs;
    }

    public void setRs(String rs)
    {
        this.rs = rs;
    }

    public String getRd()
    {
        return rd;
    }

    public void setRd(String rd)
    {
        this.rd = rd;
    }

    public int getPr()
    {
        return pr;
    }

    public void setPr(int pr)
    {
        this.pr = pr;
    }

    public long getUt()
    {
        return ut;
    }

    public void setUt(long ut)
    {
        this.ut = ut;
    }

    public long getPv()
    {
        return pv;
    }

    public void setPv(long pv)
    {
        this.pv = pv;
    }

    public String getUsc()
    {
        return usc;
    }

    public void setUsc(String usc)
    {
        this.usc = usc;
    }

}
