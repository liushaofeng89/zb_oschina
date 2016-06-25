package com.spoder.main;

/**
 * KEY
 * @author liushaofeng
 * @date 2016-6-2 下午10:57:07
 * @version 1.0.0
 */
public interface IDataInfoKey
{
    /********************************** 基本信息开始 ******************************************/
    /** 注册号/统一社会信用代码 */
    public static final String REGISTER_CODE = "注册号/统一社会信用代码";
    /** 名称 */
    public static final String COMPANY_NAME = "名称";
    /** 类型 */
    public static final String COMPANY_TYPE = "类型";
    /** 登记机关 */
    public static final String REGISTRATION_AUTHORITY = "登记机关";
    /** 核准日期 */
    public static final String APPROVED_DATE = "核准日期";
    /** 住所 */
    public static final String RESIDENCE = "住所";
    /** 营业期限自 */
    public static final String BUSINESS_START_TIME = "营业期限自";
    /** 法定代表人 */
    public static final String LEGAL_REPRESENTATIVE = "法定代表人";
    /** 成立日期 */
    public static final String COMPANY_CREATE_TIME = "成立日期";
    /** 登记状态 */
    public static final String REGISTRATION_STATUS = "登记状态";
    /** 注册资本 */
    public static final String REGISTERED_CAPITAL = "注册资本";
    /** 经营范围 */
    public static final String BUSINESS_SCOPE = "经营范围";
    /** 营业期限至 */
    public static final String BUSINESS_END_TIME = "营业期限至";
    /********************************** 基本信息结束 ******************************************/

    /********************************** 股东信息开始 ******************************************/
    /** 股东 */
    public static final String COMPANY_PARTNER = "股东";
    /** 证照/证件类型 */
    public static final String COMPANY_PARTNER_ID_TYPE = "证照/证件类型";
    /** 证照/证件类型 */
    public static final String COMPANY_PARTNER_ID = "证照/证件号码";
    /** 股东类型 */
    public static final String COMPANY_PARTNER_TYPE = "股东类型";
    /** 详情 */
    public static final String COMPANY_PARTNER_DETAIL = "详情";
    /********************************** 股东信息结束 ******************************************/

    /********************************** 变更信息开始 ******************************************/
    /** 变更事项 */
    public static final String CHANGE_CONTENT = "变更事项";
    /** 变更前内容 */
    public static final String CHANGE_CONTENT_BEFORE = "变更前内容";
    /** 变更后内容 */
    public static final String CHANGE_CONTENT_AFTER = "变更后内容";
    /** 变更日期 */
    public static final String CHANGE_DATE = "变更日期";
    /********************************** 变更信息结束 ******************************************/

    /********************************** 年检-企业基本信息开始 ******************************************/
    /** 企业名称 */
    public static final String REPORT_COMPANY_NAME = "企业名称";
    /** 企业联系电话 */
    public static final String REPORT_COMPANY_TELPHONE = "企业联系电话";
    /** 邮政编码 */
    public static final String REPORT_COMPANY_POSTCODE = "邮政编码";
    /** 企业通信地址 */
    public static final String REPORT_COMPANY_ADDRESS = "企业通信地址";
    /** 企业电子邮箱 */
    public static final String REPORT_COMPANY_MAIL = "企业电子邮箱";
    /** 有限责任公司本年度是否发生股东股权转让 */
    public static final String REPORT_COMPANY_STOCK_TRANSFER = "有限责任公司本年度是否发生股东股权转让";
    /** 企业经营状态 */
    public static final String REPORT_COMPANY_BUSINESS_STATE = "企业经营状态";
    /** 是否有网站或网店 */
    public static final String REPORT_COMPANY_WEB_SITE = "是否有网站或网店";
    /** 是否有投资信息或购买其他公司股权 */
    public static final String REPORT_COMPANY_OTHER_STOCK = "是否有投资信息或购买其他公司股权";
    /** 从业人数 */
    public static final String REPORT_COMPANY_WORKER_COUNT = "从业人数";
    /********************************** 年检-企业基本信息结束 ******************************************/
}
