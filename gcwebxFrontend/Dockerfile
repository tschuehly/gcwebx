FROM node:14.7.0-alpine3.10 AS build
WORKDIR /usr/src/gcwebxFrontend
COPY package.json package-lock.json /usr/src/gcwebxFrontend/
RUN npm install -d
COPY . /usr/src/gcwebxFrontend/
RUN npm run build:app --prod --aot=true --buildOptimizer=true
#RUN node --max_old_space_size=4192 ./node_modules/@angular/cli/bin/ng build --prod --aot=true --buildOptimizer=true

FROM nginx:1.19.1-alpine AS prod
COPY --from=build /usr/src/gcwebxFrontend/dist/* /usr/share/nginx/html/
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/gcwebx.conf
EXPOSE 80

CMD ["nginx", "-g","daemon off;"]
