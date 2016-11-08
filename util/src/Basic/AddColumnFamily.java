package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/24.
 */
public class AddColumnFamily {

    public static void main(String[] args) throws IOException
    {

        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);
        HColumnDescriptor columnDescriptor = new HColumnDescriptor("qual1");

        admin.addColumn("temp", columnDescriptor);
        System.out.print("coloumn added");

    }
}
