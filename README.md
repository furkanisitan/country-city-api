# country-city-api
An example RESTful API that manages countries, cities and languages for Spring Boot.

- applicationUrl: `http://localhost:8080`

## Requirements

* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

> The JAVA_HOME environment variable must be set. [see](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/furkanisitan/country-city-api.git
```

**2. Navigate to the root folder of the project.**

**3. Run the following command.**

MacOS / Linux:
```bash
./mvnw spring-boot:run
```

Windows:
```bash
mvnw spring-boot:run
```
The app will start running at `http://localhost:8080`

## Documentation

You can find the API documentation [here](https://editor.swagger.io/?url=https://raw.githubusercontent.com/furkanisitan/country-city-api/main/docs/api-docs.yaml).

Also, after running the project, the Swagger UI page will then be available at `http://localhost:8080/swagger-ui/index.html`.

## Filtering, Paging and Sorting

Some endpoints get optional query parameters for filtering, paging and sorting. 
How to use these query parameters is explained below.

### Filtering

The following query parameter is used for filtering.

- `filter: array[string]`

A single filter text consists of field name, filter operator and value information. The field name is added first, followed by the filter operator and then the value.

`{fieldName}{filterOperator}{value}`

###### Filter Operators

The following characters are used to specify the query operator of the filtering.

- `:` &nbsp; -> equals
- `!:` -> not equals
- `:%` -> starts with
- `%:` -> ends with
- `%` &nbsp; -> contains
- `>` &nbsp; -> greater than
- `<` &nbsp; -> less than
- `>:` -> greater than or equals
- `<:` -> less than or equals

###### Samples

- `name%a,population>300000`
- `continent:EUROPE<t,name%f`

> If more than one filter expression is given these expressions are combined with the `and` operator.

> The `:%`, `%:` and `%` operators only support string fields.

### Paging

The following two query parameters are used for paging.

- `page: integer($int32) - default: 0`
- `size: integer($int32) - default: 20`

Paging is performed when at least one of the parameters is given. For the parameter not given its default value is used. No paging is performed if both parameters are null.

### Sorting

The following query parameter is used for sorting.

- `sort: array[string]`

A single sort text consists of direction and field name information. At the beginning is the direction then the field name is added. The default is used if direction is not specified.

`{direction}{fieldName}`

###### Direction Operators

The following characters are used to specify the direction of the sorting.

- `+` -> ascending (default)
- `-` -> descending

###### Samples

- `+name,-code`
- `name,-population`

>  The first valid expression is used if the same field name is given more than once.

## Author

**Furkan Işıtan**

* [github/furkanisitan](https://github.com/furkanisitan)
