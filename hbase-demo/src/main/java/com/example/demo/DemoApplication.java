package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.protobuf.generated.AdminProtos;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static Configuration config;

    public static void main(String[] args) throws URISyntaxException, IOException {
        //SpringApplication.run(DemoApplication.class, args);
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
        TableName tableName = TableName.valueOf("myTableName");
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(tableName);
            Put put = new Put(Bytes.toBytes("row_key1"));
            put.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("user_id"), Bytes.toBytes("1001"));
            put.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("user_name"), Bytes.toBytes("张三"));
            put.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("mobile_no"), Bytes.toBytes("131****1111"));
            table.put(put);
        }


    }

    @Test
    public void DDL() throws IOException, URISyntaxException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));

        try (Connection connection = ConnectionFactory.createConnection(config);
             Admin admin = connection.getAdmin()) {
            TableName tableName = TableName.valueOf("myTableName");
            if (admin.tableExists(tableName)) {
                return;
            }
            //设置列族
            ColumnFamilyDescriptor cfDesc = ColumnFamilyDescriptorBuilder
                    .newBuilder(Bytes.toBytes("personal_info"))
                    .setCompressionType(Compression.Algorithm.SNAPPY)//压缩算法
                    .setCompactionCompressionType(Compression.Algorithm.SNAPPY)//合并压缩算法
                    .setDataBlockEncoding(DataBlockEncoding.PREFIX)//写入硬盘编码方式
                    .setBloomFilterType(BloomType.ROW)//布隆过滤器类型
                    .setMinVersions(1)//设置列族最小版本数
                    .setMaxVersions(3)//设置列族最大版本数
                    .build();
            //设2置表
            TableDescriptor tableDesc = TableDescriptorBuilder.newBuilder(tableName)
                            .setColumnFamily(cfDesc)
                            .build();
            //创建表
            admin.createTable(tableDesc);
        }

    }

    @Test
    public void testGet() throws IOException, URISyntaxException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));

        TableName tableName = TableName.valueOf("myTableName");
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(tableName);
            String rowKey = "row_key1";
            Get get = new Get(Bytes.toBytes(rowKey));
            //获取指定行指定列
            //get.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("user_name"));
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                System.out.println("行键 = " + Bytes.toString(result.getRow()));
                System.out.println("列族 = " + Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println("列限定符 = " + Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println("值 = " + Bytes.toString(CellUtil.cloneValue(cell)));
                System.out.println("时间戳 = " + cell.getTimestamp());
            }
        }

    }

    @Test
    public void testGet1() throws IOException {
        TableName tableName = TableName.valueOf("myTableName");
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(tableName);
            String rowkey = "row_key1";
            Get get = new Get(Bytes.toBytes(rowkey));
            get.addColumn(Bytes.toBytes("F"), Bytes.toBytes("name"));
            get.addColumn(Bytes.toBytes("F"), Bytes.toBytes("age"));
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                System.out.println("rowkey = " + Bytes.toString(result.getRow()));
                System.out.println("列族 = " + Bytes.toString(CellUtil.cloneFamily(cell)));
            }

        }
    }

    @Test
    public void testScan1() throws IOException, URISyntaxException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));

        TableName tableName = TableName.valueOf("myTableName");
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(tableName);
            Scan scan = new Scan();
            //扫描指定列族、指定列
            scan.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("user_name"));
            scan.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("mobile_no"));
            //使用缓存块
            scan.setCacheBlocks(true);
            //客户端每次rpc fetch 的行数
            scan.setCaching(100);
            scan.withStartRow(Bytes.toBytes("row_key1"));
            scan.withStopRow(Bytes.toBytes("row_key20"));
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                for (Cell cell : result.rawCells()) {
                    System.out.println("行键 = " + Bytes.toString(result.getRow()));
                    System.out.println("列族 = " + Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println("列限定符 = " + Bytes.toString(CellUtil.cloneQualifier(cell)));
                    System.out.println("值 = " + Bytes.toString(CellUtil.cloneValue(cell)));
                    System.out.println("时间戳 = " + cell.getTimestamp());
                }
            }
        }
    }

    @Test
    public void testDelete() throws IOException, URISyntaxException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));

        TableName tableName = TableName.valueOf("myTableName");
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(tableName);
            Delete delete = new Delete(Bytes.toBytes("row_key11"));
			delete.addColumn(Bytes.toBytes("personal_info"), Bytes.toBytes("city"));
            table.delete(delete);
        }
    }

    @Test
    public void testFilter() throws IOException, URISyntaxException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        config.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));

        TableName tableName = TableName.valueOf("myTableName");
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(tableName);
            Scan scan = new Scan();
            //使用缓存块
            scan.setCacheBlocks(true);
            //客户端每次rpc fetch 的行数
            scan.setCaching(100);
            FilterList filterList = new FilterList();
            //相当于行内各列 like '%张%'
            Filter substringFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("张"));
            scan.setFilter(substringFilter);
            //指定列 like '%张%'
            Filter singleColumnValueFilter = new SingleColumnValueFilter(Bytes.toBytes("personal_info"),
                    Bytes.toBytes("user_name"), CompareFilter.CompareOp.EQUAL, new SubstringComparator("张"));
            //分页过滤器
            Filter pageFilter = new PageFilter(2L);
            filterList.addFilter(substringFilter);
            filterList.addFilter(singleColumnValueFilter);
            filterList.addFilter(pageFilter);
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                for (Cell cell : result.rawCells()) {
                    System.out.println("行键 = " + Bytes.toString(result.getRow()));
                    System.out.println("列族 = " + Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println("列限定符 = " + Bytes.toString(CellUtil.cloneQualifier(cell)));
                    System.out.println("值 = " + Bytes.toString(CellUtil.cloneValue(cell)));
                    System.out.println("时间戳 = " + cell.getTimestamp());
                }
            }
        }
    }


}