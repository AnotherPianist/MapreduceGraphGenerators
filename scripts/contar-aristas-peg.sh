
#!/bin/bash

for i in {0..9}; do
    hadoop fs -cat teg_rmat_graph/part-m-0000$i | wc -l
done

for i in {10..15}; do
    hadoop fs -cat teg_rmat_graph/part-m-000$i | wc -l
done
