echo "**************   10000  **************"
hadoop fs -rm -r teg_rmat_graph
hadoop jar PegasusN.jar 18 65250 16 0.5 0.2 0.2 0.1 0.1 teg_rmat_graph
./contar-aristas-peg.sh

echo "**********************************"
echo

echo "**************   100000  **************"
hadoop fs -rm -r teg_rmat_graph
hadoop jar PegasusN.jar 18 806009 16 0.5 0.2 0.2 0.1 0.1 teg_rmat_graph
./contar-aristas-peg.sh
echo "**********************************"
echo

echo "**************   1000000  **************"
hadoop fs -rm -r teg_rmat_graph
hadoop jar PegasusN.jar 18 9595150 16 0.5 0.2 0.2 0.1 0.1 teg_rmat_graph
./contar-aristas-peg.sh
echo "**********************************"
echo

echo "**************   10000000  **************"
hadoop fs -rm -r teg_rmat_graph
hadoop jar PegasusN.jar 18 111302071 16 0.5 0.2 0.2 0.1 0.1 teg_rmat_graph
./contar-aristas-peg.sh
echo "**********************************"
echo

echo "**************   100000000  **************"
hadoop fs -rm -r teg_rmat_graph
hadoop jar PegasusN.jar 18 1266526383 16 0.5 0.2 0.2 0.1 0.1 teg_rmat_graph
./contar-aristas-peg.sh
echo "**********************************"
echo