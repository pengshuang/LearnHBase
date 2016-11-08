package Filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.filter.TimestampsFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Filter9 {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        List<Long> ts = new ArrayList<Long>();
        // 向列表中添加时间戳
        ts.add(new Long(5));
        ts.add(new Long(10));
        ts.add(new Long(15));
        Filter filter = new TimestampsFilter(ts);

        Scan scan1 = new Scan();
        // 向scan实例中添加过滤器
        scan1.setFilter(filter);
        ResultScanner scanner1 = hTable.getScanner(scan1);
        for(Result result:scanner1){
            System.out.println(result);
        }
        scanner1.close();

        Scan scan2 = new Scan();
        scan2.setFilter(filter);
        // 添加时间范围限制
        scan2.setTimeRange(8,12);
        ResultScanner scanner2 = hTable.getScanner(scan2);
        for(Result result:scanner2){
            System.out.println(result);
        }
        scanner2.close();
    }
}
