# notifier-packt
Spring Boot scheduled  service to check for free packt daily offers.

# Download
Download the latest version of the notifier-pack application:


# Customizing configuration
Notifier Packt is fully configurable using either a YAML or an external properties file.

The properties file must be named as application and the extension:
* application.yml
* application.properties

And it must be placed in the same folder as the jar file.

## Customizing SimpleJavaMail configuration

Notifier Packt uses SimpleJavaMail for sending the email notifications.

You can fully modify any of the SimpleJavaMail properties as you need:

http://www.simplejavamail.org/#section-available-properties



## Customize encryption configuration

As adding passwords is unsecure, Notifier Packt provides an encryption mechanism using Jaspyt.

First, you would need to encrypt a password using the Jaspyt command line tools and a master password:
* http://www.jasypt.org/cli.html
* http://www.jasypt.org/download.html

Command line (Windows):
```bat
encrypt.bat input=<password_to_encrypt> password=<master_password_for_encryption>
```

Command line (Unix):
```bash
./encrypt.sh input=<password_to_encrypt> password=<master_password_for_encryption>
```

Now add the master password to the external configuration file:
```yaml
jasypt:
  encryptor:
    password: master_password_for_encryption
```

You could also add it as an environment variable, and just refer to it:
```yaml
jasypt:
  encryptor:
    password: ${MASTER_PASSWORD_ENVIRONMENT_VARIABLE}
```

And finally, add the encrypted password at the SimpleJavaMail configuration inside ENC():

```yaml
simplejavamail:
  ...
  smtp:
    ...
    password: ENC(your_encrypted_password)
    ...
```
## Customize crontab

Finally, you can customize at which time should the process be executed, just add the following property:

```yaml
service:
  cron: 0 0 4 * * ?
```

# How to execute

## Java execution
After you have configured any properties and environment variables, just launch it as:

```sh
java -jar notifier-packt.jar
```


## Docker execution
A Dockerfile is provided to run Notifier Packt as a Docker container.

In order to build and execute the container, just modify and execute the following commands:

Build:
```sh
docker image build -t flashk/notifier-packt:<version> .
```

Run:
```sh
docker container run -d flashk/notifier-packt:<version>
```  
