# notifier-packt

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4f44adffbcc347e4a50774dd89084a41)](https://app.codacy.com/app/Flashky/notifier-packt?utm_source=github.com&utm_medium=referral&utm_content=Flashky/notifier-packt&utm_campaign=Badge_Grade_Dashboard)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/08c6235b955540038929f66cccfb616e)](https://www.codacy.com/app/Flashky/notifier-packt?utm_source=github.com&utm_medium=referral&utm_content=Flashky/notifier-packt&utm_campaign=Badge_Coverage)

Spring Boot scheduled  service to check for free packt daily offers.

## Download
Download the latest version of the notifier-pack application:

PENDING

After you download it, you will see two files:
-   The notifier-pack jar file.
-   The application.yml configuration file.

## Customizing configuration
Notifier Packt is fully configurable by editing the external application.yaml file.

### Customizing SimpleJavaMail configuration
Notifier Packt uses SimpleJavaMail for sending the email notifications.

You can modify any of the SimpleJavaMail properties as you need:

<http://www.simplejavamail.org/#section-available-properties>

#### Customize encryption configuration

As exposing passwords in a properties file is unsecure, Notifier Packt provides an optional encryption mechanism using Jaspyt.

First, you will need to download the Jaspyt encrypt command line tool:
-   <http://www.jasypt.org/cli.html>
-   <http://www.jasypt.org/download.html>

Encrypt your email password using the command line tool

```bash
# For windows:
encrypt.bat input=<password_to_encrypt> password=<master_password_for_encryption>

# For unix:
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

And finally, add the encrypted password to the SimpleJavaMail configuration inside ENC():

```yaml
simplejavamail:
  ...
  smtp:
    ...
    password: ENC(your_encrypted_password)
    ...
```
### Customize crontab

Finally, you can customize at which time should the process be executed, just add the following property:

```yaml
service:
  cron: 0 0 4 * * ?
```

## How to execute

### Java execution
After you have configured any properties and environment variables, just launch it as:

```sh
java -jar notifier-packt.jar
```

### Docker execution
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
## License
Notifier Packt is Open Source software released under the Apache 2.0 license.

## Third Party Licenses
-   Icons by Icons8 (<https://icons8.com> | [License](https://icons8.com/license))
-   SimpleJavaMail (<http://www.simplejavamail.org> | [License](https://github.com/bbottema/simple-java-mail/blob/develop/LICENSE-2.0.txt) | [Notice](https://github.com/bbottema/simple-java-mail/blob/develop/NOTICE.txt))
