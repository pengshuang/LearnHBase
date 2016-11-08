package Filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Filter1 {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-0"));
        // 指定比较运算符和比较器
        Filter filter1 = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,
                new BinaryComparator(Bytes.toBytes("row-22")));
        scan.setFilter(filter1);
        ResultScanner scanner1 = hTable.getScanner(scan);
        for(Result res: scanner1){
            System.out.println(res);
        }
        scanner1.close();

        // 用正则表达式来匹配行键
        Filter filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL,
                new RegexStringComparator(".*-.5"));
        scan.setFilter(filter2);
        ResultScanner scanner2 = hTable.getScanner(scan);
        for(Result res: scanner2){
            System.out.println(res);
        }
        scanner2.close();

        // 子串匹配方法
        Filter filter3 = new RowFilter(CompareFilter.CompareOp.EQUAL,
                new SubstringComparator("-5"));
        scan.setFilter(filter3);
        ResultScanner scanner3 = hTable.getScanner(scan);
        for(Result res: scanner3){
            System.out.println(res);
        }
        scanner3.close();
    }
}
