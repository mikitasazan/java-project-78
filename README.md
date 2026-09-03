# Валидатор данных (Java)

[![hexlet-check](https://github.com/mikitasazan/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/mikitasazan/java-project-78/actions)
[![Build](https://github.com/mikitasazan/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/mikitasazan/java-project-78/actions)

Создание собственной библиотеки для проверки корректности (валидации) данных – отличный способ прокачать навыки проектирования кода, в особенности, объектно-ориентированной архитектуры. Создание правильных иерархий классов, расширяемая архитектура, применение принципов SOLID, использование fluent-интерфейса – все это предстоит делать в проекте

Учебный проект Хекслета: https://ru.hexlet.io/programs/java
Как это должно работать: https://asciinema.org/a/NtQ6xBownxYFN2H8WEffvtcS1

## Стек

- Java 21
- Gradle
- JUnit 5

## Установка

```bash
git clone https://github.com/mikitasazan/java-project-78.git
cd java-project-78/app
make build
```

## Использование

Библиотека проверяет строки, числа и объекты `Map` (включая проверку значений
по ключам через `shape()`) через fluent-интерфейс:

```java
import hexlet.code.Validator;

var v = new Validator();
var schema = v.string().required().minLength(5).contains("hex");

schema.isValid("hexlet"); // true
schema.isValid("java");   // false
```

Запустить тот же пример:

```bash
cd app
make run
```

Прогнать тесты:

```bash
cd app
make test
```

---

<details>
<summary>Автоматические тесты Хекслета</summary>

Тесты запускаются на каждый коммит. За запуск отвечает файл `.github/workflows/hexlet-check.yml` — не удаляйте и не переименовывайте ни его, ни репозиторий.

</details>

## О Хекслете

[Хекслет](https://ru.hexlet.io/) — школа программирования: авторские программы обучения с практикой, поддержкой наставников и реальными проектами, которые остаются в резюме. Этот репозиторий — один из таких проектов.
