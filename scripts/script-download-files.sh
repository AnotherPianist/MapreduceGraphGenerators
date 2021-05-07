#!/bin/bash

# D1
wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/D1-10K.tar
tar -xvf D1-10K.tar 
rm D1-10K.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/d1-10k.txt
rm -r out/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/D1-100K.tar
tar -xvf D1-100K.tar
rm D1-100K.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/d1-100k.txt
rm -r out/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/D1-1M.tar
tar -xvf D1-1M.tar 
rm D1-1M.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/d1-1m.txt
rm -r out/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/D1-10M.tar
tar -xvf D1-10M.tar 
rm D1-10M.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/d1-10m.txt
rm -r out/

# U3_3

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/U3-3-10K.tar
tar -xvf U3-3-10K.tar 
rm U3-3-10K.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/u3-3-10k.txt
rm -r out/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/U3-3-100K.tar
tar -xvf U3-3-100K.tar 
rm U3-3-100K.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/u3-3-100k.txt
rm -r out/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/U3-3-1M.tar
tar -xvf U3-3-1M.tar 
rm U3-3-1M.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/u3-3-1m.txt
rm -r out/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/U3-3-10M.tar
tar -xvf U3-3-10M.tar 
rm U3-3-10M.tar 
cat out/part-r-000* > /home/memoria_rmat2020/files/u3-3-10m.txt
rm -r out/

# PegasusN
wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/peg-10K.tar
tar -xvf peg-10K.tar 
rm peg-10K.tar
cat teg_rmat_graph/part-m-000* > /home/memoria_rmat2020/files/peg-10k.txt
rm -r teg_rmat_graph/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/peg-100K.tar
tar -xvf peg-100K.tar 
rm peg-100K.tar
cat teg_rmat_graph/part-m-000* > /home/memoria_rmat2020/files/peg-100k.txt
rm -r teg_rmat_graph/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/peg-1M.tar
tar -xvf peg-1M.tar 
rm peg-1M.tar
cat teg_rmat_graph/part-m-000* > /home/memoria_rmat2020/files/peg-1m.txt
rm -r teg_rmat_graph/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/peg-10M.tar
tar -xvf peg-10M.tar 
rm peg-10M.tar
cat teg_rmat_graph/part-m-000* > /home/memoria_rmat2020/files/peg-10m.txt
rm -r teg_rmat_graph/

#TegViz

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/teg-10K.tar
tar -xvf teg-10K.tar
rm teg-10K.tar
cat rmat_dst/part-000* > /home/memoria_rmat2020/files/teg-10k.txt
rm -r rmat_dst/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/teg-100K.tar.gz
tar -xzvf teg-100K.tar.gz
rm teg-100K.tar.gz
cat rmat_dst/part-000* > /home/memoria_rmat2020/files/teg-100k.txt
rm -r rmat_dst/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/teg-1M.tar.gz
tar -xzvf teg-1M.tar.gz
rm teg-1M.tar.gz
cat rmat_dst/part-000* > /home/memoria_rmat2020/files/teg-1m.txt
rm -r rmat_dst/

wget https://aws-logs-937850040142-us-east-2.s3.us-east-2.amazonaws.com/resultados-mapreduce/teg-10M.tar.gz
tar -xzvf teg-10M.tar.gz
rm teg-10M.tar.gz
cat rmat_dst/part-000* > /home/memoria_rmat2020/files/teg-10m.txt
rm -r rmat_dst/