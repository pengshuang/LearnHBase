import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class ScanTable {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("user_info"), Bytes.toBytes("age"));
        scan.addColumn(Bytes.toBytes("user_info"), Bytes.toBytes("name"));
        ResultScanner scanner = hTable.getScanner(scan);
        for (Result result = scanner.next(); result != null; result = scanner.next()){
            System.out.println("Found row: " + result.toString());
        }
        scanner.close();
    }
}
