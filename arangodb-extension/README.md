# ArangoDB-extension

![ArangoDB Project](https://jnosql.github.io/img/logos/ArangoDB.png)


ArangoDB extension has implementations to use specific behavior that is beyond the API such as AQL.

## Set dependency


```xml

  <dependency>
     <groupId>org.jnosql.artemis</groupId>
     <artifactId>aragangodb-extension</artifactId>
     <version>${jnosql.version}</version>
  </dependency>
```

## Make ArangoDB manager available to container

```java

public class ArangoDBProducer {


    @Produces
    public ArangoDBDocumentCollectionManager getManager() {
        ArangoDBDocumentCollectionManager manager = ...;
        return manager;
    }

    @Produces
    public ArangoDBDocumentCollectionManagerAsync getManagerAsync() {
        ArangoDBDocumentCollectionManagerAsync managerAsync = ...;
        return managerAsync;
    }
}


```

## ArangoDBRepository

ArangoDBRepository is an extension of Repository that allows using AQL annotation that executes AQL.


```java
    interface PersonRepository extends ArangoDBRepository<Person, String> {

        @AQL("FOR p IN Person RETURN p")
        List<Person> findAll();

        @AQL("FOR p IN Person FILTER p.name = @name RETURN p")
        List<Person> findByName(@Param("name") String name);
    }
```

## ArangoDBRepositoryAsync

ArangoDBRepositoryAsync is an extension of RepositoryAsync that allows using AQL annotation that executes AQL.


```java
    interface PersonAsyncRepository extends ArangoDBRepositoryAsync<Person, String> {

        @AQL("FOR p IN Person FILTER p.name = @name RETURN p")
        void queryName(@Param("name") String name);

        @AQL("FOR p IN Person FILTER p.name = @name RETURN p")
        void queryName(@Param("name") String name, Consumer<List<Person>> callBack);
    }
```


## ArangoDBTemplate and ArangoDBTemplateAsync

ArangoDBTemplate is a specialization of Document Template that allows using AQL both synchronous and asynchronous.

```java
        List<Person> people = template.aql("FOR p IN Person FILTER p.name = @name RETURN p", params);

        String query = "FOR p IN Person FILTER p.name = @name RETURN p";
        Consumer<List<Person>> callBack = p -> {
        };
        Map<String,Object> params = Collections.singletonMap("name", "Ada");
        templateAsync.aql(query, params, callBack);

```
