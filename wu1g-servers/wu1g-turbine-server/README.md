turbine是聚合服务器发送事件流数据的一个工具，hystrix的监控中，只能监控单个节点，实际生产中都为集群，因此可以通过 
turbine来监控集群下hystrix的metrics情况，通过eureka来发现hystrix服务。