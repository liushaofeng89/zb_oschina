package models;

import org.jsoup.nodes.Element;

/**
 * 年报信息列表数据模型
 * @author liushaofeng
 * @date 2016-6-6 下午11:14:55
 * @version 1.0.0
 */
public class SAICCompanyReportModel
{
    private int index;
    private String reportYear;
    private String publishDate;

    /**
     * default constructor
     */
    public SAICCompanyReportModel()
    {
    }

    /**
     * constructor with parameter
     * @param index 年报索引编号
     * @param reportYearInfo 报告年份
     * @param publishDate 报告发布日期
     */
    public SAICCompanyReportModel(Element index, Element reportYearInfo, Element publishDate)
    {
        this.index = Integer.parseInt(index.text().trim());
        this.reportYear = reportYearInfo.text().trim();
        this.publishDate = publishDate.text().trim();
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getReportYear()
    {
        return reportYear;
    }

    public void setReportYear(String reportYear)
    {
        this.reportYear = reportYear;
    }

    public String getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(String publishDate)
    {
        this.publishDate = publishDate;
    }

}
