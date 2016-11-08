package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengshuang on 16/10/28.
 */
public class BatchExample1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test");

        byte[] ROW1 = Bytes.toBytes("row1");
        byte[] ROW2 = Bytes.toBytes("row2");

        byte[] COLFAM1 = Bytes.toBytes("colfam1");
        byte[] COLFAM2 = Bytes.toBytes("colfam2");

        byte[] QUAL1 = Bytes.toBytes("qual1");
        byte[] QUAL2 = Bytes.toBytes("qual2");

        List<Row> batch = new ArrayList<Row>();

        Put put = new Put(ROW1);
        put.add(COLFAM1, QUAL2,Bytes.toBytes("val5"));
        batch.add(put);

        Get get1 = new Get(ROW2);
        get1.addColumn(COLFAM1,QUAL2);
        batch.add(get1);

        Delete delete = new Delete(ROW1);
        delete.deleteColumns(COLFAM1,QUAL2);
        batch.add(delete);

        Object[] results = new Object[batch.size()];
        try {
            table.batch(batch, results);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        for(int i = 0; i < results.length; i++) {
            System.out.println("Result[" + i + "]: " + results[i]);
        }


    }
}
