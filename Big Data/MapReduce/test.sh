# @Zijiao: Run and test a mapreduce job; change file name and path for different test job


# Run these two command before attempting TitleCount Exercise to create input and output path
hadoop fs -mkdir -p /mp2/D-input
hadoop fs -mkdir -p /mp2/D-output

# Run all these codes everytime you make revisions to your JAVA file.
# The next four lines are from the tutorial
rm -rf ./build/* ./TopPopularLinks.jar
export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main TopPopularLinks.java -d build
jar -cvf TopPopularLinks.jar -C build/ ./
# Also clean up the output directory
hadoop fs -rm -r /mp2/D-output

hadoop jar TopPopularLinks.jar TopPopularLinks -D N=5 /mp2/links /mp2/D-output
# Execute this after the series of commands above, granted it did not return any errors.
# Show all the result of your code
#hadoop fs -cat /mp2/D-output/part*

# Show first 10 lines in descending order
hadoop fs -cat /mp2/D-output/part* | sort -n -k2 | head -n 10