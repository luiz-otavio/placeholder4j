# <p align="center">Placeholder4j</p>
<a align="center">[![](https://jitpack.io/v/luiz-otavio/wisply-placeholder.svg)](https://jitpack.io/#luiz-otavio/wisply-placeholder)
[![](https://jitpack.io/v/luiz-otavio/wisply-placeholder/month.svg)](https://jitpack.io/#luiz-otavio/wisply-placeholder)
[![](https://jitpack.io/v/luiz-otavio/wisply-placeholder/week.svg)](https://jitpack.io/#luiz-otavio/wisply-placeholder)
</a>

## Beside of understanding how placeholders are important: </p>
Constantly we need to replace some values in a string. For example, if we have a message like this: `Hello {name}, how are you?` 
and we want to replace the `{name}` with a value, we can use the `replaceAll` method from the `String` class.
```java
public static void main(String[]args){
    String message = "Hello {name}, how are you?";
    String name = "John";
    String replacedMessage = message.replaceAll("\\{name\\}", name);
    System.out.println(replacedMessage);
}
```

But, if we have multiple placeholders, we need to call the `replaceAll` method multiple times which is not a good practice.
```java
public static void main(String[]args){
    String message = "Hello {name}, how are you? I'm {age} years old.";
    String name = "John";
    String age = "20";
    String replacedMessage = message.replaceAll("\\{name\\}", name).replaceAll("\\{age\\}", age);
    System.out.println(replacedMessage);
}
```

## How Placeholder4j can help you
Placeholder4j is a library that helps you to replace placeholders in a string. It is simple to use and you can replace multiple placeholders in a single call.
```java
public static void main(String[]args){
    PlaceholderAPI.PlaceholderDelegator delegator = PlaceholderAPI.createDelegator('%');
    // Registering a new placeholder for the name
    delegator.register(new VariablePlaceholder("%name%", "John"));
    // Registering a new placeholder for the age
    delegator.register(new VariablePlaceholder("%age%", "20"));
    
    // Calling PlaceholderAPI to replace it.
    System.out.println(
        PlaceholderAPI.replace("Hello %name%, how are you? I'm %age% years old.")
    );
}
```
Calling it, the output will be: `Hello John, how are you? I'm 20 years old.`

## How to install
Groovy DSL:
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.luiz-otavio:placeholder4j:{PROJECT_VERSION}'
}
```

Kotlin DSL:
```kotlin
repositories() {
    maven("https://jitpack.io")
}

dependencies() {
    implementation("com.github.luiz-otavio:placeholder4j:{PROJECT_VERSION}")
}
```

Maven:
```xml
<repositories>
    <repository>
        <id>jitpack</id>
        <name>jitpack</name>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.luiz-otavio</groupId>
        <artifactId>placeholder4j</artifactId>
        <version>{PROJECT_VERSION}</version>
    </dependency>
</dependencies>
```

## License
This project is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html).

## Contributing
Open an issue or a pull request and let's discuss about it.
