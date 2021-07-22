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
