# Spring Academy OAuth 2.0 REST API Security Course

This repository represents the structure of
the [real-world simulated project](https://github.com/vmware-tanzu-learning/spring-academy/blob/main/docs/lab-authoring-style-guide.md#remember-youre-building-a-real-application)
used to teach Spring Academy learners concepts taught in a course.
This repo is a companion to
the [Spring Academy OAuth 2.0 REST API Security Course repository](https://github.com/vmware-tanzu-learning/course-secure-rest-api-oauth2).

### Branches and environments

- `main` - Commits to this branch will automatically be deployed to Spring Academy **Staging**
- `prod` - Commits will be used for Spring Academy. Deploys are a manual process at this time.

## Documentation

Learn more about building courses and labs in
the [Spring Academy Contribution Guide](https://github.com/vmware-tanzu-learning/spring-academy/blob/main/CONTRIBUTING.md).

### Running the Authorization Server

```shell
docker run --rm --name sso -p 9000:9000 ghcr.io/vmware-tanzu-learning/course-secure-rest-api-oauth2-code/sso:latest
```

### Tocken Request

```shell
#http -a <client_id>:<client_secret> --form <auth_server_endpoint> grant_type=<the_grant_type> scope=<scopes>

http -a cashcard-client:secret --form :9000/oauth2/token grant_type=client_credentials scope=cashcard:read
```

### GET All Cash Cards

```shell
http :8080/cashcards -A bearer -a $TOKEN
```

### POST a cashcard

```shell
http POST :8080/cashcards amount=50.89 owner=sarah1 -A bearer -a $TOKEN 
```
