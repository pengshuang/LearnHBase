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
public class ScanExample1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test");

        Scan scan1 = new Scan();
        ResultScanner scanner1 = table.getScanner(scan1);
        for (Result res: scanner1) {
            System.out.println(res);
        }
        scanner1.close();

        Scan scan2 = new Scan();
        scan2.addFamily(Bytes.toBytes("colfam1"));
        ResultScanner scanner2 = table.getScanner(scan2);
        for (Result res: scanner2) {
            System.out.println(res);
        }
        scanner2.close();

        Scan scan3 = new Scan();
        // 使用 builder 模式将详细限制条件添加到 Scan 中
        scan3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-5"))
                .addColumn(Bytes.toBytes("colfam2"), Bytes.toBytes("col-33"))
                .setStartRow(Bytes.toBytes("row-10")).setStopRow(Bytes.toBytes("row-20"));
        ResultScanner scanner3 = table.getScanner(scan3);
        for(Result res: scanner3) {
            System.out.println(res);
        }
        scanner3.close();

    }


}
