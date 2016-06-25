package com.spoder.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.SAICCompany;
import models.SAICCompanyAbnormal;
import models.SAICCompanyChange;
import models.SAICCompanyCheckup;
import models.SAICCompanyOffice;
import models.SAICCompanyPartner;
import models.SAICCompanyPartnerInvest;
import models.SAICCompanyRecord;
import models.SAICCompanyReportBasic;
import models.SAICCompanyReportExternalGuarantee;
import models.SAICCompanyReportInvestInfo;
import models.SAICCompanyReportModel;
import models.SAICCompanyReportRevisionRecord;
import models.SAICCompanyReportStockChange;
import models.SAICCompanyReportStockInfo;
import models.SAICCompanyReportWebSite;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.spider.util.Http;
import com.spider.util.MongoAdapter;

/**
 * 程序入口(http://gsxt.saic.gov.cn/) DEMO
 * SITE：http://gsxt.scaic.gov.cn/ztxy.do?djjg=&maent.entbigtype=11&maent.pripid=5105000000013119&method=qyInfo&random=1464846846306
 * @author liushaofeng
 * @date 2016-6-2 下午07:29:32
 * @version 1.0.0
 */
public class Main
{
    /** 日志 */
    private static Logger log = Logger.getLogger(Main.class);
    /** 查询的地址 */
    private static String QUERY_BASE_URL = IProvienceConstant.PROVIENCE_SICHUAN;
    /** 数据库连接 */
    private static Datastore ds = null;
    /** 注册号/统一社会信用代码 */
    private static String rno = null;
    /** 年报数据 */
    private static List<SAICCompanyReportModel> reportList = new ArrayList<SAICCompanyReportModel>();;

    private static Map<String, String> argsMap = null;
    private static Document document = null;

