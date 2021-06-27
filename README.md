## Scrape It
Scrape Swayam to populate own db

## To run the project locally

- Clone the repo onto your machine
- Ensure mongodb and rabbitmq are running locally on your machine (Right now the project assumes default config with no auth)
  Alternatively if you have docker engine installed and docker-compose setup, navigate to the root directory

  ``` docker-compose up ```


- Make the API service up and running. When in root directory, run the following command

  ``` ./gradlew api:bootRun```

- Make the Async service up and running. When in root directory, run the following command

  ```./gradlew async:bootRun```

And you are all set, to access the apis from localhost!

## Pending things
- Tests
- Scraping the details page of course

