# Инструкция по настройке приложения

## Подготовка среды разработки

1. Установите Android Studio последней версии
2. Установите Java Development Kit (JDK) 11 или выше
3. Настройте Android SDK для API level 23 и выше
4. Убедитесь, что у вас установлен Git

## Настройка проекта

1. Клонируйте репозиторий:
```bash
git clone https://github.com/RVL-Aini/Monitoring.git
```

2. Откройте Android Studio и импортируйте проект:
   - Выберите "Open an existing Android Studio project"
   - Укажите путь к склонированному репозиторию
   - Дождитесь завершения синхронизации Gradle

3. Настройка SDK:
   - Откройте SDK Manager в Android Studio
   - Установите Android SDK Platform 23 или выше
   - Установите Android Build Tools
   - Установите Android Emulator (если планируете использовать эмулятор)

4. Настройка эмулятора (опционально):
   - Откройте AVD Manager
   - Создайте новое виртуальное устройство
   - Выберите образ системы (рекомендуется API 23 или выше)

5. Сборка проекта:
```bash
./gradlew build
```

6. Запуск тестов:
```bash
./gradlew test
```

## Настройка окружения для разработки

### Рекомендуемые плагины Android Studio:
- Kotlin
- Android WiFi ADB
- Android Butterknife Zelezny
- Key Promoter X

### Настройка code style:
1. Импортируйте настройки code style из `.idea/codeStyles`
2. Включите автоматическое форматирование кода перед коммитом

### Переменные окружения:
1. JAVA_HOME - путь к установленной JDK
2. ANDROID_HOME - путь к Android SDK

## Решение проблем

### Общие проблемы:

1. Ошибка "SDK location not found":
   - Создайте файл `local.properties`
   - Укажите путь к Android SDK: `sdk.dir=/path/to/Android/Sdk`

2. Ошибка сборки Gradle:
   - Выполните `./gradlew clean`
   - Попробуйте "Invalidate Caches / Restart" в Android Studio

3. Проблемы с зависимостями:
   - Выполните "Sync Project with Gradle Files"
   - Проверьте подключение к интернету
   - Очистите кэш Gradle: `./gradlew cleanBuildCache`

4. Ошибки с JAVA_HOME:
   - Убедитесь, что переменная среды JAVA_HOME указывает на корректную версию JDK
   - Проверьте версию Java: `java -version`
   - Перезапустите Android Studio после изменения JAVA_HOME

## Дополнительные ресурсы

- [Официальная документация Android](https://developer.android.com/docs)
- [Руководство по Kotlin](https://kotlinlang.org/docs/home.html)
- [Документация WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
