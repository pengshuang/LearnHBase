package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

/**
 * Created by pengshuang on 16/10/25.
 */
public class GetData {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");

        Get get = new Get(toBytes("row1"));
        Result result = hTable.get(get);
        byte[] value = result.getValue(Bytes.toBytes("user_info"), Bytes.toBytes("age"));
        System.out.println(Bytes.toString(value));
        hTable.close();
    }
}
