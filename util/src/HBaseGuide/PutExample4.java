package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengshuang on 16/10/27.
 */
public class PutExample4 {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test1");

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        //检查指定列是否存在,按检查的结果决定是否执行 put 操作
        boolean res1 = table.checkAndPut(Bytes.toBytes("row1"),
                Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),null,put1);
        //输出结果应为: "Put applied: true"
        System.out.println("Put applied: " + res1);
        //再次向同一单元格写入数据
        boolean res2 = table.checkAndPut(Bytes.toBytes("row1"),
                Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),null,put1);
        //因为那个列的值已经存在,此时的输出结果应为 "Put applied:false"
        System.out.println("Put applied: " + res2);

        Put put2 = new Put(Bytes.toBytes("row1"));
        //创建一个新的 Put 实例,这次使用一个不同的列限定符
        put2.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));
        //当上一次的put值存在时,写入新的值
        boolean res3 = table.checkAndPut(Bytes.toBytes("row1"),
                Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),Bytes.toBytes("val1"),put2);
        //因为已经存在,所以输出的结果应当为 "Put applied: true"
        System.out.println("Put applied: " + res3);

    }


}
