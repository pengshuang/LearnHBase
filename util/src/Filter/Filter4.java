package Filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Filter4 {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL,
                new SubstringComparator(".4"));
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result result : scanner){
            System.out.println(result);
        }
        scanner.close();
    }
}