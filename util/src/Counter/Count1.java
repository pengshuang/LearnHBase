package Counter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/26.
 */
public class Count1 {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");
        // 计数器值加一
        long cnt1 = hTable.incrementColumnValue(Bytes.toBytes("20160101"),
                Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1);
        long cnt2 = hTable.incrementColumnValue(Bytes.toBytes("20160101"),
                Bytes.toBytes("daily"), Bytes.toBytes("hits"), 1);
        // 得到计数器的当前值
        long current = hTable.incrementColumnValue(Bytes.toBytes("20160101"),
                Bytes.toBytes("daily"), Bytes.toBytes("hits"), 0);

        long cnt3 = hTable.incrementColumnValue(Bytes.toBytes("20160101"),
                Bytes.toBytes("daily"), Bytes.toBytes("hits"), -1);


    }
}
