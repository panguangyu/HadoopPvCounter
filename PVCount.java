package project;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PVCount {
    static String INPUT = "/logs_input";
    static String OUTPUT = "/logs_output";

    static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String[] logArguments = line.split(" ");
            String ip = logArguments[0];
            context.write(new Text(ip), new LongWritable(1));
        }
    }

    static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> value, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
            Long sum = 0L;
            for (LongWritable c: value) {
                sum += c.get();
            }
            context.write(key, new LongWritable(sum));
        }
    }

    public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException {
        Job job = Job.getInstance(new Configuration(), PVCount.class.getSimpleName());
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setJarByClass(PVCount.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, INPUT);
        FileOutputFormat.setOutputPath(job, new Path(OUTPUT));

        job.waitForCompletion(true);
    }
}
