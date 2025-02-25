# Android Site Monitor App

This repository contains the source code for an Android app that monitors the availability of a website using a background worker.

## Features

- Periodically checks the availability of a specified website.
- Sends notifications if the website is down.

## Usage

1. Clone the repository.
2. Replace `"https://example.com"` in `MainActivity.kt` with the URL of the website you want to monitor.
3. Build and run the app on an Android device.

## Dependencies

- `androidx.work:work-runtime-ktx`
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`
- `androidx.core:core-ktx`

## License

This project is licensed under the MIT License.
