# WPlaceholder
[![](https://jitpack.io/v/luiz-otavio/wisply-placeholder.svg)](https://jitpack.io/#luiz-otavio/wisply-placeholder)

Flexible library to replace placeholders inside messages/texts with Regex expressions.

## For what it's good for
Placeholder replacement is a common task in many languages. This library is a flexible and easy to use solution to replace texts/messages with new messages.

## Besides replacing placeholders
To understand how does it works, let's look at an example:
```java
public static void main(String[]args){
    String message = "Hello {name}, how are you?";
    String name = "John";
    String replacedMessage = message.replaceAll("\\{name\\}", name);
    System.out.println(replacedMessage);
}
```

It will print: `Hello John, how are you?`

The `\\{name\\}` is a placeholder. It's a special character that is used to replace a value.
That essentially means that the `\\{name\\}` will be replaced with the value of the `name` variable.

## Applying WPlaceholder
There are few ways to add WPlaceholder in your project like:

Gradle DSL:
```groovy
repositories() {
    maven {
        name = "jitpack"
        url = 'https://jitpack.io'
    }
}   

dependencies() {
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

## How to use it
```java
public class Main {
    public static void main(String[] args) {
         PlaceholderAPI.PlaceholderDelegator delegator = PlaceholderAPI.createDelegator('%');

         delegator.register(new VariablePlaceholder("%name%", "WizardBR_"));
         
         System.out.println(
           PlaceholderAPI.replace("Hello %name%!")
         );
    }
}
```
It will be: `Hello WizardBR_!`

## License
This project is licensed under the GNU General Public License v3.0.

## Contributing
Please, feel free to contribute!
