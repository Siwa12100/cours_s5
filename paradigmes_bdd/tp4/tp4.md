# TP - Analyse des Ventes avec Hadoop

Le fichier `sales.csv` contient plusieurs colonnes, dont celles qui nous intéressent sont :
- **Région** : Région du monde
- **Pays** : Pays où la vente a eu lieu
- **Item Type** : Type de produit vendu
- **Sales Channel** : Canal de vente (en ligne ou hors ligne)
- **Profit Total** : Profit généré par la vente

## 1. Exécution avec Hadoop

### Code du fichier `WCount.java`

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCount {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("arg", args[args.length - 1]);
        
        String[] ourArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        Job job = Job.getInstance(conf, "Profit Example");

        job.setJarByClass(WCount.class);
        job.setMapperClass(WCountMap.class);
        job.setReducerClass(WCountReduce.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(ourArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(ourArgs[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

### Explication

- Ce fichier principal configure et exécute le programme Hadoop en spécifiant le **Mapper** et le **Reducer**.
- Il prend en entrée le fichier CSV et crée un fichier de sortie contenant les résultats.
- R remplacer `WCountMap` et `WCountReduce` par les classes Map et Reduce respectives selon la tâche demandée.


## Q1 : Calcul du bénéfice total par région

### `WCountMap.java` (Mapper)

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCountMap extends Mapper<Object, Text, Text, Text> {
    private String parameter;

    protected void setup(Context context) {
        parameter = context.getConfiguration().get("arg");
    }

    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (!fields[0].equals("Region") && fields[0].equals(parameter)) {
            context.write(new Text(fields[0]), new Text(fields[13]));
        }
    }
}
```

### `WCountReduce.java` (Reducer)

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCountReduce extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double totalProfit = 0;
        for (Text val : values) {
            totalProfit += Double.parseDouble(val.toString());
        }
        context.write(key, new Text("Total Profit: " + totalProfit));
    }
}
```

### Commandes Hadoop

```bash
hadoop jar wcount.jar demo.WCount sales.csv output q1_region
```

---

## Q2 : Calcul du bénéfice total par pays

### `WCountMap.java` (Mapper)

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCountMap extends Mapper<Object, Text, Text, Text> {
    private String parameter;

    protected void setup(Context context) {
        parameter = context.getConfiguration().get("arg");
    }

    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (!fields[1].equals("Country") && fields[1].equals(parameter)) {
            context.write(new Text(fields[1]), new Text(fields[13]));  // Récupère le profit total
        }
    }
}
```

### Commandes Hadoop

```bash
hadoop jar wcount.jar demo.WCount sales.csv output q2_pays
```

---

## Q3 : Calcul du bénéfice total par article

### `WCountMap.java` (Mapper)

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCountMap extends Mapper<Object, Text, Text, Text> {
    private String parameter;

    protected void setup(Context context) {
        parameter = context.getConfiguration().get("arg");
    }

    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (!fields[2].equals("Item Type") && fields[2].equals(parameter)) {
            context.write(new Text(fields[2]), new Text(fields[13]));  // Récupère le profit total
        }
    }
}
```

### Commandes Hadoop

```bash
hadoop jar wcount.jar demo.WCount sales.csv output q3_article
```

---

## Q4 : Nombre de ventes en ligne et hors ligne

### `WCountMap.java` (Mapper)

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCountMap extends Mapper<Object, Text, Text, Text> {
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (!fields[2].equals("Item Type")) {
            String salesChannel = fields[3].equals("Online") ? "Online" : "Offline";
            context.write(new Text(fields[2]), new Text(salesChannel + "," + fields[13]));  // Canal de vente + profit
        }
    }
}
```

### `WCountReduce.java` (Reducer)

```java
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCountReduce extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int onlineSales = 0, offlineSales = 0;
        double onlineProfit = 0.0, offlineProfit = 0.0;

        for (Text val : values) {
            String[] fields = val.toString().split(",");
            if (fields[0].equals("Online")) {
                onlineSales++;
                onlineProfit += Double.parseDouble(fields[1]);
            } else {
                offlineSales++;
                offlineProfit += Double.parseDouble(fields[1]);
            }
        }

        context.write(key, new Text("Online Sales: " + onlineSales + ", Offline Sales: " + offlineSales +
                ", Online Profit: " + onlineProfit + ", Offline Profit: " + offlineProfit));
    }
}
```

### Commandes Hadoop

```bash
hadoop jar wcount.jar demo.WCount sales.csv output q4_sales
```

---

## Q5 : Amélioration avec bénéfice en ligne et hors ligne

Il suffit d'utiliser les fichiers Mapper et Reducer de la question 4, car ils incluent déjà le calcul du bénéfice pour les ventes en ligne et hors ligne.

### Commandes Hadoop

```bash
hadoop jar wcount.jar demo.WCount sales.csv output q5_benefices
```