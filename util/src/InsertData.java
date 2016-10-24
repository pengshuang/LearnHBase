import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/24.
 */
public class InsertData {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        Put p = new Put(Bytes.toBytes("1"));
        p.add(Bytes.toBytes("user_info"), Bytes.toBytes("height"), Bytes.toBytes("180"));
        hTable.put(p);
        hTable.close();
    }
}
