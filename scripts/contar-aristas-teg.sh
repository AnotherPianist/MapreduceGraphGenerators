
#!/bin/bash

for i in {0..9}; do
    hadoop fs -cat rmat_dst/part-0000$i | wc -l
done
for i in {10..19}; do
    hadoop fs -cat rmat_dst/part-000$i | wc -l
done