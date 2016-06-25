package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

/**
 * 用于记录发起企业信息采集时，必须的URL和相关参数
 */
@Entity
public class SAICCompanySpider {
    @Id
    public ObjectId id;
    /**
     * 注册号
     */
    @Indexed
    public String rno = null;//对应工商C1
    /**
     * 记录采集URL
     * 示例：
     * GET形式 http://gsxt.cqgs.gov.cn/search_getEnt.action?entId=5002241201405210547219&id=500224602062266&type=16
     * POST形式URL中POST参数和URL中间用@@分隔，后面组成形式为key=value&key=value，并在后续程序中提取处理
     * POST形式 http://gsxt.cqgs.gov.cn/search_getEnt.action@@entId=5002241201405210547219&id=500224602062266&type=16
     */
    public String url = null;
    /**
     * 创建/更新时间
     */
    @Indexed
    public long ut = System.currentTimeMillis();//

    public SAICCompanySpider() {

    }
}
