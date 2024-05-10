# EphemeralKibanaOperator

This operator is responsible for managing the lifecycle of Kibana instances in a Kubernetes cluster. It is based on the [Java Operator SDK](https://javaoperatorsdk.io/docs/getting-started) and uses the [Spring Boot Starter for this SDK](https://mvnrepository.com/artifact/io.javaoperatorsdk/operator-framework-spring-boot-starter/).

## The idea

Running Kibana all the time is expensive and if it is not used constantly, it might be a good idea to have a way to create and destroy Kibana instances on demand. This operator is responsible for that. Another idea is to simplify the process of creating Kibana instances by providing a custom resource definition (CRD) that can provide a simpler facade to creating Kibana instances. Kibana has quite a lot of parameters that can be configured, but if we assume an internal Kibana instance that is not exposed to the internet, we can simplify the configuration, for example skipping the authentication and authorization parts. Since this Kibana is not supposed to run all the time, it also does not need to be highly available, so we can skip the clustering part, and add functionality to clean up internal Kibana indices when it shuts down.

## The resources to create

The operator will create the following resources:

- An `EphemeralKibana` custom resource - the parent resource itself
- A `Deployment` for the Kibana instance - this one specifies the Kibana image to use, the environment variables to set etc.
- A `Service` for the Kibana instance - this one specifies the ports to expose.
- `Pod` resources - the actual Kibana instances.


