FROM mcr.microsoft.com/playwright/java:focal

ARG SCRATCH_DIR=/opt/app

RUN mkdir ${SCRATCH_DIR}
RUN mkdir ./build
RUN mkdir ./de
RUN mkdir ./de/aws
RUN mkdir /opt/app/ms-playwright

COPY src/ src/
COPY gradle/ gradle/
COPY build.gradle gradlew ./
COPY gradle/lambda-build-init.gradle /opt/app
COPY settings.gradle ./

RUN ./gradlew --project-cache-dir $SCRATCH_DIR/gradle-cache -Dsoftware.amazon.aws.lambdabuilders.scratch-dir=$SCRATCH_DIR --init-script $SCRATCH_DIR/lambda-build-init.gradle build

RUN cp -r /opt/app/*/build/libs/* /opt/app
RUN cp -r $SCRATCH_DIR/*/build/distributions/lambda-build/META-INF /opt/app
RUN cp -r $SCRATCH_DIR/*/build/distributions/lambda-build/de/* /opt/app
RUN cp -r /ms-playwright/* /opt/app/ms-playwright/

RUN rm -rf /ms-playwright/
RUN rm -rf $SCRATCH_DIR/*/build/distributions/lambda-build/de/
RUN rm -rf $SCRATCH_DIR/*/build/distributions/lambda-build/META-INF
RUN rm -rf /opt/app/*/build/libs/

ENTRYPOINT [ "/usr/bin/java", "-cp", "/opt/app/*", "com.amazonaws.services.lambda.runtime.api.client.AWSLambda" ]

CMD ["de.aws.App::handleRequest"]
