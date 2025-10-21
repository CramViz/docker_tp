FROM httpd:2.4-alpine

# Activer les modules nécessaires
RUN sed -i 's/#LoadModule proxy_module/LoadModule proxy_module/' /usr/local/apache2/conf/httpd.conf && \
    sed -i 's/#LoadModule proxy_http_module/LoadModule proxy_http_module/' /usr/local/apache2/conf/httpd.conf

# Inclure notre conf de vhost
RUN echo 'Include conf/extra/httpd-vhosts.conf' >> /usr/local/apache2/conf/httpd.conf

# Copier nos fichiers
COPY httpd-vhosts.conf /usr/local/apache2/conf/extra/httpd-vhosts.conf
COPY index.html /usr/local/apache2/htdocs/index.html