    /**
     * 企业信息采集过程
     * @param id
     * @param rno
     * @param args
     * @throws IOException
     */
    public static void companyBasicInfoSpider(String id, String rno, String[] args) throws IOException
    {
        argsMap = convert(args);
        // =====================工商公示信息start=====================//
        String body = Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap), argsMap, null,
            null);
        document = Jsoup.parse(body);
        if (null != document)
        {
            // 登记信息-基本信息
            insertCompanyBasicInfo(document.select(".detailsList").get(0));
            // 登记信息-变更信息
            insertCompanyChange(document.select(".detailsList").get(2));
            // 登记信息-股东信息
            insertCompanyPartner(document.select(".detailsList").get(1));
        }

        argsMap.put("method", "baInfo");// 修改查询“备案信息”的参数
        body = Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap), argsMap, null, null);
        document = Jsoup.parse(body);
        if (null != document)
        {
            // 备案信息-主要人员信息
            // TODO 需要根据表格类型判断
            insertCompanyRecord(document.select(".detailsList").get(0));
            // 备案信息-分支机构信息
            insertCompanyOffice(document.select(".detailsList").get(1));
        }

        argsMap.put("method", "jyycInfo");// 修改查询“经营异常信息”的参数
        body = Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap), argsMap, null, null);
        document = Jsoup.parse(body);
        if (null != document)
        {
            // 经营异常信息
            insertCompanyAbnormal(document.select(".detailsList").get(0));
        }

        argsMap.put("method", "ccjcInfo");// 修改查询“抽查检查信息”的参数
        body = Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap), argsMap, null, null);
        document = Jsoup.parse(body);
        if (null != document)
        {
            // 抽查检查信息
            insertCompanyCheckup(document.select(".detailsList").get(0));
        }
        // =====================工商公示信息end=====================//

        // =====================企业公示信息start=====================//
        argsMap.put("method", "qygsInfo");// 修改查询“企业年报”的参数
        body = Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap), argsMap, null, null);
        document = Jsoup.parse(body);
        if (null != document)
        {
            // 企业年报列表
            findyearReportList();
            // 企业年报详情
            insertCompanyReport();
        }
        // =====================企业公示信息end=====================//

    }

    private static void insertCompanyReport()
    {
        for (SAICCompanyReportModel model : reportList)
        {
            String year = model.getReportYear().substring(0, 4);
            argsMap.put("method", "ndbgDetail");// 修改查询“年报详细信息”的参数
            argsMap.put("maent.nd", year);// 修改查询“年报详细信息”的参数
            document = Jsoup.parse(Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap),
                argsMap, null, null));
            if (null != document)
            {
                // 年度报告-企业基本信息
                insertCompanyReportBasic(document.select(".detailsList").get(0));
                // 年度报告-网站或网店信息
                insertCompanyReportWebsite(document.select(".detailsList").get(1), model.getReportYear());
                // 年度报告-股东（发起人）及出资信息
                insertCompanyReportStock(document.select(".detailsList").get(2), model.getReportYear());
                // 年度报告-对外投资信息
                insertCompanyReportInvest(document.select(".detailsList").get(3), model.getReportYear());
                // 对外提供保证担保信息
                insertCompanyReportExternalGuarantee(document.select(".detailsList").get(5), model
                    .getReportYear());
                // 股权变更信息
                insertCompanyReportStockChange(document.select(".detailsList").get(6), model
                    .getReportYear());
                // 年度报告-修改记录
                insertCompanyReportRevision(document.select(".detailsList").get(7), model.getReportYear());
            }
        }
        argsMap.remove("maent.nd");// 恢复参数
    }

    private static void insertCompanyReportRevision(Element parentElement, String year)
    {
        Elements select = parentElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的
            {
                SAICCompanyReportRevisionRecord reportRevisionInfo = new SAICCompanyReportRevisionRecord(
                    element.child(0).text(), element.child(1).text(), element.child(2).text(), element
                        .child(3).text(), element.child(3).text());
                reportRevisionInfo.setRy(year);
                reportRevisionInfo.setRno(rno);
                ds.save(reportRevisionInfo);
            }
        }
        System.out.println("insert Company Report Revision Finished!");
    }

    private static void insertCompanyReportStockChange(Element parentElement, String year)
    {
        Elements select = parentElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的
            {
                SAICCompanyReportStockChange stockChangeInfo = new SAICCompanyReportStockChange(element
                    .child(0).text(), element.child(1).text(), element.child(2).text(), element.child(3)
                    .text());
                stockChangeInfo.setRy(year);
                stockChangeInfo.setRno(rno);
                ds.save(stockChangeInfo);
            }
        }
        System.out.println("insert Company Report External Guarantee Finished!");
    }

    private static void insertCompanyReportExternalGuarantee(Element parentElement, String year)
    {
        Elements select = parentElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的
            {
                SAICCompanyReportExternalGuarantee externalGuaranteeInfo = new SAICCompanyReportExternalGuarantee(
                    element.child(0).text(), element.child(1).text(), element.child(2).text(), element
                        .child(3).text(), element.child(4).text(), element.child(5).text(), element
                        .child(6).text());
                externalGuaranteeInfo.setRy(year);
                externalGuaranteeInfo.setRno(rno);
                ds.save(externalGuaranteeInfo);
            }
        }
        System.out.println("insert Company Report External Guarantee Finished!");
    }

    private static void insertCompanyReportWebsite(Element element, String year)
    {
        Elements select = element.select("tr");
        if (select.size() > 2)
        {
            for (int i = 2; i < select.size(); i++)
            {
                SAICCompanyReportWebSite website = new SAICCompanyReportWebSite(select.get(i).child(0)
                    .text(), select.get(i).child(1).text(), select.get(i).child(2).text());
                website.setRno(rno);
                website.setRy(year);
                ds.save(website);
            }
        }
        System.out.println("insert Company Report Website Finished!");
    }

    private static void insertCompanyReportInvest(Element parentElement, String year)
    {
        Elements select = parentElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的
            {
                SAICCompanyReportInvestInfo investInfo = new SAICCompanyReportInvestInfo(element.child(0)
                    .text(), element.child(1).text());
                investInfo.setRy(year);
                investInfo.setRno(rno);
                ds.save(investInfo);
            }
        }
        System.out.println("insert Company Report Stock Finished!");

    }

    private static void insertCompanyReportStock(Element parentElement, String year)
    {
        Elements select = parentElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的
            {
                SAICCompanyReportStockInfo stockInfo = new SAICCompanyReportStockInfo(element.child(0)
                    .text(), element.child(1).text(), element.child(2).text(), element.child(3).text(),
                    element.child(4).text(), element.child(5).text(), element.child(6).text());
                stockInfo.setRy(year);
                stockInfo.setRno(rno);
                ds.save(stockInfo);
            }
        }
        System.out.println("insert Company Report Stock Finished!");
    }

    private static void insertCompanyReportBasic(Element element)
    {
        Elements select = element.select("tr");
        SAICCompanyReportBasic basicInfo = new SAICCompanyReportBasic();
        for (int i = 2; i < select.size(); i++)
        {
            Map<String, String> dataMap = resovleData(select);
            basicInfo.setRno(dataMap.get(IDataInfoKey.REGISTER_CODE));
            basicInfo.setCn(dataMap.get(IDataInfoKey.REPORT_COMPANY_NAME));
            basicInfo.setTel(dataMap.get(IDataInfoKey.REPORT_COMPANY_TELPHONE));
            basicInfo.setZip(dataMap.get(IDataInfoKey.REPORT_COMPANY_POSTCODE));
            basicInfo.setAddr(dataMap.get(IDataInfoKey.REPORT_COMPANY_ADDRESS));
            basicInfo.setEm(dataMap.get(IDataInfoKey.REPORT_COMPANY_MAIL));
            basicInfo.setEq(resovleBool(dataMap.get(IDataInfoKey.REPORT_COMPANY_STOCK_TRANSFER)));
            basicInfo.setPs(dataMap.get(IDataInfoKey.REPORT_COMPANY_BUSINESS_STATE));
            basicInfo.setWs(resovleBool(dataMap.get(IDataInfoKey.REPORT_COMPANY_WEB_SITE)));
            basicInfo.setIn(resovleBool(dataMap.get(IDataInfoKey.REPORT_COMPANY_OTHER_STOCK)));
            basicInfo.setPpn(dataMap.get(IDataInfoKey.REPORT_COMPANY_WORKER_COUNT));
        }
        basicInfo.setRno(rno);
        ds.save(basicInfo);
        System.out.println("insert Company Report Basic Finished!");
    }

    // 0-否；1-是；-1-空
    private static int resovleBool(String data)
    {
        if (null == data || data.isEmpty())
        {
            return -1;
        } else
        {
            if ("是".equals(data.trim()))
            {
                return 1;
            }
            return 0;
        }
    }

    private static void findyearReportList()
    {
        reportList.clear();// 清空数据
        if (document.select(".detailsList").size() > 0)
        {
            Elements trElements = document.select("#sifapanding").select(".detailsList").get(1)
                .select("tr");
            for (int i = 2; i < trElements.size(); i++)
            {
                Element element = trElements.get(i);// 一年的年报
                reportList.add(new SAICCompanyReportModel(element.child(0), element.child(1), element
                    .child(2)));
            }
        }
    }

    private static void insertCompanyCheckup(Element checkupElement)
    {
        Elements select = checkupElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的TR
            {
                SAICCompanyCheckup e = new SAICCompanyCheckup(element.child(0).text(), element.child(1)
                    .text(), element.child(2).text(), element.child(3).text(), element.child(4).text());
                e.setRno(rno);
                ds.save(e);
            }
        }
        // TODO 写入DB
        System.err.println("Insert Company Checkup Finished!");
    }

    private static void insertCompanyAbnormal(Element abnormalElement)
    {
        Elements select = abnormalElement.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的TR
            {
                SAICCompanyAbnormal e = new SAICCompanyAbnormal(element.child(0).text(), element.child(1)
                    .text(), element.child(2).text(), element.child(3).text(), element.child(4).text(),
                    element.child(5).text());
                e.setRno(rno);
                ds.save(e);
            }
        }
        System.err.println("Insert Company Abnormal Finished......");
    }

    private static void insertCompanyOffice(Element officeInfo)
    {
        if (null == officeInfo)
        {
            log.info(rno + "无分支机构信息！");
            return;
        }

        Elements select = officeInfo.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的TR
            {
                SAICCompanyOffice e = new SAICCompanyOffice(element.child(0).text(), element.child(1)
                    .text(), element.child(2).text(), element.child(3).text());
                e.setRno(rno);
                ds.save(e);
            }
        }
        System.err.println("Insert Company Office Finished!");

    }

    private static void insertCompanyRecord(Element partnerInfo)
    {
        Elements select = partnerInfo.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的TR
            {
                SAICCompanyRecord e = new SAICCompanyRecord(element.child(0).text(), element.child(1)
                    .text(), element.child(2).text());
                e.setRno(rno);
                ds.save(e);
                SAICCompanyRecord e2 = new SAICCompanyRecord(element.child(3).text(), element.child(4)
                    .text(), element.child(5).text());
                e2.setRno(rno);
                ds.save(e2);
            }
        }
        System.err.println("Insert Company Record Finished......");
    }

    private static void insertCompanyChange(Element changeInfo)
    {
        Elements select = changeInfo.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的TR
            {
                SAICCompanyChange e = new SAICCompanyChange(element.child(0).text(), element.child(1)
                    .text(), element.child(2).text(), element.child(3).text());
                e.setRno(rno);
                ds.save(e);
            }
        }
        System.out.println("Insert Company Change Finished!");
    }

    private static void insertCompanyPartner(Element companyPartnerInfo)
    {
        Elements select = companyPartnerInfo.select("tr");
        for (int i = 2; i < select.size(); i++)
        {
            Element element = select.get(i);
            if (null != element && !element.child(0).hasAttr("colspan"))// 不是翻页的
            {
                SAICCompanyPartner e = null;
                if (-1 != element.child(4).html().indexOf("<a>"))// 如果股东详细信息部分是链接，则获取获取
                {
                    String indexId = insertCompanyPartnerDetail(element.child(4));
                    e = new SAICCompanyPartner(element.child(0).text(), element.child(1).text(), element
                        .child(2).text(), element.child(3).text(), indexId);
                } else
                {
                    e = new SAICCompanyPartner(element.child(0).text(), element.child(1).text(), element
                        .child(2).text(), element.child(3).text());
                }
                e.setRno(rno);
                ds.save(e);
            }
        }
        System.out.println("Insert Company Partner Finished!");
    }

    private static String insertCompanyPartnerDetail(Element child)
    {
        String attr = child.child(0).attr("onclick");// showRyxx('610400122028211','6104001220282')
        String substring = attr.substring(attr.indexOf("(") + 2, attr.lastIndexOf(",") - 1);
        argsMap.put("method", "tzrCzxxDetial");// 查询“股东及出资信息”的参数
        argsMap.put("maent.xh", substring);
        String body = Http.httpPostSpider(QUERY_BASE_URL + Http.getUrlParamSuffix(argsMap), argsMap, null,
            null);
        document = Jsoup.parse(body);
        Element tableElement = document.select(".detailsList").get(0);
        Element dataElement = tableElement.select("tr").get(3);
        SAICCompanyPartnerInvest companyPartnerInvest = new SAICCompanyPartnerInvest(dataElement.child(0)
            .text(), dataElement.child(3).text(), dataElement.child(4).text(), dataElement.child(5).text(),
            dataElement.child(6).text(), dataElement.child(7).text(), dataElement.child(8).text());
        companyPartnerInvest.setRno(rno);
        ds.save(companyPartnerInvest);
        argsMap.remove("maent.xh");
        // 查询出数据记录时的入库ID, 并返回该ID
        Query<SAICCompanyPartnerInvest> filter = ds.createQuery(SAICCompanyPartnerInvest.class).filter(
            "sn", dataElement.child(0).text());
        return filter.iterator().next().getId().toString();
    }

    private static void insertCompanyBasicInfo(Element companyBasicInfo)
    {
        Elements trs = companyBasicInfo.select("tr");
        String companyType = trs.get(2).child(1).text();// 基本信息-类型
        if (ICompanyType.COMPANY_TYPE_COLLECTIVE_OWNERSHIP.equals(companyType.trim())
            || ICompanyType.COMPANY_TYPE_OTHER_LIMITED.equals(companyType.trim()))
        {
            SAICCompany basicInfo = new SAICCompany();
            Map<String, String> dataMap = resovleData(trs);

            rno = dataMap.get(IDataInfoKey.REGISTER_CODE);
            basicInfo.setUsc(dataMap.get(IDataInfoKey.REGISTER_CODE));
            basicInfo.setRno(dataMap.get(IDataInfoKey.REGISTER_CODE));
            basicInfo.setCn(dataMap.get(IDataInfoKey.COMPANY_NAME));
            basicInfo.setTp(dataMap.get(IDataInfoKey.COMPANY_TYPE));
            basicInfo.setLp(dataMap.get(IDataInfoKey.LEGAL_REPRESENTATIVE));
            basicInfo.setRc(dataMap.get(IDataInfoKey.REGISTERED_CAPITAL));
            basicInfo.setEd(dataMap.get(IDataInfoKey.COMPANY_CREATE_TIME));
            basicInfo.setAdd(dataMap.get(IDataInfoKey.RESIDENCE));
            basicInfo.setBts(dataMap.get(IDataInfoKey.BUSINESS_START_TIME));
            basicInfo.setBte(dataMap.get(IDataInfoKey.BUSINESS_END_TIME));
            basicInfo.setBs(dataMap.get(IDataInfoKey.BUSINESS_SCOPE));
            basicInfo.setRi(dataMap.get(IDataInfoKey.REGISTRATION_AUTHORITY));
            basicInfo.setIsd(dataMap.get(IDataInfoKey.APPROVED_DATE));
            basicInfo.setRs(dataMap.get(IDataInfoKey.REGISTRATION_STATUS));
            ds.save(basicInfo);
            System.out.println("Insert Company Basic Info Finished!");
        }
    }

    private static void printMap(Map<String, String> dataMap)
    {
        Iterator<Entry<String, String>> iterator = dataMap.entrySet().iterator();
        while (iterator.hasNext())
        {
            Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + "=" + next.getValue());
        }
    }

    /**
     * 将表格的数据遍历解析成键值对形式的数据
     * @param trs tr documents
     * @return the data of key-value
     */
    private static Map<String, String> resovleData(Elements trs)
    {
        Map<String, String> dataMap = new HashMap<String, String>();
        for (Element element : trs)
        {
            Elements children = element.children();
            if (children.size() % 2 == 0)
            {
                for (int i = 0; i < children.size() - 1; i += 2)
                {
                    dataMap.put(children.get(i).text(), children.get(i + 1).text());
                }
            }
        }
        return dataMap;
    }

    private static Map<String, String> convert(String[] args)
    {
        Map<String, String> argsMap = new HashMap<String, String>();
        for (String string : args)
        {
            String[] split = string.split("=");
            argsMap.put(split[0].trim(), split.length != 2 ? "" : split[1].trim());// 部分请求参数只有key，没有value
        }
        return argsMap;
    }

    public static void main(String[] args) throws InterruptedException, IOException
    {
        ds = MongoAdapter.getDatastore();
        // 假设需要两个参数rno和id
        String rno = "330181000025882";// 注册号
        String id = "4ADE1F1F9AE59A14DFA1002E20ADC9F9BB124093C9EFDE9E4AE953C1369E934A";// 序号

        companyBasicInfoSpider(id, rno, args);
    }
}
