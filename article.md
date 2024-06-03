# Kubernetes Operator In Kotlin: Could You? Would You? Should You?

When software developers want to solve a problem, they write an application.
When they want to make that application available to other people, they host it somewhere and make it scalable, and possibly monetize it afterwards by making it paid or a subscription or what not. If they are lucky, that application can become a source of (conditionally) passive income.
But what if it's something useful but not sellable? Or if you don't want to do all this work about hosting, scaling and supporting it, but still want other developers to use your very reusable and undeniably cool application?

Well then. There's always an option of making it into a Kubernetes operator, right?

Right... maybe. Why Kubernetes, one may ask?

Kubernetes is an orchestration framework that allows one to spin up applications and services of their choosing, provided they can be run within a container. In fact, one doesn't need a Kubernetes operator to run an app on Kubernetes; one just needs a container image. Let's say we have a Docker image of our app, and we're hosting it in an AWS container registry; we can deploy it to a Kubernetes cluster, which can be running on EC2 instances or other compute resources.

It is a lot of work, though. Work that someone else probably won't want to repeat, just to evaluate if they need that application. A Kubernetes operator would help streamline that task, leaving less room for error. 

> A Kubernetes operator is a piece of software deployed to a Kubernetes cluster that defines and manages custom resources. Kubernetes Operators follow a specific pattern for managing complex applications. When a custom resource is created in the cluster, the operator uses the provided specification to configure an instance of the application. This specification is designed to be simpler and more straightforward for users. Custom resources abstract away the internal complexity, providing a simplified configuration interface while the operator manages the underlying resources.

## The Go Way

Wait, wait, says you, I am a Kotlin developer. Aren't most Kubernetes operators written in Go?
Indeed they are, because the Kubernetes itself is written in Go, and Go's concurrency model, performance characteristics, and general simplicity make it the ideal language for writing Kubernetes operators. The established ecosystem and tooling around Go in the Kubernetes community also play a significant role in its widespread adoption for this purpose. But... it is not the only option.





