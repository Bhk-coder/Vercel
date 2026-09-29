FROM ubuntu:focal
LABEL authors="bhavi_hark6y2"

ENV DEBIAN_FRONTEND=nonintercative

RUN apt-get update
RUN apt-get install -y curl
RUN curl -sL https://deb.nodesource.com/setup_20.x | bash -
RUN apt-get upgrade -y
RUN apt-get install -y nodejs

RUN apt-get install git -y
RUN apt-get install -y awscli

WORKDIR /home/app

COPY main.sh main.sh

ENTRYPOINT ["/home/app/main.sh"]