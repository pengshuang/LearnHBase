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
public class Filter6 {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        SingleColumnValueFilter filter = new SingleColumnValueFilter(
                Bytes.toBytes("colfam1"),
                Bytes.toBytes("col-5"),
                CompareFilter.CompareOp.NOT_EQUAL,
                new SubstringComparator("val-5"));
        filter.setFilterIfMissing(true);

        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result result: scanner){
            System.out.println(result);
        }
        scanner.close();

        Get get = new Get(Bytes.toBytes("row-6"));
        get.setFilter(filter);
        Result result = hTable.get(get);
        System.out.println(result);
    }
}
