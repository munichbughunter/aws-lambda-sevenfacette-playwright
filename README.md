# aws-lambda-sevenfacette-playwright
An example project for running 7Facette and Playwright on AWS Lambda using custom container images as Lambda functions

## Prerequisite

* [Docker](https://www.docker.com/)
* [AWS Serverless Application Model](https://aws.amazon.com/serverless/sam/) installed and configured for deploying to AWS

## Building

The AWS SAM command will build the Docker image for us!

``` console
sam build
```

You can see the docker build steps in the console output. 

## Running locally

A genuinely nice aspect of the AWS SAM is the ability to run the API locally.

```console
sam local start-api
```

Invoke the function with HTTP request. This is necessary because we expect a POST request including JSON body. This triggers the function, and we’ll get the output what we would see in the CloudWatch logs.

```console
curl -XPOST "http://127.0.0.1:3000/test" -d '{"package": "de.aws.api", "class": "", "method": ""}'
```

## Deploying to AWS Lambda

Finally, we can have AWS SAM create our Lambda and connect it to a REST API for us! 🚀

```console
sam deploy --guided
```
