package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

/**
 * Created by pengshuang on 16/10/26.
 */
public class DeleteData {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HTable hTable = new HTable(conf, "temp2");
        Delete delete = new Delete(toBytes("1"));
        delete.deleteColumn(Bytes.toBytes("user_info2"), Bytes.toBytes("age"));
        hTable.delete(delete);
        hTable.close();

    }
}
