# ----------------------
# Stage 1: Build the JAR
# ----------------------
FROM maven:3.9.3-eclipse-temurin-17 AS build

# First, copy and build the contract
WORKDIR /app
# Copy full contract project (parent + child modules)
COPY aithronix-contract ./aithronix-contract

# Build contracts (parent + submodules)
WORKDIR /app/aithronix-contract
RUN mvn clean install -DskipTests

# Then build the main application
WORKDIR /app/aithronix-cart
COPY aithronix-cart/pom.xml .
COPY aithronix-cart/src ./src
RUN mvn clean package -DskipTests

# ----------------------
# Stage 2: Run the app
# ----------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/aithronix-cart/target/aithronix-cart-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8082
EXPOSE 5005

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
