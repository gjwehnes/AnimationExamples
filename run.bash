echo "compile"
javac -classpath . utilities/*.java backgrounds/*.java animations/*.java sprites/*.java universes/*.java  gui/*.java main/*.java -d ./bin
echo "run Examples.java"
java -classpath ./bin Examples
