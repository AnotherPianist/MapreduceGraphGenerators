
directorio <- dir('/home/memoria_rmat2020/files', recursive = FALSE)

for (f in directorio) {
    # Procesar el archivo f
    print(f)
    w <- unlist(strsplit(f,"\\."))[1]
    cat("---------------")
    cat(f)
    cat("---------------\n")
    #leer grafo desde archivo
    f <- paste("/home/memoria_rmat2020/files",f,sep="/")
    print(f)

    graph<-read.graph(f,format="edgelist")
    print(graph)
    # calculate degree
    d = degree(graph, mode = "all")
    dd = degree.distribution(graph, mode = "all", cumulative = FALSE)

    #data <- data[data > 0]
    fit <- power.law.fit(dd, implementation = "plfit")
    print(fit)

    degree = 1:max(d)
    probability = dd[-1]

    # delete blank values
    nonzero.position = which(probability != 0)
    probability = probability[nonzero.position]
    degree = degree[nonzero.position]
    reg = lm(log(probability) ~ log(degree))
    cozf = coef(reg)
    power.law.fit = function(x) exp(cozf[[1]] + cozf[[2]] * log(x))
    alpha = -cozf[[2]]
    R.square = summary(reg)$r.squared

    fn = paste(w, "png", sep=".")
    png(filename=fn)

    # plot
    plot(probability ~ degree, log = "xy", xlab = "Degree (log)", ylab = "Probability (log)", 
    col = 1, main = "Degree Distribution")
    curve(power.law.fit, col = "red", add = T, n = length(d))

    dev.off()
    rm(list=ls())

}
