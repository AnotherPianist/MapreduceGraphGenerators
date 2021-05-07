#!/bin/bash
function how_to_use {
  echo "ERROR"
  echo "$0 <workers>"
  exit 1
}


if (($# != 1)) 
then
  how_to_use
fi

workers=$1
echo "**********************************"
echo
declare -a n=("10000" "100000" "1000000" "10000000" "100000000")
for v_n in ${n[@]};do
   echo "---" $v_n "---"
   for i in {1..3}; do
      echo "++++++++ "$i "++++++++"
   # Remove output files on HDFS
      hadoop fs -rm -r /hrmat
      hadoop jar HRmatU_3_3.jar -n $v_n -m $workers
   done
done
echo "**********************************"
echo
