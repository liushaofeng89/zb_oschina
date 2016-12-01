
/**
 * 伪代码示例说明
 * Created by su on 2016/3/7.
 */
public class Demo {
    /**
     * 日志
     */
    private static Logger log = Logger.getLogger(Demo.class);

    public static void main(String[] args) throws Exception {
        ds = MongoAdapter.getDatastore();
        //假设需要两个参数rno和id
        String rno = "330181000025882";//注册号
        String id = "4ADE1F1F9AE59A14DFA1002E20ADC9F9BB124093C9EFDE9E4AE953C1369E934A";//序号

        companyBasicInfoSpider(id, rno);
    }

    /**
     * 企业信息采集过程
     *
     * @param id
     * @param rno
     */
    public static void companyBasicInfoSpider(String id, String rno) {
        //=====================工商公示信息start=====================//
        //登记信息-基本信息
        insertCompanyBasicInfo(参数组);
        //登记信息-股东信息
        insertCompanyPartner(参数组);
        //登记信息-变更信息
        insertCompanyChange(参数组);
        //备案信息-主要人员信息
        insertCompanyRecord(参数组);
        //备案信息-分支机构信息
        insertCompanyOffice(参数组);
        //经营异常信息
        insertCompanyAbnormal(参数组);
        //抽查检查信息
        insertCompanyCheckup(参数组);
        //=====================工商公示信息end=====================//

        //=====================企业公示信息start=====================//
        //企业年报列表
        findyearReportList(参数组);
        //企业年报详情
        insertCompanyReport(参数组);
        //=====================企业公示信息end=====================//
    }

    //企业年报详情方法说明
    insertCompanyReport(参数组) {
        //年度报告-企业基本信息
        insertCompanyReportBasic(参数组);
        //年度报告-股东（发起人）及出资信息
        insertCompanyReportStock(参数组);
        //年度报告-对外投资信息
        insertCompanyReportInvest(参数组);
        //年度报告-网站或网店信息
        insertCompanyReportWebsite(参数组);
        //对外提供保证担保信息
        insertCompanyReportExternalGuarantee(参数组);
        //股权变更信息
        insertCompanyReportStockChange(参数组);
        //年度报告-修改记录
        insertCompanyReportRevision(参数组);
    }
}
