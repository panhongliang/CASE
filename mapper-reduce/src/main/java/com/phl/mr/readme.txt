Mapper类的四个泛型
Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
  KEYIN:当前行的偏移量的类型;
  VALUEIN:当前行的内容的类型;
  KEYOUT:传递给reduce的key的类型
  VALUEOUT传递给reduce的value的类型
map方法：
三个入参：
  protected void map(KEYIN key, VALUEIN value,
                     Context context) throws IOException, InterruptedException {
    context.write((KEYOUT) key, (VALUEOUT) value);
  }
 context.write((KEYOUT) key, (VALUEOUT) value);完成partition(分区)工作。
 默认的分区方式为：按照key的hashcode分区 ，hashcode相同的key会被分到同一个reduce上，
 可以利用这点完成一些特殊的功能 ，如相同省份的数据放在同一个结果文件中。
 具体实现为：
 实现Partitioner的getPartition 方法，然后配置：job.setPartitionerClass()

 reduce方法：
 protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

 相同hashcode的key会被送到同一个reduce中,
 相同key的value，会被放在Iterable中一次调用reduce,
 一个reduce节点会产生一个结果文件，可以使用这点，使相关联的key分配到同一个reduce上，然后产生的结果放在同一个文件中。


 注：当job.setNumReduceTasks(1);时分区方法将不起作用，因为所有的中间结果将汇总到一个reduce上。

 分区内部数据是按key排序，所以reduce的结果文件中的数据是按key排序

