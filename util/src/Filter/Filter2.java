package Filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Filter2 {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");
        // 创建一个过滤器, 指定比较运算符和比较器, 使用过滤器来返回特定的列族
        Filter filter1 = new FamilyFilter(CompareFilter.CompareOp.LESS,
                new BinaryComparator(Bytes.toBytes("colfam3")));
        Scan scan = new Scan();
        scan.setFilter(filter1);
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result res: scanner){
            System.out.println(res);
        }
        scanner.close();

        Get get1 = new Get(Bytes.toBytes("row-5"));
        get1.setFilter(filter1);
        Result result1 = hTable.get(get1);
        System.out.println("Result of get(): " + result1);

        Filter filter2 = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
                new BinaryComparator(Bytes.toBytes("colfam3")));
        Get get2 = new Get(Bytes.toBytes("row-5"));
        get2.addFamily(Bytes.toBytes("colfam1"));
        get2.setFilter(filter2);
        Result result2 = hTable.get(get2);
        System.out.print("Result of get(): " + result2);
    }
}
