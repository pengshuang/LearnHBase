package Counter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.util.Bytes;
import org.hsqldb.Table;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Count2 {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");
        Increment increment1 = new Increment(Bytes.toBytes("20160101"));
        increment1.addColumn(Bytes.toBytes("daily"), Bytes.toBytes("clicks"), 1);
        increment1.addColumn(Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1);
        increment1.addColumn(Bytes.toBytes("weekly"), Bytes.toBytes("click"), 10);
        increment1.addColumn(Bytes.toBytes("weekly"), Bytes.toBytes("hits"), 10);

    }
}
