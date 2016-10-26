package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

public class CreateTable {

    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);

        HTableDescriptor table = new HTableDescriptor(toBytes("temp3"));
        HColumnDescriptor family = new HColumnDescriptor(toBytes("user_info"));
        table.addFamily(family);
        admin.createTable(table);
        System.out.println("Table created!");
    }
}
