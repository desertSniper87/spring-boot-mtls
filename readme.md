# Spring Boot Test MTLS Project

## To Build

```bash
mvn clean install -DskipTests=true
```

## To Run

```bash
mvn spring-boot:run
```

## Using nGinx as a passthrough

Please change `/etc/nginx/nginx.conf` to use nginx as a ssl upstream proxy.

Add the following lines after `http` block. 

```nginx
stream {
    upstream spring-boot {
        server localhost:8081;
    }
    server {
        listen 443;
        proxy_pass spring-boot;
    }
} 
```

## Java Version

I used Java 11 for the project. Please change `pom.xml` to change java version. 

# Using openssl to generate certificates

## Step 1 - Generate certificate authority using [[openssl]]

### Ext File (v3.ext)

```yaml

authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names

[alt_names]
DNS.1 = bcc-ca-website.org


```


### Generate CA certificate

Here, Common name is BCC

```bash
openssl req \
  -new \
  -x509 \
  -nodes \
  -newkey rsa:2048\
  -days 365 \
  -subj '/CN=bcc' \
  -keyout ca.key \
  -out ca.crt
```

#### Generate CA certificate for client

```bash
openssl req \
  -new \
  -x509 \
  -nodes \
  -newkey rsa:2048\
  -days 365 \
  -subj '/CN=bcc-client' \
  -keyout ca-client.key \
  -out ca-client.crt
```

### Generate Server Certificate

#### Generate Server Key

```bash
openssl genrsa \
  -out server.key 2048
```

### Generate CSR

Here common name is localhost

```bash
openssl req \
  -new \
  -key server.key \
  -subj '/CN=bcc-ca-website.org' \
  -out server.csr
```

### Generate Signed Certificate

```bash
openssl x509 \
  -req \
  -in server.csr \
  -CA ca.crt \
  -CAkey ca.key \
  -CAcreateserial \
  -days 365 \
  -extfile v3.ext \
  -out server.crt
```

### Generate Client Certificate


#### Generate Client Key

```bash
openssl genrsa \
  -out client.key 2048
```

### Generate CSR

Here common name is client's name

```bash
openssl req \
  -new \
  -key client.key \
  -subj '/CN=torsho' \
  -out client.csr
```

### Generate Signed Certificate

```bash
openssl x509 \
  -req \
  -in client.csr \
  -CA ca-client.crt \
  -CAkey ca-client.key \
  -CAcreateserial \
  -days 365 \
  -out client.crt

