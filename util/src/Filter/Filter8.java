package Filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.nativeio.NativeIO;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Filter8 {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");
        Filter filter = new InclusiveStopFilter(Bytes.toBytes("row-5"));

        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes("row-3"));
        scan.setFilter(filter);
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result result:scanner){
            System.out.println(result);
        }
        scanner.close();
    }
}
