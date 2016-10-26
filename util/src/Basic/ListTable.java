package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;


/**
 * Created by pengshuang on 16/10/24.
 */
public class ListTable {

    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);

        HTableDescriptor[] tableDescriptor = admin.listTables();
        for(int i=0; i < tableDescriptor.length; i++) {
            System.out.println(tableDescriptor[i].getNameAsString());
        }
    }
}
