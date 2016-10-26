package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/24.
 */
public class DeleteColumn {
    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);

        admin.deleteColumn("temp","contactDetails");
        System.out.print("column deleted!");
    }
}
