package Filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Filter5 {
    private static void filter(boolean drop,
                               CompareFilter.CompareOp operator,
                               ByteArrayComparable comparator)
    throws IOException {
        Filter filter;
        if(comparator != null){
            filter = new DependentColumnFilter(Bytes.toBytes("colfam1"),
                    Bytes.toBytes("col-5"), drop, operator, comparator);
        } else {
            filter = new DependentColumnFilter(Bytes.toBytes("colfam1"),
                    Bytes.toBytes("col-5"), drop);
        }

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = hTable.getScanner(scan);
        for(Result result : scanner){
            System.out.println(result);
        }
        scanner.close();

        Get get = new Get(Bytes.toBytes("row-5"));
        get.setFilter(filter);
        Result result = hTable.get(get);
        System.out.println(result);

    }
    public static void main(String[] args) throws IOException
    {
        filter(true, CompareFilter.CompareOp.NO_OP, null);
        filter(false, CompareFilter.CompareOp.NO_OP, null);
        filter(true, CompareFilter.CompareOp.EQUAL,
                new BinaryPrefixComparator(Bytes.toBytes("val-5")));
        filter(false, CompareFilter.CompareOp.EQUAL,
                new BinaryPrefixComparator(Bytes.toBytes("val-5")));
        filter(true, CompareFilter.CompareOp.EQUAL,
                new RegexStringComparator(".*\\.5"));
        filter(false, CompareFilter.CompareOp.EQUAL,
                new RegexStringComparator(".*\\.5"));
    }

}
