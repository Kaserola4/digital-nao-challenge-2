# 🔬 Google Scholar Research Integration System

Automated system for retrieving and managing research publications from Google Scholar for the Top 3 researchers at the Innovation Center.

## 📋 Project Purpose

Automate the retrieval and storage of research publications for the Top 3 researchers at the Innovation Center using the Google Scholar API, eliminating manual data entry processes and enabling real-time access to academic metrics.

## 🎯 Key Functionalities

- 🔌 **Google Scholar Integration** - Connects to Google Scholar via SerpAPI to retrieve researcher profiles and publications
- 🏗️ **MVC Architecture** - Implements Java MVC architecture with Spring Boot for robust data extraction
- 💾 **Automated Data Population** - Automatically populates a relational database with author and article information
- 🛡️ **Error Handling** - Comprehensive error handling with consistent API responses
- ✅ **Data Validation** - Validates data integrity and handles missing or malformed information
- 📊 **Real-time Metrics** - Access to citation counts, h-index, and publication trends

## 💡 Project Relevance

**Problem:** Manual data collection is time-consuming, error-prone, and difficult to maintain consistently across multiple researchers.

**Solution:** This system automates researcher data integration, saving time, improving accuracy, and enabling scalable updates for institutional research metrics. The Innovation Center can now track research output efficiently and make data-driven decisions.

## 🛠️ Tech Stack

- **Java 17** - Programming language
- **Spring Boot 3.5.6** - Application framework
- **Spring Web** - REST API development
- **RestTemplate** - HTTP client for SerpAPI integration
- **SerpAPI** - Google Scholar data provider
- **SpringDoc OpenAPI (Swagger)** - Interactive API documentation
- **Spring Dotenv** - Environment variable management
- **Maven** - Build and dependency management

## 📋 Prerequisites

Before you begin, ensure you have:  

- ☕ **Java 17** or higher installed ([Download](https://adoptium.net/))
- 📦 **Maven 3.6+** installed ([Download](https://maven.apache.org/download.cgi))
- 🔑 **SerpAPI Account** - Sign up at [serpapi.com](https://serpapi.com/) (100 free searches/month)

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Kaserola4/digital-nao-challenge-3.git
cd digital-nao-challenge-3/google-scholar
```
## ⚙️ Configure Environment

Create an environment file based on the example:
```bash
cp .env.example .env
```
## 🧩 Build the Project

Make sure you have Java 17+ and Maven installed.
Then build the project:
```bash
mvn clean install
```
## ▶️ Run the Application

You can run the application using Maven:
```bash
mvn spring-boot:run
```

## 🧪Testing the API
### Swagger UI

- Navigate to http://localhost:8080/swagger-ui/index.html
 Find the GET /author/{authorId} endpoint
- Click "Try it out"
- Enter an Author ID (e.g., EicYvbwAAAAJ for Geoffrey Hinton)
- Click "Execute"
-  View the response below
