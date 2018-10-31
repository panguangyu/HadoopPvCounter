# hadoopPvCounter
使用Hadoop-MapReduce根据nginx日志计算PV

步骤：

1、在IntelliJ Idea中将PVCounter.java打包成jar包，并上传到本地目录如 /home/panguangyu/pvcounter.jar

2、在hdfs上传test_log作为w计算文件，

> bin/hadoop fs -mkdir /logs_input <br />

3、启动wordcount计算程序

> bin/hadoop jar /home/panguangyu/pvcounter.jar

4、查看hdfs上的输出文件，输出在output目录下

> bin/hadoop fs -cat /output/part-r-00000

5、输出结果如下：

10.228.39.162   18<br />
10.228.39.172   14<br />
10.228.39.173   6<br />
10.228.39.186   3<br />
10.228.39.187   3<br />
10.228.39.199   17<br />
10.228.8.2      1<br />
10.228.8.3      1<br />
