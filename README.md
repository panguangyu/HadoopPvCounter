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



