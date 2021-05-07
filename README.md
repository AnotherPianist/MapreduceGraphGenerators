# Mapreduce Graph Generators

### Prerequisites 📋

The first thing you should do is download the executable files from the **jars** folder:
* To generate directed graphs use h4dg.jar.
* To generate undireted graphs use h4ug.jar.

To run the graph generators you need to have Hadoop MapReduce installed. This can be done locally (on your own pc) or if you prefer you can use services such as [Amazon EMR](https://aws.amazon.com/es/emr/?whats-new-cards.sort-by=item.additionalFields.postDateTime&whats-new-cards.sort-order=desc) or [Google Cloud Dataproc](https://cloud.google.com/dataproc/?hl=es-419).

**Note**: To install Hadoop locally you can follow the official tutorial: [Apache Hadoop](https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/SingleCluster.html) 😀.


## Usage ⚙️

### Directed Graph

Step by step to generate a **directed** graph of _n_ nodes.
1. Make sure to remove any _output_ from any previous run.

```
hadoop fs -rm -r /hrmat
```
2. Run the generator.
    * n: Number of nodes in the graph.
    * w: Number of worker nodes in the cluster.
```
hadoop jar h4dg.jar -n <n> -m <w>
```
### Undirected Graph

Step by step to generate a **undirected** graph of _n_ nodes.
1. Make sure to remove any _output_ from any previous run.

```
hadoop fs -rm -r /hrmat
```
2. Run the generator.
    * n: Number of nodes in the graph.
    * w: Number of worker nodes in the cluster.
```
hadoop jar h4ug.jar -n <n> -m <w>
```

## Wiki 📖

You can find much more about how to use this project on our [Wiki](https://github.com/FerLopezGallegos/MapreduceGraphGenerators/wiki)

---
⌨️ Plantilla de: [Villanuevand](https://github.com/Villanuevand) 😊
