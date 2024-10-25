# weather-app
Real-Time Data Processing System for Weather Monitoring with Rollups and Aggregates

Name: Priyam (kumaripriyam2001@gmail.com)
Weather App
Github Link : https://github.com/priyam0112/weather-app.git
Summary
The Weather App is a web application that provides users with daily weather summaries for
various cities. It retrieves weather data from the OpenWeatherMap API, processes the
information to generate daily summaries, and displays them in a user-friendly interface. The
application allows users to monitor temperature trends and receive alerts for extreme weather
conditions.
Features
• Fetches real-time weather data for multiple cities.
• Stores daily weather summaries in a MongoDB database.
• Displays weather summaries in a responsive, user-friendly interface.
• Provides alert thresholds for temperature and specific weather conditions.
Technologies Used
• Backend: Java with Spring Boot
• Database: MongoDB
• Frontend: HTML, CSS, JavaScript, Thymeleaf
• API: OpenWeatherMap API
Getting Started
Prerequisites
• Java 11 or later
• Maven
• MongoDB
• An API key from OpenWeatherMap
Installation
1. Clone the repository:
Run this command in terminal.
git clone https://github.com/priyam0112/weather-app.git
cd weather-app
cd zip
2. Set up MongoDB:
o Ensure MongoDB is installed and running.
o Create a database named weatherDB.
3. Configure API key:
o Open WeatherService.java class and add your Open weather API key.
4. Build the project:
Run this command in terminal.
mvn clean install
5. Run the application:
mvn spring-boot:run
6. Access the application:
o Open your web browser and navigate to -
http://localhost:8080/weather-summary.
Database Commands
Show Databases
To list all databases in your MongoDB instance, use:
show dbs
Use the Weather Database
Switch to the weatherDB database:
use weatherDB
View Daily Weather Summaries
To view all entries in the dailyWeatherSummaries collection:
db.dailyWeatherSummaries.find().pretty()
Clear Daily Weather Summaries
To clear all entries from the dailyWeatherSummaries collection:
db.dailyWeatherSummaries.deleteMany({})
Drop the Weather Database
If you need to delete the entire database:
use weatherDB
db.dropDatabase()
Usage
Alerts
• The homepage displays the daily weather summary table for predefined cities.
• You can enter a city name in the search box and click "Search" to view the weather
summary for that specific city.
• The application tracks user-defined thresholds for temperature or specific weather
conditions.
• Alerts are triggered if conditions exceed these thresholds, providing real-time
notifications.