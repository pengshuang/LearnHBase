package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengshuang on 16/10/27.
 */
public class GetExample2 {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test1");

        byte[] cf1 = Bytes.toBytes("colfam1");
        byte[] qf1 = Bytes.toBytes("qual1");
        byte[] qf2 = Bytes.toBytes("qual2");

        byte[] row1 = Bytes.toBytes("row1");
        byte[] row2 = Bytes.toBytes("row2");

        List<Get> gets = new ArrayList<Get>();

        Get get1 = new Get(row1);
        get1.addColumn(cf1,qf1);
        gets.add(get1);

        Get get2 = new Get(row2);
        get2.addColumn(cf1, qf2);
        gets.add(get2);

        Get get3 = new Get(row2);
        get3.addColumn(cf1, qf2);
        gets.add(get3);
        // 从 Hbase 中获取这些行和选定的列
        Result[] results = table.get(gets);
        System.out.println("First iteration...");
        for (Result result : results) {
            String row = Bytes.toString(result.getRow());
            System.out.print("Row: " + row + " ");
            byte[] val = null;
            // 遍历结果并检查哪些行中包含选定的列
            if (result.containsColumn(cf1, qf1)){
                val = result.getValue(cf1,qf1);
                System.out.println("Value: " + Bytes.toString(val));
            }
            if (result.containsColumn(cf1, qf2)){
                val = result.getValue(cf1,qf2);
                System.out.println("Value: " + Bytes.toString(val));
            }
        }
    }


}
