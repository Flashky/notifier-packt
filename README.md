# notifier-packt

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4f44adffbcc347e4a50774dd89084a41)](https://app.codacy.com/app/Flashky/notifier-packt?utm_source=github.com&utm_medium=referral&utm_content=Flashky/notifier-packt&utm_campaign=Badge_Grade_Dashboard)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/08c6235b955540038929f66cccfb616e)](https://www.codacy.com/app/Flashky/notifier-packt?utm_source=github.com&utm_medium=referral&utm_content=Flashky/notifier-packt&utm_campaign=Badge_Coverage)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Spring Boot scheduled  service to check for free packt daily offers.

## Cloning and importing the project

 1.  Execute `git clone` to download the repository.
 2.  Once you have downloaded it, import it in your favourite IDE.
 3.  *OPTIONAL* Compile the code and obtain the executable jar.

## Configuration
Notifier Packt is fully configurable. You can do it in three different ways:

  - Editing the external `application.yaml` file.
  - Overriding properties via environment variables.
  - Overriding properties via program arguments.

### General properties
| Property                            | Values            | Default        | Description                                                  |
|-------------------------------------|-------------------|----------------|--------------------------------------------------------------|
| `app.lang`                          | `en`/`es`         | `en`           | Sets the language of the application                         |
| `app.schedule`                      | (cron expression) | `0 0 4 * * ?`  | Sets the time on which the app will look for new free offers |
| `app.notifications.twitter.enabled` | `true`/`false `   | `true`         | Enables or disables the twitter notifications                |
| `app.notifications.email.enabled`   |  `true`/`false`   | `true`         | Enables or disables email notifications                      |

#### Twitter specific properties
If twitter notifications are enabled, you will also have to configure the following properties:

|  Property                                  | Values               | Description                                   |
|--------------------------------------------|----------------------|-----------------------------------------------|
| `app.notifications.twitter.accessSecret`   | `<access_secret>`    | Enables or disables the twitter notifications |
| `app.notifications.twitter.accessKey`      | `<access_key>`       | Enables or disables email notifications       |
| `app.notifications.twitter.consumerKey`    | `<consumer_key>`     | Sets the master password for encryption       |
| `app.notifications.twitter.consumerSecret` | `<consumer_secret>`  | Sets the master password for encryption       |

#### Mail specific properties
If email notifications are enabled, you will need to configure the following `SimpleJavaMail` properties as well:

<http://www.simplejavamail.org/#section-available-properties>

#### Encryption specific properties
You can use `Jasypt` to encrypt any password you want to secure. Then, you can set the encryption master password using the following property:

| Property                    | Values              | Description                             |
|-----------------------------|---------------------|-----------------------------------------|
| `jasypt.encryptor.password` | `<master_password>` | Sets the master password for encryption |

Any encrypted property password must follow the format: 
`ENC(encrypted_password)`

Example:
`app.notifications.twitter.accessSecret = ENC(my-encrypted-password)`

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
  - Icons by Icons8 (<https://icons8.com> | [License](https://icons8.com/license))
  - SimpleJavaMail (<http://www.simplejavamail.org> | [License](https://github.com/bbottema/simple-java-mail/blob/develop/LICENSE-2.0.txt) | [Notice](https://github.com/bbottema/simple-java-mail/blob/develop/NOTICE.txt))
  - Emoji-Java (<https://github.com/vdurmont/emoji-java> | [License](https://github.com/vdurmont/emoji-java/blob/master/LICENSE.md))
