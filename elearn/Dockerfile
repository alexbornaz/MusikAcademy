FROM node:18.14.2-alpine3.17
RUN mkdir -p /app/client
WORKDIR /app/client
COPY package.json /app/client
COPY package-lock.json /app/client

RUN npm install

COPY . /app/client

CMD ["npm", "start"]