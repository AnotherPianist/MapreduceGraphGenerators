#!/bin/bash

hadoop fs -rm -r teg_rmat_graph
hadoop jar PegasusN.jar 18 1266526383 8 0.5 0.2 0.2 0.1 0.1 teg_rmat_graph
hadoop fs -get teg_rmat_graph
tar -cvf peg-100M.tar teg_rmat_graph/
rm -r teg_rmat_graph/
aws s3 cp peg-100M.tar s3://aws-logs-937850040142-us-east-2/resultados-mapreduce/peg-100M.tar

hadoop fs -rm -r teg_rmat_graph
hadoop fs -rm -r /hrmat
hadoop jar HRmatU_3_3.jar -n 100000000 -m 8
hadoop fs -get /hrmat/out
tar -cvf U3-3-100M.tar out/
rm -r out/
aws s3 cp U3-3-100M.tar s3://aws-logs-937850040142-us-east-2/resultados-mapreduce/U3-3-100M.tar

hadoop fs -rm -r teg_rmat_graph
hadoop fs -rm -r /hrmat
hadoop jar HRmatD_1_1.jar -n 100000000 -m 8
hadoop fs -get /hrmat/out
tar -cvf D1-1-100M.tar out/
rm -r out/
aws s3 cp D1-1-100M.tar s3://aws-logs-937850040142-us-east-2/resultados-mapreduce/D1-1-100M.tar

hadoop fs -rm -r /hrmat
./teg_rmat.sh 100000000 1266526383 8 0.5 0.2 0.2 0.1 0
hadoop fs -get rmat_dst
tar -czvf teg-100M.tar.gz rmat_dst/
rm -r rmat_dst/
aws s3 cp teg-100M.tar.gz s3://aws-logs-937850040142-us-east-2/resultados-mapreduce/teg-100M.tar.gz

